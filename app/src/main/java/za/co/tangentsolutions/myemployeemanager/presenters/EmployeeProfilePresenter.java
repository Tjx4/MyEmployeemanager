package za.co.tangentsolutions.myemployeemanager.presenters;

import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.EmployeeProfileActivity;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.contracts.EmployeeProfilePresenterContract;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeDetailsModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.FullEmployeeProfileModel;
import za.co.tangentsolutions.myemployeemanager.models.PositionModel;
import za.co.tangentsolutions.myemployeemanager.models.UserModel;
import za.co.tangentsolutions.myemployeemanager.providers.HttpConnectionProvider;
import za.co.tangentsolutions.myemployeemanager.providers.RestServiceProvider;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeProfileView;

public class EmployeeProfilePresenter extends BaseChildPresenter implements EmployeeProfilePresenterContract {
    private EmployeeProfileView employeeProfileView;
    private List<EmployeeDetailsModel> employeeDetailsList;
    private EmployeeModel currentEmplyee;
    private boolean isMyprofile;
    private FullEmployeeProfileModel fullEmployeeProfileModel;

    public EmployeeProfilePresenter(EmployeeProfileActivity employeeProfileActivity) {
        super(employeeProfileActivity);
        this.employeeProfileView = employeeProfileActivity;

        Bundle payloadBundle = employeeProfileActivity.getPayloadBundle();
        isMyprofile = payloadBundle.getBoolean(Constants.ISMYPROFILE_KEY);

        if(isMyprofile) {
            employeeProfileView.showAdminTools();
            showMyProfileInfo();
        }
        else {
            setEmployeeDetails(getEmployeeFromBundle(payloadBundle));
            employeeProfileView.showEmployeeDetails(getUserDetails());
        }
    }

    @Override
    public void showMyProfileInfo() {
        new DoAsyncCall(0).execute();
    }

    @Override
    public boolean isMyprofile() {
        return isMyprofile;
    }

    @Override
    public void setMyprofile(boolean myprofile) {
        isMyprofile = myprofile;
    }

    @Override
    public EmployeeModel getEmployeeFromBundle(Bundle payloadBundle){
        UserModel userModel = new UserModel();
        userModel.setId(payloadBundle.getInt(Constants.ID_KEY));
        userModel.setUsername(payloadBundle.getString(Constants.UNAME_KEY));
        userModel.setFirst_name(payloadBundle.getString(Constants.FNAME_KEY));
        userModel.setLast_name(payloadBundle.getString(Constants.SNAME_KEY));
        userModel.setIs_active(payloadBundle.getBoolean(Constants.ISACTIVE_KEY));
        userModel.setIs_staff(payloadBundle.getBoolean(Constants.ISSTUFF_KEY));
        userModel.setIs_superUser(payloadBundle.getBoolean(Constants.ISSUPERUSER_KEY));

        PositionModel positionModel = new PositionModel();
        positionModel.setName(payloadBundle.getString(Constants.POSITION_KEY) );

        EmployeeModel currentEmplyee = new EmployeeModel();
        currentEmplyee.setUser(userModel);
        currentEmplyee.setPosition(positionModel);
        currentEmplyee.setPhone_number(payloadBundle.getString(Constants.PHONENUMBER_KEY));
        currentEmplyee.setEmail(payloadBundle.getString(Constants.EMAIL_KEY));
        return currentEmplyee;
    }

    @Override
    public void setEmployeeDetails(EmployeeModel currentEmployee) {
        String fullName = currentEmployee.getUser().getFirst_name() +" "+ currentEmployee.getUser().getLast_name();

        List<EmployeeDetailsModel> employeeDetailsList = new ArrayList<>();
        employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.nameTag), fullName));
        employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.usernameTag), currentEmployee.getUser().getUsername()));
        employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.emailTag), currentEmployee.getEmail()));
        employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.positionTag), currentEmployee.getPosition().getName()));

        if(isMyprofile){
            employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.id_number), currentEmployee.getId_number()));
            employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.tax_number), currentEmployee.getTax_number()));
            employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.next_review), currentEmployee.getNext_review()));
            employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.physical_address), currentEmployee.getPhysical_address()));

            for(int n = 0; n < currentEmployee.getEmployee_next_of_kin().getEmployee_next_of_kin().size(); ++n){
                int nextOfKinIndex = n + 1;

                employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.next_of_kin)+" "+ nextOfKinIndex,  currentEmployee.getEmployee_next_of_kin().getEmployee_next_of_kin().get(n).getName()));

            }

        }

        this.employeeDetailsList = employeeDetailsList;
    }

    @Override
    public List<EmployeeDetailsModel> getUserDetails(){
        return employeeDetailsList;
    }

    @Override
    public String makeFullEmployeeDetailsHttpCall() throws IOException {
        String service = RestServiceProvider.authenticate.getPath();
        String url = currentenvironment + service;

        return new HttpConnectionProvider().makeOathCall(url, "GET", true, true, httpConTimeout, 3, this);
    }

    @Override
    public String getfullEmployeeDetails() throws IOException, JSONException {
        fullEmployeeProfileModel = new FullEmployeeProfileModel();
        String response = makeFullEmployeeDetailsHttpCall();
        fullEmployeeProfileModel.setModel(new JSONObject(response));
        return response;
    }

    @Override
    protected void duringAsyncCall(int actionIndex) {
        if(actionIndex == 0 && isCached())
            return;

        employeeProfileView.showLoadingDialog(activity.getString(R.string.loading_your_profile));
    }

    @Override
    protected Object doAsyncOperation(DoAsyncCall currentTusk, int actionIndex) throws Exception {
        super.doAsyncOperation(currentTusk, actionIndex);

        String response = null;

        switch (actionIndex){
            case 0:
                response = getfullEmployeeDetails();
                fullEmployeeProfileModel.setSuccessful(false);
                break;
        }

        return response;
    }

    @Override
    protected void afterAsyncCall(int actionIndex) {
        if(fullEmployeeProfileModel.isSuccessful()){
            switch (actionIndex){
                case 0:
                    setEmployeeDetails(fullEmployeeProfileModel.getEmployee());
                    employeeProfileView.showEmployeeDetails(getUserDetails());
                    break;
            }
        }
        else {
            employeeProfileView.showHttpCallError(activity.getString((R.string.full_employees_fetch_error_message)));
            super.afterAsyncCall(actionIndex);
        }

        super.afterAsyncCall(actionIndex);
    }
}