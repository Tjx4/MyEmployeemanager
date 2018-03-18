package za.co.tangentsolutions.myemployeemanager.views;

import android.view.View;

import java.util.List;

import za.co.geartronix.proto_app.Models.EmployeeFilterModel;
import za.co.geartronix.proto_app.Models.EmployeeModel;
import za.co.geartronix.proto_app.Presenters.EmployeesDashboardPresenter;

public interface EmployeesDashBoardView extends BaseAsyncView {
    void porpulateEmployeesList(List<EmployeeModel> employeesList, int currentUserId);
    void porpulateBasicFilterSpinner(List<EmployeeFilterModel> basicFilters);
    EmployeesDashboardPresenter getPresenter();
    void toggleFilterSpinner(View view);
}
