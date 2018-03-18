package za.co.tangentsolutions.myemployeemanager.views;

import android.os.Bundle;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeDetailsModel;

public interface EmployeeProfileView extends BaseAsyncView {
    Bundle getPayloadBundle();
    void showAdminTools();
    void showEmployeeDetails(List<EmployeeDetailsModel> employeeDetailsList);
}