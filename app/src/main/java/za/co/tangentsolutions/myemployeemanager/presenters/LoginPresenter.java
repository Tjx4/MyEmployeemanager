package za.co.tangentsolutions.myemployeemanager.presenters;

import android.view.View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.LoginActivity;
import za.co.tangentsolutions.myemployeemanager.contracts.LoginPresenterContract;
import za.co.tangentsolutions.myemployeemanager.models.LoginModel;
import za.co.tangentsolutions.myemployeemanager.models.LoginObject;
import za.co.tangentsolutions.myemployeemanager.models.UserModel;
import za.co.tangentsolutions.myemployeemanager.providers.UserClient;
import za.co.tangentsolutions.myemployeemanager.views.LoginView;

public class LoginPresenter extends BaseAsyncPresenter implements LoginPresenterContract {

    private LoginView loginView;
    private String username;
    private String password;

    public LoginPresenter() {
        super();
    }

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
                LoginUser();
            }
            else{
                loginView.showEmptyPasswordError(R.string.invalid_password_error_message);
            }
        }
        else{
            loginView.showEmptyUsernameError(R.string.invalid_username_error_message);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void LoginUser(){
        loginView.showLoadingDialog(activity.getString(R.string.logging_in));

        LoginObject loginObject = new LoginObject(username, password);
        UserClient userClien = getUserClient();
        Call<LoginModel> call1 = userClien.loginUser(loginObject);
        call1.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.isSuccessful()){
                    LoginModel loginModel = response.body();
                    String token = loginModel.getToken();
                    cacheProvider.cacheToken(token);
                    setToken(token);
                    makeUserDetailsCall();
                }
                else{
                    loginView.showHttpCallError(activity.getString(R.string.login_error_message));
                }

                loginView.hideLoadingDialog();
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void makeUserDetailsCall(){
        UserClient userClien = getUserClient();
        Call<UserModel> call = userClien.getUser(getToken());

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    UserModel userModel = response.body();
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
}