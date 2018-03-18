package za.co.tangentsolutions.myemployeemanager.presenters;

import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import za.co.geartronix.proto_app.Activities.LoginActivity;
import za.co.geartronix.proto_app.Models.LoginModel;
import za.co.geartronix.proto_app.Models.UserModel;
import za.co.geartronix.proto_app.Providers.HttpConnectionProvider;
import za.co.geartronix.proto_app.Providers.RestServiceProvider;
import za.co.geartronix.proto_app.R;
import za.co.geartronix.proto_app.Views.LoginView;
import za.co.geartronix.proto_app.contracts.LoginPresenterContract;

public class LoginPresenter extends BaseAsyncPresenter implements LoginPresenterContract {

    private LoginModel loginModel;
    private UserModel userModel;
    private LoginView loginView;
    private String username;
    private String password;

    public LoginPresenter(LoginActivity loginActivity) {
        super(loginActivity);
        this.loginView = loginActivity;
    }

    public void handleOnLoginButtonClicked(View view){
        clickedView = view;
        username = loginView.getUsername();
        password = loginView.getPassword();

        if(!username.isEmpty()){
            if(!password.isEmpty()){
                new DoAsyncCall(0, view).execute();
            }
            else{
                loginView.showEmptyPasswordError(R.string.invalid_password_error_message);
            }
        }
        else{
            loginView.showEmptyUsernameError(R.string.invalid_username_error_message);
        }
    }

    @Override
    public String makeAuthtokeHttpCall(String username, String password) throws IOException {
        String service = RestServiceProvider.authenticate.getPath();
        String url = currentenvironment + service;

        Bundle payload = new Bundle();
        payload.putString("username", username);
        payload.putString("password", password);

      //  return new HttpConnectionProvider(payload).makeCallForData(url, "POST", true, true, httpConTimeout,this);
        return new HttpConnectionProvider().makeOathCall(url, "POST", true, true, httpConTimeout, 0, this);
    }

    @Override
    public String makeUserDetailsHttpCall() throws IOException {
        String service = RestServiceProvider.userDetails.getPath();
        String url = currentenvironment + service;

        return new HttpConnectionProvider().makeOathCall(url, "GET", true, true, httpConTimeout, 1, this);
    }

    @Override
    public String getAuthToken() throws IOException, JSONException {
        loginModel = new LoginModel();
        String response = makeAuthtokeHttpCall(username, password);
        loginModel.setModel(new JSONObject(response));
        loginModel.setSuccessful(true);
        return response;
    }

    @Override
    public String getUserDetails() throws IOException, JSONException {
        // TODO: improve
        loginModel.setSuccessful(false);
        userModel = new UserModel();
        String response = makeUserDetailsHttpCall();
        JSONArray rootJsonArray = new JSONArray(response);
        JSONObject userJson = (JSONObject)rootJsonArray.get(0);
        userModel.setModel(userJson);
        loginModel.setSuccessful(true);
        return response;
    }

    @Override
    protected void duringAsyncCall(int actionIndex) {
        if(actionIndex == 0)
            loginView.showLoadingDialog("Loging in");
    }

    @Override
    protected Object doAsyncOperation(DoAsyncCall currentTusk, int actionIndex) throws Exception {
        super.doAsyncOperation(currentTusk, actionIndex);

        String response = null;

        switch (actionIndex){
            case 0:
                response = getAuthToken();
                break;
            case 1:
                response = getUserDetails();
                break;
         }

        return response;

    }

    @Override
    protected void afterAsyncCall(int actionIndex) {
        if(loginModel.isSuccessful()){

            switch (actionIndex){
                case 0:
                    setToken(loginModel.getToken());
                    cacheProvider.cacheToken(loginModel.getToken());
                    new DoAsyncCall(1).execute();
                break;
                case 1:
                     cacheProvider.cacheUser(userModel);
                     loginView.startEmployeesActivity();
                     //loginView.hideLoadingDialog();
                 break;
            }

        }
        else {
            loginView.showHttpCallError(activity.getString((R.string.login_error_message)));
        }

        super.afterAsyncCall(actionIndex);
    }
}