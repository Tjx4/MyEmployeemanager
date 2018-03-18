package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONException;
import org.json.JSONObject;

public class ReviewModel {
    private int id;
    private String date;
    private String salary;
    private String type;
    private EmployeeModel employee;
    private int position;

    public void setModel(JSONObject reviewJson) throws JSONException {
        setId(reviewJson.getInt("id"));
        setDate(reviewJson.getString("date"));
        setSalary(reviewJson.getString("salary"));
        setType(reviewJson.getString("type"));

        if(reviewJson.has("employee"))
            setPosition(reviewJson.getInt("employee"));

        if(reviewJson.has("position"))
            setPosition(reviewJson.getInt("position"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}