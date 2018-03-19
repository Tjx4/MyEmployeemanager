package za.co.tangentsolutions.myemployeemanager.views;

import za.co.tangentsolutions.myemployeemanager.presenters.LoginPresenter;

public interface LoginView extends BaseAsyncView {
    LoginPresenter getPresenter();
    String getUsername();
    String getPassword();
    void showLoginError(String errorMessage);
    void showEmptyUsernameError(int error_msg_resource);
    void showEmptyPasswordError(int error_msg_resource);
    void startEmployeesDashboardActivity();
}