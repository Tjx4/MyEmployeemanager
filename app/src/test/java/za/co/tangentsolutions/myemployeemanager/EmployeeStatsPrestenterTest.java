package za.co.tangentsolutions.myemployeemanager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.tangentsolutions.myemployeemanager.activities.EmloyeeStatsActivity;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeeStatsPrestenter;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeStatsView;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeStatsPrestenterTest {

    private EmployeeStatsPrestenter employeeStatsPrestenter;
    @Mock
    private EmloyeeStatsActivity emloyeeStatsActivity;

    @Mock
    private EmployeeStatsView employeeStatsView;

    @Before
    public void beforeTest() throws Exception {
        employeeStatsPrestenter = new EmployeeStatsPrestenter(emloyeeStatsActivity);
    }

    @Test
    public void shouldShowNumberOfEmployees(){

    }

    @Test
    public void shouldShowBirthDaysThisMonth(){

    }

    @Test
    public void showPositionAndEmployeesInSimilarPosition(){

    }
}