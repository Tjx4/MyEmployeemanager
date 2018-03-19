package za.co.tangentsolutions.myemployeemanager.presenters;

import android.os.Bundle;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.LoginActivity;
import za.co.tangentsolutions.myemployeemanager.contracts.LoginPresenterContract;
import za.co.tangentsolutions.myemployeemanager.models.LoginModel;
import za.co.tangentsolutions.myemployeemanager.models.LoginObject;
import za.co.tangentsolutions.myemployeemanager.models.UserModel;
import za.co.tangentsolutions.myemployeemanager.providers.HttpConnectionProvider;
import za.co.tangentsolutions.myemployeemanager.providers.RestServiceProvider;
import za.co.tangentsolutions.myemployeemanager.providers.UserClient;
import za.co.tangentsolutions.myemployeemanager.views.LoginView;

public class LoginPresenter extends BaseAsyncPresenter implements LoginPresenterContract {

    private LoginModel loginModel;
    private UserModel userModel;
    private LoginView loginView;
    private String username;
    private String password;

    public LoginPresenter(LoginActivity loginActivity) {
        super(loginActivity);
        this.loginView = loginActivity;
        setUserClient();
    }

    public void handleOnLoginButtonClicked(View view){
        clickedView = view;
        username = loginView.getUsername();
        password = loginView.getPassword();

        if(!username.isEmpty()){
            if(!password.isEmpty()){
                //new DoAsyncCall(0, view).execute();
                makeLoginCall();
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

        return new HttpConnectionProvider(payload).makeCallForData(url, "POST", true, true, httpConTimeout,this, false);

    }

    @Override
    public String makeUserDetailsHttpCall() throws IOException {
        String service = RestServiceProvider.userDetails.getPath();
        String url = currentenvironment + service;

        return new HttpConnectionProvider().makeCallForData(url, "GET", true, true, httpConTimeout,this, true);
     }


    public void makeLoginCall(){
        loginView.showLoadingDialog("Loging in");

        LoginObject loginObject = new LoginObject(username, password);
        UserClient userClien = getUserClient();
        Call<LoginModel> call1 = userClien.loginUser(loginObject);
        call1.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.isSuccessful()){
                    LoginModel loginModel = response.body();
                    String token = loginModel.getToken();
                    setToken(token);
                    makeUserDetailsCall();
                }
                else{
                    //Do somethinig when failed
                    loginView.hideLoadingDialog();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }


    @Override
    public void makeUserDetailsCall(){
        UserClient userClien = getUserClient();
        String token = getToken();
        Call<UserModel> call = userClien.getUser(token);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    cacheProvider.cacheUser(userModel);
                    loginView.startEmployeesDashboardActivity();
                    loginView.hideLoadingDialog();
                }
                else{
                    loginView.showHttpCallError(activity.getString((R.string.login_error_message)));
                    loginView.hideLoadingDialog();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                loginView.showHttpCallError(activity.getString((R.string.connection_error_message)));
            }
        });

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
                    loginModel.setSuccessful(false);
                    new DoAsyncCall(1).execute();
                break;
                case 1:
                     cacheProvider.cacheUser(userModel);
                     loginView.startEmployeesDashboardActivity();
                 break;
            }

        }
        else {
            loginView.showHttpCallError(activity.getString((R.string.login_error_message)));
        }

        super.afterAsyncCall(actionIndex);
    }
}