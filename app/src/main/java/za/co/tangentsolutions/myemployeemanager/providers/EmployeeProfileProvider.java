package za.co.tangentsolutions.myemployeemanager.providers;

import android.os.Bundle;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;

public class EmployeeProfileProvider {

    public Bundle getEmployeeProfile(EmployeeModel employeeModel) {
        Bundle payload = new Bundle();
        payload.putInt(Constants.ID_KEY, employeeModel.getUser().getId());
        payload.putString(Constants.UNAME_KEY, employeeModel.getUser().getUsername());
        payload.putString(Constants.FNAME_KEY, employeeModel.getUser().getFirst_name());
        payload.putString(Constants.SNAME_KEY, employeeModel.getUser().getLast_name());
        payload.putString(Constants.POSITION_KEY, employeeModel.getPosition().getName());
        payload.putString(Constants.EMAIL_KEY, employeeModel.getEmail());
        payload.putBoolean(Constants.ISACTIVE_KEY, employeeModel.getUser().isIs_active());
        payload.putBoolean(Constants.ISSTUFF_KEY, employeeModel.getUser().isIs_staff());
        payload.putBoolean(Constants.ISSUPERUSER_KEY, employeeModel.getUser().isIs_superUser());
        return payload;
    }
}