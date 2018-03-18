package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONException;
import org.json.JSONObject;

public class NextOfKinModel {
    private int id;
    private String name;
    private String relationship;
    private String phone_number;
    private String email;
    private String physical_address;
    private int employee;

    public void setModel(JSONObject nextOfkinJson) throws JSONException {
        setId(nextOfkinJson.getInt("id"));
        setName(nextOfkinJson.getString("name"));
        setRelationship(nextOfkinJson.getString("relationship"));
        setPhone_number(nextOfkinJson.getString("phone_number"));
        setEmail(nextOfkinJson.getString("email"));
        setPhysical_address(nextOfkinJson.getString("physical_address"));

        if(nextOfkinJson.has("employee"))
            setEmployee(nextOfkinJson.getInt("employee"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhysical_address() {
        return physical_address;
    }

    public void setPhysical_address(String physical_address) {
        this.physical_address = physical_address;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }
}