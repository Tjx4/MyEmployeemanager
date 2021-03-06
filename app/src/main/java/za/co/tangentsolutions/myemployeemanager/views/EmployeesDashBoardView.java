package za.co.tangentsolutions.myemployeemanager.views;

import android.view.View;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeesDashboardPresenter;

public interface EmployeesDashBoardView extends BaseAsyncView {
    void porpulateEmployeesList(List<EmployeeModel> employeesList, int currentUserId);
    void porpulateBasicFilterListView(List<EmployeeFilterModel> basicFilters);
    EmployeesDashboardPresenter getPresenter();
    void toggleFilterSpinner(View view);
    void openEmployeeFilterDialog();
    void onFilterButtonClicked(View view);
    void showEmptyFilterWarnigToast(int warningStringRes);
    EmployeeFilterFragment getFilterFragment();
    void setFilterTitle(String title);
    int getFilterListVisibility();
}
