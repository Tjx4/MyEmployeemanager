package za.co.tangentsolutions.myemployeemanager.presenters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.contracts.EmployeeStatsPrestenterContract;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeListModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatsListModel;
import za.co.tangentsolutions.myemployeemanager.providers.UserClient;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeStatsView;

public class EmployeeStatsPrestenter extends BaseChildPresenter implements EmployeeStatsPrestenterContract {

    private EmployeeStatsView employeeStatsView;
    private List<EmployeeStatModel> employeeStatsList;
    private EmployeeStatsListModel employeeStatsListModel;
    private int statsCount;

    public EmployeeStatsPrestenter(BaseActivity activity) {
        super(activity);
        employeeStatsView = (EmployeeStatsView)activity;
        employeeStatsList = new ArrayList<>();
        employeeStatsListModel = new EmployeeStatsListModel();
        fetchRemoteEmployeesAndShowStats();
    }

    @Override
    public int getStatsCount() {
        return statsCount;
    }

    @Override
    public void fetchRemoteEmployeesAndShowStats(){
        employeeStatsView.showLoadingDialog(activity.getString(R.string.fetching_employees));

        UserClient userClient = getUserClient();
        Call<List<EmployeeModel>> call = userClient.getEmployeesList(getToken(), new HashMap<String, String>());

        call.enqueue(new Callback<List<EmployeeModel>>() {
            @Override
            public void onResponse(Call<List<EmployeeModel>> call, Response<List<EmployeeModel>> response) {
                if(response.isSuccessful()){
                    List<EmployeeModel> remoteEmployeeList = response.body();
                    EmployeeListModel employeeListModel = new EmployeeListModel();
                    employeeListModel.setEmployee(remoteEmployeeList);

                    employeeStatsListModel.setEmployeeStatsList(employeeListModel);
                    setEmployeeStats(employeeStatsListModel.getEmployeeStatsList());
                    employeeStatsView.porpulateStatsListView(getUserStats());
                    statsCount = employeeStatsListModel.getEmployeeStatsList().getEmployee().size();
                }
                else{
                    employeeStatsView.showHttpCallError(activity.getString((R.string.employees_stats_error_message)));
                }

                employeeStatsView.hideLoadingDialog();
            }

            @Override
            public void onFailure(Call<List<EmployeeModel>> call, Throwable t) {
                employeeStatsView.showHttpCallError(activity.getString((R.string.connection_error_message)));
            }
        });
    }

    @Override
    public void setEmployeeStats(EmployeeListModel currentEmployee) {

        List<EmployeeStatModel> employeeStatsList = new ArrayList<>();
        employeeStatsList.add(new EmployeeStatModel("Number of employees", getEmployeeCount(currentEmployee.getEmployee())));

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
    public String getEmployeeCount(List<EmployeeModel> employeeList) {
        return ""+employeeList.size();
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

}
