package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONException;
import org.json.JSONObject;

public class FullEmployeeProfileModel extends BaseModel{
    private EmployeeModel employee;

    public void setModel(JSONObject employeeJsonObject) throws JSONException {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setModel(employeeJsonObject);
        setEmployee(employeeModel);
        setSuccessful(true);
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }
}
