package za.co.tangentsolutions.myemployeemanager.contracts;

import org.json.JSONException;
import java.io.IOException;

public interface LoginPresenterContract extends BasePresenterContract {
    String getAuthToken()throws IOException, JSONException;
}