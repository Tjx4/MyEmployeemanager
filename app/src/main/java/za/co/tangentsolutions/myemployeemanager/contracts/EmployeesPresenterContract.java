package za.co.tangentsolutions.myemployeemanager.contracts;

import org.json.JSONException;
import java.io.IOException;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeListModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;

public interface EmployeesPresenterContract extends BasePresenterContract {
    void initializeFilteredEmployeesList();
    void initializeEmployeeListAndBasicFilters();
    void handleOnFilterSpinnerClicked(EmployeeFilterModel filter);
    void setCustomFilters(EmployeeFilterFragment filterFragment);
    void showEmployeesList(List<EmployeeModel> employeesList);
    String getEmployees() throws IOException, JSONException;
    String getFilteredEmployees() throws IOException, JSONException;
    void showAllEmployees();
    String makeEmployeesHttpCall() throws IOException;
    String checkEmployeeListUpdate() throws IOException, JSONException;
    boolean isNewEmployeeAvailable(EmployeeListModel remoteEmployeeList);
    boolean isUpdatedEmployeeDetail(EmployeeModel employeeModel, EmployeeModel remoteEmployeeModel);
}