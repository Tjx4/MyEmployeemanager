package za.co.tangentsolutions.myemployeemanager;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.tangentsolutions.myemployeemanager.activities.EmployeesDashBoardActivity;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeesDashboardPresenter;
import za.co.tangentsolutions.myemployeemanager.views.EmployeesDashBoardView;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        employeesDashboardPresenter = new EmployeesDashboardPresenter();
    }

    @Test
    public void shouldToggleFilterList(){
        employeesDashBoardView.toggleFilterSpinner(null);
        int actual = employeesDashBoardView.getFilterListVisibility();
        assert (actual == View.VISIBLE);

        employeesDashBoardView.toggleFilterSpinner(null);
        actual = employeesDashBoardView.getFilterListVisibility();
        assert (actual == View.GONE);
    }

    @Test
    public void shouldShorWarningToastIfFiltersAreEmpty(){
        verify(employeesDashBoardView).showEmptyFilterWarnigToast(R.string.no_filter_warning_string);
    }

    @Test
    public void shouldShowFilteredList(){
        when(employeesDashBoardView.getFilterFragment().getGenderFilter()).thenReturn("");
        employeesDashBoardView.onFilterButtonClicked(null);

        verify(employeesDashBoardView).showEmptyFilterWarnigToast(R.string.no_filter_warning_string);
    }

    @Test
    public void shouldShowSetFilterTitleToOnlyWomen(){
        when(employeesDashBoardView.getFilterFragment().getGenderFilter()).thenReturn("F");
        employeesDashBoardView.onFilterButtonClicked(null);

        verify(employeesDashBoardView).setFilterTitle(employeesDashboardActivity.getString(R.string.women_only_custom_filter_display));
    }

    @Test
    public void shouldShowSetFilterTitleToOnlyMen(){
        when(employeesDashBoardView.getFilterFragment().getGenderFilter()).thenReturn("M");
        employeesDashBoardView.onFilterButtonClicked(null);

        verify(employeesDashBoardView).setFilterTitle(employeesDashboardActivity.getString(R.string.men_only_custom_filter_display));
    }

    @Test
    public void shouldShowSetFilterTitleToOnlyThisMonth(){
        when(employeesDashBoardView.getFilterFragment().getStartDateRangeFilter()).thenReturn("1");
        employeesDashBoardView.onFilterButtonClicked(null);

        verify(employeesDashBoardView).setFilterTitle(employeesDashboardActivity.getString(R.string.this_month_custom_filter_display));
    }
}