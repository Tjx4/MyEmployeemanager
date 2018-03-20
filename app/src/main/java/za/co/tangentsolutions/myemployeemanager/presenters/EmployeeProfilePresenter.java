package za.co.tangentsolutions.myemployeemanager.presenters;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.EmployeeProfileActivity;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.contracts.EmployeeProfilePresenterContract;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeDetailsModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.FullEmployeeProfileModel;
import za.co.tangentsolutions.myemployeemanager.models.PositionModel;
import za.co.tangentsolutions.myemployeemanager.models.UserModel;
import za.co.tangentsolutions.myemployeemanager.providers.UserClient;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeProfileView;

public class EmployeeProfilePresenter extends BaseChildPresenter implements EmployeeProfilePresenterContract {
    private EmployeeProfileView employeeProfileView;
    private List<EmployeeDetailsModel> employeeDetailsList;
    private boolean isMyprofile;
    private FullEmployeeProfileModel fullEmployeeProfileModel;
    private int detailsCount;

    public EmployeeProfilePresenter(){
        super();
    }

    public EmployeeProfilePresenter(EmployeeProfileActivity employeeProfileActivity) {
        super(employeeProfileActivity);
        this.employeeProfileView = employeeProfileActivity;
        employeeDetailsList = new ArrayList<>();
        Bundle payloadBundle = employeeProfileActivity.getPayloadBundle();
        isMyprofile = payloadBundle.getBoolean(Constants.ISMYPROFILE_KEY);

        if(isMyprofile) {
            employeeProfileView.showAdminTools();
            showMyProfileInfo();
        }
        else {
            setEmployeeDetails(getEmployeeFromBundle(payloadBundle));
            employeeProfileView.porpulateDetailsListView(getUserDetails());
        }
    }
    @Override
    public int getDetailsCount() {
        return detailsCount;
    }

    @Override
    public void showMyProfileInfo() {
        fetchFullUserProfile();
    }

    @Override
    public void fetchFullUserProfile(){
        employeeProfileView.showLoadingDialog(activity.getString(R.string.fetching_employees));

        UserClient userClient = getUserClient();
        Call<EmployeeModel> call = userClient.getEmployee(getToken());

        call.enqueue(new Callback<EmployeeModel>() {
            @Override
            public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                if(response.isSuccessful()){
                    EmployeeModel fullEmployeeProfile = response.body();

                    if(fullEmployeeProfileModel == null)
                        fullEmployeeProfileModel = new FullEmployeeProfileModel();

                    fullEmployeeProfileModel.setEmployee(fullEmployeeProfile);

                    setEmployeeDetails(fullEmployeeProfileModel.getEmployee());
                    employeeProfileView.porpulateDetailsListView(getUserDetails());
                    detailsCount = getUserDetails().size();
                    fullEmployeeProfileModel.setSuccessful(false);
                }
                else{
                    employeeProfileView.showHttpCallError(activity.getString((R.string.employees_stats_error_message)));
                }

                employeeProfileView.hideLoadingDialog();
            }

            @Override
            public void onFailure(Call<EmployeeModel> call, Throwable t) {
                employeeProfileView.showHttpCallError(activity.getString((R.string.connection_error_message)));
            }
        });
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

            for(int n = 0; n < currentEmployee.getEmployee_next_of_kin().size(); ++n){
                int nextOfKinIndex = n + 1;

                employeeDetailsList.add(new EmployeeDetailsModel(activity.getString(R.string.next_of_kin)+" "+ nextOfKinIndex,  currentEmployee.getEmployee_next_of_kin().get(n).getName()));

            }

        }

        this.employeeDetailsList = employeeDetailsList;
    }

    @Override
    public List<EmployeeDetailsModel> getUserDetails(){
        return employeeDetailsList;
    }

}