package za.co.tangentsolutions.myemployeemanager.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EmployeeStatsListModel extends BaseModel{

    EmployeeListModel employeeStatsList;

    public void setModel(JSONArray employeesListJsonArray) throws JSONException {
        employeeStatsList = new EmployeeListModel();
        employeeStatsList.setEmployee(new ArrayList<EmployeeModel>());

        for(int i = 0; i < employeesListJsonArray.length(); i++){
            JSONObject employeeJSon = (JSONObject)employeesListJsonArray.get(i);
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setModel(employeeJSon);
            employeeStatsList.getEmployee().add(employeeModel);
        }
    }

    public EmployeeListModel getEmployeeStatsList() {
        return employeeStatsList;
    }

    public void setEmployeeStatsList(EmployeeListModel employeeStatsList) {
        this.employeeStatsList = employeeStatsList;
    }
}
