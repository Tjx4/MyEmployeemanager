package za.co.tangentsolutions.myemployeemanager.presenters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.contracts.EmployeeStatsPrestenterContract;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeListModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatsListModel;
import za.co.tangentsolutions.myemployeemanager.providers.HttpConnectionProvider;
import za.co.tangentsolutions.myemployeemanager.providers.RestServiceProvider;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeStatsView;

public class EmployeeStatsPrestenter extends BaseChildPresenter implements EmployeeStatsPrestenterContract {

    private EmployeeStatsView employeeStatsView;
    private List<EmployeeStatModel> employeeStatsList;
    private EmployeeModel currentEmplyee;
    private EmployeeStatsListModel employeeStatsListModel;

    public EmployeeStatsPrestenter(BaseActivity activity) {
        super(activity);
        employeeStatsView = (EmployeeStatsView)activity;
        showEmployeeStats();
    }

    @Override
    public void showEmployeeStats() {
        new DoAsyncCall(0).execute();
    }


    @Override
    public void setEmployeeStats(EmployeeListModel currentEmployee) {

        List<EmployeeStatModel> employeeStatsList = new ArrayList<>();
        employeeStatsList.add(new EmployeeStatModel("Number of employees", ""+currentEmployee.getEmployee().size()));

        int daysRemainingThisMonth = 11;
        employeeStatsList.add(new EmployeeStatModel("Birth days this month", getBirthDaysThisMonthCount(currentEmployee.getEmployee(), daysRemainingThisMonth)));

        employeeStatsList.add(new EmployeeStatModel("Female employees", getFemaleCount(currentEmployee.getEmployee()) ));
        employeeStatsList.add(new EmployeeStatModel("Male employees", getMaleCount(currentEmployee.getEmployee()) ));

        this.employeeStatsList = employeeStatsList;
    }


    @Override
    public List<EmployeeStatModel> getUserStats(){
        return employeeStatsList;
    }

    @Override
    public String makeFullEmployeeDetailsHttpCall() throws IOException {
        String service = RestServiceProvider.employee.getPath();
        String url = currentenvironment + service;

        return new HttpConnectionProvider().makeOathCall(url, "GET", true, true, httpConTimeout, 2, this);
    }

    @Override
    public String getEmployeeStats() throws IOException, JSONException {
        employeeStatsListModel = new EmployeeStatsListModel();
        String response = makeFullEmployeeDetailsHttpCall();
        employeeStatsListModel.setModel(new JSONArray(response));
        employeeStatsListModel.setSuccessful(true);
        return response;
    }

    @Override
    public String getBirthDaysThisMonthCount(List<EmployeeModel> employeeList, int daysLeftInMonth) {
        int count = 0;

        for(int i = 0; i < employeeList.size(); ++i){
            if( employeeList.get(i).getDays_to_birthday() <= daysLeftInMonth)
                count++;
        }
        return ""+count;
    }

    @Override
    public String getByGenderCount(List<EmployeeModel> employeeList, String gender) {
        int count = 0;

        for(int i = 0; i < employeeList.size(); ++i){
            if( gender.equals(employeeList.get(i).getGender()) )
                count++;
        }
        return ""+count;
    }

    @Override
    public String getFemaleCount(List<EmployeeModel> employeeList) {
        return getByGenderCount(employeeList,"F").toString();
    }

    @Override
    public String getMaleCount(List<EmployeeModel> employeeList) {
        return getByGenderCount(employeeList,"M").toString();
    }

    @Override
    protected void duringAsyncCall(int actionIndex) {
        if(actionIndex == 0 && isCached())
            return;

        employeeStatsView.showLoadingDialog(activity.getString(R.string.loading_stats));
    }

    @Override
    protected Object doAsyncOperation(DoAsyncCall currentTusk, int actionIndex) throws Exception {
        super.doAsyncOperation(currentTusk, actionIndex);

        String response = null;

        switch (actionIndex){
            case 0:
                response = getEmployeeStats();
                break;
        }

        return response;
    }


    @Override
    protected void afterAsyncCall(int actionIndex) {
        if(employeeStatsListModel.isSuccessful()){
            switch (actionIndex){
                case 0:
                    setEmployeeStats(employeeStatsListModel.getEmployeeStatsList());
                    employeeStatsView.showEmployeeStats(getUserStats());
                    employeeStatsListModel.setSuccessful(false);
                    break;
            }
        }
        else {
            employeeStatsView.showHttpCallError(activity.getString((R.string.employees_stats_error_message)));
            super.afterAsyncCall(actionIndex);
        }

        super.afterAsyncCall(actionIndex);
    }
}
