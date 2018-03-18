package za.co.tangentsolutions.myemployeemanager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.tangentsolutions.myemployeemanager.activities.LoginActivity;
import za.co.tangentsolutions.myemployeemanager.presenters.LoginPresenter;
import za.co.tangentsolutions.myemployeemanager.views.LoginView;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    private LoginPresenter loginPresenter;
    @Mock
    private LoginView loginView;

    @Mock
    private LoginActivity loginActivity;

    @Before
    public void beforeTest() throws Exception {
        loginPresenter = new LoginPresenter(loginActivity);
    }

    @Test
    public void shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception {
        when(loginView.getUsername()).thenReturn("");
        loginPresenter.handleOnLoginButtonClicked(null);

        verify(loginView).showEmptyUsernameError(R.string.invalid_username_error_message);
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        when(loginView.getUsername()).thenReturn("username");
        when(loginView.getPassword()).thenReturn("");
        loginPresenter.handleOnLoginButtonClicked(null);

        verify(loginView).showEmptyPasswordError(R.string.invalid_password_error_message);
    }

    @Test
    public void shouldParseCorrectData() throws Exception {
        when(loginView.getUsername()).thenReturn("username");
        when(loginView.getPassword()).thenReturn("12345");
        loginPresenter.handleOnLoginButtonClicked(null);

        String expectedUsername = loginView.getUsername();
        String expectedPassword = loginView.getPassword();

        verify(loginPresenter).makeAuthtokeHttpCall(expectedUsername, expectedPassword);
    }

    @Test
    public void shouldMakeBackendCall() throws Exception {
        verify(loginPresenter).makeAuthtokeHttpCall(loginView.getUsername(), loginView.getPassword());

        //
    }

    @Test
    public void shouldShowErrorAlertIfLoginFailed() throws Exception {
        when(loginView.getUsername()).thenReturn("username");
        when(loginView.getPassword()).thenReturn("123");
        loginPresenter.handleOnLoginButtonClicked(null);

        verify(loginView).showHttpCallError("");
    }

    @Test
    public void shouldGoToEmployeeDashboardIfLoginSuccessful() throws Exception {
        verify(loginView).startEmployeesActivity();
    }
}