package za.co.tangentsolutions.myemployeemanager.contracts;

import org.json.JSONException;
import java.io.IOException;

public interface LoginPresenterContract extends BasePresenterContract {
    String makeAuthtokeHttpCall(String username, String password) throws IOException;
    String makeUserDetailsHttpCall() throws IOException;
    String getAuthToken()throws IOException, JSONException;
    String getUserDetails()throws IOException, JSONException;
}