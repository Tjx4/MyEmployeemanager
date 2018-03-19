package za.co.tangentsolutions.myemployeemanager.presenters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.tangentsolutions.myemployeemanager.activities.EmployeesDashBoardActivity;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeListModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.providers.BasicEmployeeFiltersProviders;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.providers.UserClient;
import za.co.tangentsolutions.myemployeemanager.views.EmployeesDashBoardView;
import za.co.tangentsolutions.myemployeemanager.contracts.EmployeesPresenterContract;

public class EmployeesDashboardPresenter extends BaseSlideMenuPresenter implements EmployeesPresenterContract {

    private EmployeesDashBoardView employeesDashBoardView;
    private EmployeeListModel employeeListModel;
    private List<EmployeeFilterModel> filters;
    Map<String, String> params;
    private boolean isPorpulatedEmployees, isPorpulatedFilters;
    private String employeeFilterString = "";

    public EmployeesDashboardPresenter() {
        super();
    }

    public EmployeesDashboardPresenter(EmployeesDashBoardActivity employeesDashboardActivity) {
        super(employeesDashboardActivity);
        this.employeesDashBoardView = employeesDashboardActivity;
        employeeListModel = new EmployeeListModel();
        filters = new ArrayList<>();
        params = new HashMap<>();
        initializeEmployeeListAndBasicFilters();
    }

    @Override
    public void initializeEmployeeListAndBasicFilters() {
        showEmployeeFilters();
        showAllEmployees();
    }

    @Override
    public void showEmployeeFilters() {
        employeesDashBoardView.porpulateBasicFilterListView(new BasicEmployeeFiltersProviders(activity).getBasicFilters());
        isPorpulatedFilters = true;
    }

    @Override
    public void showFilteredEmployees() {
        int indx = 0;

        for(EmployeeFilterModel currentFilter : filters){
            if(currentFilter.getKey().isEmpty() || currentFilter.getValue().isEmpty())
                continue;

            params.put(currentFilter.getKey(), currentFilter.getValue());
        }

        fetchRemoteEmployeesList();
    }

    @Override
    public void showAllEmployees() {
        employeeListModel = cacheProvider.getCachedEmployeeList();

        if(isSetEmployeeList()) {
            showEmployeesList(employeeListModel.getEmployee());
            isCheckingUpdates = true;
        }

        fetchRemoteEmployeesList();
    }

    @Override
    public void handleOnFilterSpinnerClicked(EmployeeFilterModel filter) {
        this.filters.add(filter);
        showFilteredEmployees();
    }

    @Override
    public void setCustomFilters(EmployeeFilterFragment filterFragment){
        List<EmployeeFilterModel> filters = new BasicEmployeeFiltersProviders(activity).getCustomFilters(filterFragment);
        
        if(filters.isEmpty()) {
            employeesDashBoardView.showEmptyFilterWarnigToast(R.string.no_filter_warning_string);
            return;
        }

        setFilters(filters);
        showFilteredEmployees();
    }

    @Override
    public void setFilters(List<EmployeeFilterModel> filters){
        this.filters = filters;
    }

    @Override
    public List<EmployeeFilterModel> getFilters(){
        return filters;
    }

    @Override
    public void clearFiltersIfSet(){
        if(filters.size() > 0) {
            filters.clear();
            params.clear();
        }
    }

    @Override
    public void showEmployeesList(List<EmployeeModel> employeesList) {
        employeesDashBoardView.porpulateEmployeesList(employeesList, cacheProvider.getCachedUser().getId());
        isPorpulatedEmployees = true;
    }

    @Override
    public boolean isUpdatedEmployeeDetail(EmployeeModel employeeModel, EmployeeModel remoteEmployeeModel) {
        boolean isChangedUserEmailDetails = !employeeModel.getEmail().equals(remoteEmployeeModel.getEmail());
        boolean isChangedUserFirstNameDetails = !employeeModel.getUser().getFirst_name().equals(remoteEmployeeModel.getUser().getFirst_name());
        boolean isChangedUserLastNameDetails = !employeeModel.getUser().getLast_name().equals(remoteEmployeeModel.getUser().getLast_name());

        return isChangedUserEmailDetails || isChangedUserFirstNameDetails || isChangedUserLastNameDetails;
    }

    @Override
    public boolean isNewEmployeeAvailable(EmployeeListModel remoteEmployeeList) {
        List<EmployeeModel> cachedEmployees = cacheProvider.getCachedEmployeeList().getEmployee();
        List<EmployeeModel> remoteEmployees = remoteEmployeeList.getEmployee();

        if(cachedEmployees.size() != remoteEmployees.size())
            return true;

        boolean hasUpdate = false;
        int indx = 0;
        for(EmployeeModel i : cachedEmployees){
            if(isUpdatedEmployeeDetail(i, remoteEmployees.get(indx))){
                hasUpdate = true;
                break;
            }
            indx++;
        }
        return hasUpdate;
    }

    @Override
    public void fetchRemoteEmployeesList(){
        if(!isCheckingUpdates)
            employeesDashBoardView.showLoadingDialog(activity.getString(R.string.fetching_employees));

        UserClient userClient = getUserClient();
        Call<List<EmployeeModel>> call = userClient.getEmployeesList(getToken(), params);

        call.enqueue(new Callback<List<EmployeeModel>>() {
            @Override
            public void onResponse(Call<List<EmployeeModel>> call, Response<List<EmployeeModel>> response) {
                if(response.isSuccessful()){
                    List<EmployeeModel> remoteEmployeeList = response.body();

                    employeeListModel.setEmployee(remoteEmployeeList);

                    if(isCheckingUpdates) {
                        EmployeeListModel employeeListModel = new EmployeeListModel();
                        employeeListModel.setEmployee(remoteEmployeeList);

                        if (isNewEmployeeAvailable(employeeListModel))
                            cacheProvider.cacheEmployeeList(employeeListModel);

                        isCheckingUpdates = false;
                    }
                    else{
                        showEmployeesList(employeeListModel.getEmployee());

                        if(filters.isEmpty())
                            cacheProvider.cacheEmployeeList(employeeListModel);
                    }
                }
                else{
                    employeesDashBoardView.showHttpCallError(activity.getString((R.string.employees_fetch_error_message)));
                }

                employeesDashBoardView.hideLoadingDialog();
                clearFiltersIfSet();
            }

            @Override
            public void onFailure(Call<List<EmployeeModel>> call, Throwable t) {
                employeesDashBoardView.showHttpCallError(activity.getString((R.string.connection_error_message)));
                clearFiltersIfSet();
            }
        });
    }

    @Override
    public boolean isSetEmployeeList() {
        return employeeListModel != null && employeeListModel.getEmployee() != null && !employeeListModel.getEmployee().isEmpty();
    }

}