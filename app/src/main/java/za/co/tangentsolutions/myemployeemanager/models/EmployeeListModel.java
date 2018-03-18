package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class EmployeeListModel extends BaseModel{
    List<EmployeeModel> employee;

    public void setModel(JSONArray employeesListJsonArray) throws JSONException {
        employee = new ArrayList<>();
        for(int i = 0; i < employeesListJsonArray.length(); i++){
            JSONObject employeeJSon = (JSONObject)employeesListJsonArray.get(i);
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setModel(employeeJSon);
            employee.add(employeeModel);
        }
    }

    public List<EmployeeModel> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeModel> employee) {
        this.employee = employee;
    }
}