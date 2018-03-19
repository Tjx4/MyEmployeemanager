package za.co.tangentsolutions.myemployeemanager.views;

import java.util.List;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatModel;

public interface EmployeeStatsView extends BaseAsyncView {
    void showEmployeeDetails(List<EmployeeStatModel> employeeDetailsList);
}
