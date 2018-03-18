package za.co.tangentsolutions.myemployeemanager.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.presenters.LoginPresenter;
import za.co.tangentsolutions.myemployeemanager.views.LoginView;

public class LoginActivity extends BaseAsyncActivity implements LoginView {
    private EditText usernameTxt, passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = findViewById(R.id.txtUsername);
        passwordTxt = findViewById(R.id.txtPassword);
        usernameTxt.setText("pravin.gordhan");
        passwordTxt.setText("pravin.gordhan");

        setPresenter(new LoginPresenter(this));
    }

    public void onLoginButtonClicked(View view) {
        getPresenter().handleOnLoginButtonClicked(view);
    }

    @Override
    public LoginPresenter getPresenter() {
        return (LoginPresenter)presenter;
    }

    @Override
    public String getUsername() {
        return usernameTxt.getText().toString();
    }

    @Override
    public String getPassword() {
        return  passwordTxt.getText().toString();
    }

    @Override
    public void showLoginError(String errorMessage) {
        showAlertDialogErrorMessage(errorMessage, "Error");
    }

    @Override
    public void showEmptyUsernameError(int error_msg_resource) {
        showShortToast(getString(error_msg_resource));
    }

    @Override
    public void showEmptyPasswordError(int error_msg_resource) {
        showShortToast(getString(error_msg_resource));
    }

    @Override
    public void startEmployeesActivity() {
        goToEmployeesDashboard();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}