package za.co.tangentsolutions.myemployeemanager.contracts;

import android.os.Bundle;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeDetailsModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;

public interface EmployeeProfilePresenterContract extends BasePresenterContract {
    List<EmployeeDetailsModel> getUserDetails();
    void setEmployeeDetails(EmployeeModel currentEmployee);
    EmployeeModel getEmployeeFromBundle(Bundle payloadBundle);
    boolean isMyprofile();
    void showMyProfileInfo();
    void fetchFullUserProfile();
    void setMyprofile(boolean myprofile);
    int getDetailsCount();
}