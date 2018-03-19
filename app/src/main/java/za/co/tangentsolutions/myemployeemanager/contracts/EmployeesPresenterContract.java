package za.co.tangentsolutions.myemployeemanager.contracts;

import org.json.JSONException;
import java.io.IOException;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeListModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;

public interface EmployeesPresenterContract extends BasePresenterContract {
    void initializeEmployeeListAndBasicFilters();
    void handleOnFilterSpinnerClicked(EmployeeFilterModel filter);
    void setCustomFilters(EmployeeFilterFragment filterFragment);
    void showEmployeesList(List<EmployeeModel> employeesList);
    void showAllEmployees();
    boolean isNewEmployeeAvailable(EmployeeListModel remoteEmployeeList);
    boolean isUpdatedEmployeeDetail(EmployeeModel employeeModel, EmployeeModel remoteEmployeeModel);
    void showFilteredEmployees();
    void fetchRemoteEmployeesList();
    void showEmployeeFilters();
    void setFilters(List<EmployeeFilterModel> filters);
    List<EmployeeFilterModel> getFilters();
    boolean isSetEmployeeList();
    void clearFiltersIfSet();
}