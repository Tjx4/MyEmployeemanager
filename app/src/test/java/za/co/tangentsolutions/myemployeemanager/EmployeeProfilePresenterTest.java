package za.co.tangentsolutions.myemployeemanager;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.tangentsolutions.myemployeemanager.activities.EmployeeProfileActivity;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeeProfilePresenter;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeProfileView;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeProfilePresenterTest {

    private EmployeeProfilePresenter employeeProfilePresenter;
    @Mock
    private EmployeeProfileView employeeProfileView;

    @Mock
    private EmployeeProfileActivity employeeProfileActivity;

    @Before
    public void beforeTest() throws Exception {
        employeeProfilePresenter = new EmployeeProfilePresenter(employeeProfileActivity);
    }

    @Test
    public void shouldShowFullProfileIfIsCurrentUser(){


    }

    @Test
    public void shouldShowLimitedProfileIfIsNotCurrentUser(){

        isMyprofile
    }
}