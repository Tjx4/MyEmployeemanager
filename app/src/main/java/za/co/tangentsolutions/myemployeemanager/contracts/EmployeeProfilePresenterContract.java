package za.co.tangentsolutions.myemployeemanager.contracts;

import android.os.Bundle;
import org.json.JSONException;
import java.io.IOException;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeDetailsModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;

public interface EmployeeProfilePresenterContract extends BasePresenterContract {
    List<EmployeeDetailsModel> getUserDetails();
    String getfullEmployeeDetails() throws IOException, JSONException;
    String makeFullEmployeeDetailsHttpCall() throws IOException;
    void setEmployeeDetails(EmployeeModel currentEmployee);
    EmployeeModel getEmployeeFromBundle(Bundle payloadBundle);
    boolean isMyprofile();
    void showMyProfileInfo();
    void setMyprofile(boolean myprofile);
    int getDetailsCount();
}