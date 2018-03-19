package za.co.tangentsolutions.myemployeemanager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.tangentsolutions.myemployeemanager.activities.EmployeesDashBoardActivity;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeesDashboardPresenter;
import za.co.tangentsolutions.myemployeemanager.views.EmployeesDashBoardView;

@RunWith(MockitoJUnitRunner.class)
public class EmployeesDashboardPresenterTest {

    private EmployeesDashboardPresenter employeesDashboardPresenter;
    @Mock
    private EmployeesDashBoardView employeesDashBoardView;

    @Mock
    private EmployeesDashBoardActivity employeesDashboardActivity;

    @Mock
    private EmployeeFilterFragment employeeFilterFragment;

    @Before
    public void beforeTest() throws Exception {
        employeesDashboardPresenter = new EmployeesDashboardPresenter(employeesDashboardActivity);
    }

    @Test
    public void testThatEmployeeListIsPorpulatedWithRightData(){
        //verify(employeesDashBoardView).showEmptyUsernameError(R.string.invalid_username_error_message);
    }

    @Test
    public void shouldToggleFilterList(){
        //verify(employeesDashBoardView).showEmptyUsernameError(R.string.invalid_username_error_message);
    }


    @Test
    public void shouldShorWarningToastIfFiltersAreEmpty(){
        //verify(employeesDashBoardView).showEmptyUsernameError(R.string.invalid_username_error_message);
    }

    @Test
    public void shouldShowFilteredList(){
        //verify(employeesDashBoardView).showEmptyUsernameError(R.string.invalid_username_error_message);
    }
}