package za.co.tangentsolutions.myemployeemanager.contracts;

import java.util.List;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeListModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatModel;

public interface EmployeeStatsPrestenterContract {
    List<EmployeeStatModel> getUserStats();
    void setEmployeeStats(EmployeeListModel currentEmployee);
    void fetchRemoteEmployeesAndShowStats();
    String getEmployeeCount(List<EmployeeModel> employeeList);
    String getBirthDaysThisMonthCount(List<EmployeeModel> employeeList, int daysLeftInMonth);
    String getByGenderCount(List<EmployeeModel> employeeList, String gender);
    String getFemaleCount(List<EmployeeModel> employeeList);
    String getMaleCount(List<EmployeeModel> employeeList);
    int getStatsCount();
}