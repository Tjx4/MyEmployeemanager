package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONException;
import org.json.JSONObject;

public class UserModel {
    private int id;
    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private boolean is_active;
    private boolean is_staff;
    private boolean is_superUser;
    private String token;

    public void setModel(JSONObject userJson) throws JSONException {
        setId(userJson.getInt("id"));
        setUsername(userJson.getString("username"));
        setEmail(userJson.getString("email"));
        setFirst_name(userJson.getString("first_name"));
        setLast_name(userJson.getString("last_name"));
        setIs_active(userJson.getBoolean("is_active"));
        setIs_staff(userJson.getBoolean("is_staff"));

        if(userJson.has("is_superUser"))
            setIs_superUser(userJson.getBoolean("is_superUser"));
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isIs_superUser() {
        return is_superUser;
    }

    public void setIs_superUser(boolean is_superUser) {
        this.is_superUser = is_superUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_staff() {
        return is_staff;
    }

    public void setIs_staff(boolean is_staff) {
        this.is_staff = is_staff;
    }
}