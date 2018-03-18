package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class NextOFkinListModel {
    List<NextOfKinModel> employee_next_of_kin;

    public void setModel(JSONArray nextOfKinListJsonArray) throws JSONException {
        employee_next_of_kin = new ArrayList<>();
        for(int i = 0; i < nextOfKinListJsonArray.length(); i++){
            JSONObject employeeJSon = (JSONObject)nextOfKinListJsonArray.get(i);
            NextOfKinModel nextOfKinModel = new NextOfKinModel();
            nextOfKinModel.setModel(employeeJSon);
            employee_next_of_kin.add(nextOfKinModel);
        }
    }

    public List<NextOfKinModel> getEmployee_next_of_kin() {
        return employee_next_of_kin;
    }

    public void setEmployee_next_of_kin(List<NextOfKinModel> employee_next_of_kin) {
        this.employee_next_of_kin = employee_next_of_kin;
    }
}
