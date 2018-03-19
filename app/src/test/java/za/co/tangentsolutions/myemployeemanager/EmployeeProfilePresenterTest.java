package za.co.tangentsolutions.myemployeemanager;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.activities.EmployeeProfileActivity;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeDetailsModel;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeeProfilePresenter;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeProfileView;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeProfilePresenterTest {

    @Mock
    private EmployeeProfilePresenter employeeProfilePresenter;
    @Mock
    private EmployeeProfileView employeeProfileView;

    @Mock
    private EmployeeProfileActivity employeeProfileActivity;

    List<EmployeeDetailsModel> testEmployeeStatList;

    @Before
    public void beforeTest() throws Exception {
        employeeProfilePresenter = new EmployeeProfilePresenter();

        testEmployeeStatList = new ArrayList<>();
        testEmployeeStatList.add(new EmployeeDetailsModel("Test tag1", "Test value1"));
        testEmployeeStatList.add(new EmployeeDetailsModel("Test tag1", "Test value2"));
    }

    @Test
    public void shouldPorpulateList(){
        employeeProfileView.porpulateDetailsListView(testEmployeeStatList);
        assert (employeeProfileView.getListViewItemCount() == employeeProfilePresenter.getDetailsCount());
    }


    @Test
    public void shouldShowFullProfileIfIsCurrentUser(){
        employeeProfilePresenter.setMyprofile(true);
        int actual = employeeProfileView.getUploadPicImg().getVisibility();
        int actual2 = employeeProfileView.getTakePicImg().getVisibility();

        assert(actual == View.VISIBLE);
        assert(actual2 == View.VISIBLE);
    }

    @Test
    public void shouldShowLimitedProfileIfIsNotCurrentUser(){
        employeeProfilePresenter.setMyprofile(false);
        int actual = employeeProfileView.getUploadPicImg().getVisibility();
        int actual2 = employeeProfileView.getTakePicImg().getVisibility();

        assert(actual == View.INVISIBLE);
        assert(actual2 == View.INVISIBLE);
    }
}