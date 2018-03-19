package za.co.tangentsolutions.myemployeemanager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.activities.EmloyeeStatsActivity;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatModel;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeeStatsPrestenter;
import za.co.tangentsolutions.myemployeemanager.providers.MockProvider;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeStatsView;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeStatsPrestenterTest {

    private EmployeeStatsPrestenter employeeStatsPrestenter;
    @Mock
    private EmloyeeStatsActivity emloyeeStatsActivity;

    @Mock
    private EmployeeStatsView employeeStatsView;

    List<EmployeeStatModel> testEmployeeStatList;
    List<EmployeeModel> testEmployeeList;
    MockProvider mockProvider;

    @Before
    public void beforeTest() throws Exception {
        employeeStatsPrestenter = new EmployeeStatsPrestenter(emloyeeStatsActivity);
        testEmployeeStatList = new ArrayList<>();
        testEmployeeStatList.add(new EmployeeStatModel("Test Stat", "Test value1"));
        testEmployeeStatList.add(new EmployeeStatModel("Test Stat2", "Test value2"));
        testEmployeeStatList.add(new EmployeeStatModel("Test Stat3", "Test value3"));
        mockProvider = new MockProvider();

        testEmployeeList = new ArrayList<>();
        testEmployeeList.add(mockProvider.getMockMaleEmployeeWithBirthdayThisMonth());
        testEmployeeList.add(mockProvider.getMockFemaleEmployeeWithBirthdayThisMonth());
        testEmployeeList.add(mockProvider.getMockFemaleEmployeeWithBirthdayNotThisMonth());
    }

    @Test
    public void shouldShowNumberOfEmployees(){
        //Size of emplyees list should mach the size we get from the method
        String actual = ""+testEmployeeList.size();
        assert (actual.equals(employeeStatsPrestenter.getEmployeeCount(testEmployeeList)));
    }

    @Test
    public void shouldShowBirthDaysThisMonth(){
        //Mock male1 and female2 both have birth days this month
        String actual = "2";
        assert (actual.equals(employeeStatsPrestenter.getBirthDaysThisMonthCount(testEmployeeList, 10)));
    }

    @Test
    public void shouldShowFemaleEmployees(){
        //Mock female1 and female2 make 2
        String actual = "2";
        assert (actual.equals(employeeStatsPrestenter.getFemaleCount(testEmployeeList)));
    }

    @Test
    public void shouldShowMaleEmployees(){
        //Mock male1 is the only male employee
        String actual = "1";
        assert (actual.equals(employeeStatsPrestenter.getFemaleCount(testEmployeeList)));
    }

    @Test
    public void shouldPorpulateList(){
        employeeStatsView.porpulateStatsListView(testEmployeeStatList);
        assert (employeeStatsView.getListViewItemCount() == employeeStatsPrestenter.getStatsCount());
    }
}