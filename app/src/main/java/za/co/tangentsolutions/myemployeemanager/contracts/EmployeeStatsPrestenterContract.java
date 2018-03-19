package za.co.tangentsolutions.myemployeemanager.contracts;

import org.json.JSONException;
import java.io.IOException;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeListModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatModel;

public interface EmployeeStatsPrestenterContract {
    List<EmployeeStatModel> getUserStats();
    void setEmployeeStats(EmployeeListModel currentEmployee);
    void showEmployeeStats();
    String makeFullEmployeeDetailsHttpCall() throws IOException;
    String getEmployeeStats() throws IOException, JSONException;
    String getEmployeeCount(List<EmployeeModel> employeeList);
    String getBirthDaysThisMonthCount(List<EmployeeModel> employeeList, int daysLeftInMonth);
    String getByGenderCount(List<EmployeeModel> employeeList, String gender);
    String getFemaleCount(List<EmployeeModel> employeeList);
    String getMaleCount(List<EmployeeModel> employeeList);
    int getStatsCount();
}