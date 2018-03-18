package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ReviewListModel {
    List<ReviewModel> employee_review;

    public void setModel(JSONArray reviewListJsonArray) throws JSONException {
        employee_review = new ArrayList<>();
        for(int i = 0; i < reviewListJsonArray.length(); i++){
            JSONObject employeeJSon = (JSONObject)reviewListJsonArray.get(i);
            ReviewModel reviewModel = new ReviewModel();
            reviewModel.setModel(employeeJSon);
            employee_review.add(reviewModel);
        }
    }

    public List<ReviewModel> getEmployee_review() {
        return employee_review;
    }

    public void setEmployee_review(List<ReviewModel> employee_review) {
        this.employee_review = employee_review;
    }
}