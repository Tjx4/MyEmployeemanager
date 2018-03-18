package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseModel {
    private boolean isSuccessful;
    private String responseMessage;
    private String responseCode;

    public void setModel(JSONObject responseJson) throws JSONException {
        if(responseJson == null)
            return;

        setSuccessful(responseJson.getBoolean(("isSuccessful")));
        setResponseMessage(responseJson.getString(("responseMessage")));
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}