package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONException;
import org.json.JSONObject;

public class LeaveModel {
    private int id;
    private String start_date;
    private String end_date;
    private String status;
    private String half_day;
    private String type;
    private String upload;
    private String leave_days;
    private EmployeeModel employee;

    public void setModel(JSONObject leaveJson) throws JSONException {

        if(leaveJson.has("employee")) {
            EmployeeModel employeeModel = new EmployeeModel();

            Object leaveObj = leaveJson;

            if(leaveObj instanceof Integer) {
                employeeModel.setUser(new UserModel());
                employeeModel.getUser().setId(leaveJson.getInt("employee"));
            }
            else {
                employeeModel.setModel(leaveJson.getJSONObject("employee"));
                setEmployee(employeeModel);
            }
        }

        setId(leaveJson.getInt("id"));
        setStart_date(leaveJson.getString("start_date"));
        setEnd_date(leaveJson.getString("end_date"));
        setStatus(leaveJson.getString("status"));
        setHalf_day(leaveJson.getString("half_day"));
        setType(leaveJson.getString("type"));
        setUpload(leaveJson.getString("upload"));
        setLeave_days(leaveJson.getString("leave_days"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHalf_day() {
        return half_day;
    }

    public void setHalf_day(String half_day) {
        this.half_day = half_day;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getLeave_days() {
        return leave_days;
    }

    public void setLeave_days(String leave_days) {
        this.leave_days = leave_days;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }
}