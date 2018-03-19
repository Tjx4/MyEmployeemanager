package za.co.tangentsolutions.myemployeemanager.presenters;

import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.activities.EmployeesDashBoardActivity;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeListModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.providers.BasicEmployeeFiltersProviders;
import za.co.tangentsolutions.myemployeemanager.providers.HttpConnectionProvider;
import za.co.tangentsolutions.myemployeemanager.providers.RestServiceProvider;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.views.EmployeesDashBoardView;
import za.co.tangentsolutions.myemployeemanager.contracts.EmployeesPresenterContract;

public class EmployeesDashboardPresenter extends BaseSlideMenuPresenter implements EmployeesPresenterContract {

    private EmployeesDashBoardView employeesDashBoardView;
    private EmployeeListModel employeeListModel;
    private List<EmployeeFilterModel> filters;
    private boolean isListPorpulated;

    public EmployeesDashboardPresenter(EmployeesDashBoardActivity employeesDashboardActivity) {
        super(employeesDashboardActivity);
        this.employeesDashBoardView = employeesDashboardActivity;
        filters = new ArrayList<>();
        initializeEmployeeListAndBasicFilters();
    }

    @Override
    public void initializeEmployeeListAndBasicFilters() {
        new DoAsyncCall(0).execute();
    }

    @Override
    public void handleOnFilterSpinnerClicked(EmployeeFilterModel filter) {
        this.filters.add(filter);
        employeesDashBoardView.setFilterTitle(filter.getTitleText());
        initializeFilteredEmployeesList();
    }

    @Override
    public void setCustomFilters(EmployeeFilterFragment filterFragment){
        List<EmployeeFilterModel> filters = new BasicEmployeeFiltersProviders(activity).getCustomFilters(filterFragment);
        
        if(filters.isEmpty()) {
            employeesDashBoardView.showEmptyFilterWarnigToast(R.string.no_filter_warning_string);
            return;
        }

        this.filters = filters;
        initializeFilteredEmployeesList();
    }

    @Override
    public void initializeFilteredEmployeesList() {
        new DoAsyncCall(1).execute();
    }

    @Override
    public void showEmployeesList(List<EmployeeModel> employeesList) {
        employeesDashBoardView.porpulateEmployeesList(employeesList, cacheProvider.getCachedUser().getId());
        isListPorpulated = true;
    }

    @Override
    public String makeEmployeesHttpCall() throws IOException {
        String service = RestServiceProvider.authenticate.getPath();
        String url = currentenvironment + service;

        Bundle payload = new Bundle();

        for(EmployeeFilterModel currentFilter : filters){
            if(currentFilter.getKey().isEmpty() || currentFilter.getValue().isEmpty())
                continue;

            payload.putString(currentFilter.getKey(), currentFilter.getValue());
        }

        return new HttpConnectionProvider(payload).makeOathCall(url, "GET", true, true, httpConTimeout, 2, this);
    }

    @Override
    public String checkEmployeeListUpdate() throws IOException, JSONException {
        EmployeeListModel remoteEmployeeList = new EmployeeListModel();
        String response = makeEmployeesHttpCall();
        remoteEmployeeList.setModel(new JSONArray(response));

        if (isNewEmployeeAvailable(remoteEmployeeList) || !isCached())
            cacheProvider.cacheEmployeeList(remoteEmployeeList);

        return response;
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
        List<EmployeeModel> currImages = employeeListModel.getEmployee();
        List<EmployeeModel> remoteImages = remoteEmployeeList.getEmployee();

        if(currImages.size() != remoteImages.size())
            return true;

        boolean hasUpdate = false;
        int indx = 0;
        for(EmployeeModel i : currImages){
            if(isUpdatedEmployeeDetail(i, remoteImages.get(indx))){
                hasUpdate = true;
                break;
            }
            indx++;
        }
        return hasUpdate;
    }

    @Override
    public String getEmployees() throws IOException, JSONException {
        employeeListModel = new EmployeeListModel();
        String response = makeEmployeesHttpCall();
        employeeListModel.setModel(new JSONArray(response));
        employeeListModel.setSuccessful(true);
        return response;
    }

    @Override
    public void showAllEmployees() {
        employeeListModel = cacheProvider.getCachedEmployeeList();

        if(isCached())
            showEmployeesList(employeeListModel.getEmployee());
    }


    @Override
    public String getFilteredEmployees() throws IOException, JSONException {
        return getEmployees();
    }

    @Override
    protected boolean isCached() {
        return employeeListModel != null && employeeListModel.getEmployee() != null && !employeeListModel.getEmployee().isEmpty();
    }

    @Override
    protected void beforeAsyncCall(int actionIndex) {
        super.beforeAsyncCall(actionIndex);

        if(isCheckingUpdates)
            return;

        if (actionIndex == 0) {
            try{
                employeesDashBoardView.porpulateBasicFilterSpinner(new BasicEmployeeFiltersProviders(activity).getBasicFilters());

                showAllEmployees();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void duringAsyncCall(int actionIndex) {
        if(actionIndex == 0 && isCached())
            return;

        employeesDashBoardView.showLoadingDialog(activity.getString(R.string.fetching_employees));
    }

    @Override
    protected Object doAsyncOperation(DoAsyncCall currentTusk, int actionIndex) throws Exception {
        super.doAsyncOperation(currentTusk, actionIndex);

        if(isCheckingUpdates) {
            return checkEmployeeListUpdate();
        }
        else if(isCached() && actionIndex == 0){
            return null;
        }

        String response = null;

        switch (actionIndex){
            case 0:
                response = getEmployees();
                break;
             case 1:
                response = getFilteredEmployees();
                break;
        }

        return response;
    }

    @Override
    protected void afterAsyncCall(int actionIndex) {
        if(isCheckingUpdates) {
            isCheckingUpdates = false;
            return;
        }

        if(isListPorpulated && actionIndex == 0){
            checkAndUpdate();
            return;
        }

        if(employeeListModel.isSuccessful()){
            switch (actionIndex){
                case 0:
                    showEmployeesList(employeeListModel.getEmployee());
                    cacheProvider.cacheEmployeeList(employeeListModel);
                    break;
                case 1:
                    showEmployeesList(employeeListModel.getEmployee());
                    break;
            }
        }
        else {
            employeesDashBoardView.showHttpCallError(activity.getString((R.string.employees_fetch_error_message)));
        }

        filters.clear();
        super.afterAsyncCall(actionIndex);
    }
}