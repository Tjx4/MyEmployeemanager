package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginModel extends BaseModel{
    private String token;

    @Override
    public void setModel(JSONObject responseJson) throws JSONException {
        setToken(responseJson.getString("token"));
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

}
