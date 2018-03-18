package za.co.tangentsolutions.myemployeemanager.providers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeListModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.UserModel;

public class CacheProvider {
    private SharedPreferences sharedPreferences;
    private UserModel userModel;
    private EmployeeListModel employeeListModel;
    private static final String TOKEN = "token";
    private static final String EMPLOYEELIST = "employeelist";
    private static final String EMPLOYEE = "employee";
    private static final String USER = "user";

    public CacheProvider(BaseActivity activity) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    public void cacheToken(String token){
        if(token.isEmpty())
            return;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getCachedToken() {
        String token = sharedPreferences.getString(TOKEN, "");

        if (token == null)
            token = "";

        return token;
    }

    public void cacheEmployeeList(EmployeeListModel employeeListModel){
        if(employeeListModel == null)
            return;

        this.employeeListModel = employeeListModel;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String connectionsJSONString = new Gson().toJson(employeeListModel);
        editor.putString(EMPLOYEELIST, connectionsJSONString);
        editor.commit();
    }

    public EmployeeListModel getCachedEmployeeList() {
        try {
            String js = sharedPreferences.getString(EMPLOYEELIST, "");
            JSONObject userJson = new JSONObject(js);
            JSONArray userJsonArray = userJson.getJSONArray("employee");

            EmployeeListModel employeeListModel = new EmployeeListModel();
            employeeListModel.setModel(userJsonArray);

            return employeeListModel;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EmployeeModel getCachedEmployee() {
        try {
            String js = sharedPreferences.getString(EMPLOYEE, "");
            JSONObject userJson = new JSONObject(js);
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setModel(userJson);

            return employeeModel;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cacheUser(UserModel userModel){
        if(userModel == null)
            return;

        this.userModel = userModel;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String connectionsJSONString = new Gson().toJson(userModel);
        editor.putString(USER, connectionsJSONString);
        editor.commit();
    }

    public UserModel getCachedUser(){
        try {
            String js = sharedPreferences.getString(USER, "");
            JSONObject userJson = new JSONObject(js);

            UserModel userModel = new UserModel();
            userModel.setModel(userJson);

            return userModel;
        }
        catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}