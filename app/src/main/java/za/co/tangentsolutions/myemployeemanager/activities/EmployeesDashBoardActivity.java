package za.co.tangentsolutions.myemployeemanager.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.adapters.EmployeesAdapter;
import za.co.tangentsolutions.myemployeemanager.adapters.EmployeesFilterAdapter;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeesDashboardPresenter;
import za.co.tangentsolutions.myemployeemanager.providers.EmployeeProfileProvider;
import za.co.tangentsolutions.myemployeemanager.views.EmployeesDashBoardView;

public class EmployeesDashBoardActivity extends BaseSlideMenuActivity implements EmployeesDashBoardView {
    private ListView employeesListView, filtersSpnr;
    private ImageView dropArraw;
    private TextView filterTitleTxt;
    private EmployeeFilterFragment filterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_dash_board);
        setSlideMenuDependencies(this, "Employee list", R.layout.content_employees_dash_board_dashboard);
        parentLayout = getMainLayout();
        employeesListView = findViewById(R.id.lstEmployeeList);
        filtersSpnr = findViewById(R.id.lstFilters);
        dropArraw = findViewById(R.id.imgDropArraw);
        filterTitleTxt = findViewById(R.id.txtFilterTitle);
        setPresenter(new EmployeesDashboardPresenter(this));
    }

    @Override
    public EmployeeFilterFragment getFilterFragment() {
        return filterFragment;
    }

    @Override
    public void porpulateBasicFilterSpinner(List<EmployeeFilterModel> filters) {
        final List<EmployeeFilterModel>basicFilters = filters;

        EmployeesFilterAdapter employeesAdapter = new EmployeesFilterAdapter(this, R.layout.employee_filter_item, basicFilters);
        filtersSpnr.setAdapter(employeesAdapter);
        filtersSpnr.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == basicFilters.size() - 1){
                    setFilterTitle(getString(R.string.showing_all));
                    getPresenter().showAllEmployees();
                }
                else if(position == basicFilters.size() - 2){
                    openEmployeeFilterDialog();
                }
                else{
                    getPresenter().handleOnFilterSpinnerClicked(basicFilters.get(position));
                }

                toggleFilterSpinner(view);
            }
        });
    }

    @Override
    public void toggleFilterSpinner(View view) {
        if (filtersSpnr.getVisibility() == View.GONE) {
            filtersSpnr.setVisibility(View.VISIBLE);
            dropArraw.setImageResource(R.drawable.drop_up_icon);
        } else {
            filtersSpnr.setVisibility(View.GONE);
            dropArraw.setImageResource(R.drawable.drop_down_icon);
        }
    }

    @Override
    public void setFilterTitle(String titleRes) {
        filterTitleTxt.setText(titleRes);
    }

    @Override
    public void onFilterButtonClicked(View view) {
        if(filterFragment != null)
            filterFragment.dismiss();

        getPresenter().setCustomFilters(filterFragment);
    }

    @Override
    public void showEmptyFilterWarnigToast(int warningStringRes) {
        String warningMessage = getString(warningStringRes);
        showShortToast(warningMessage);
    }

    @Override
    public void openEmployeeFilterDialog() {
        filterFragment = new EmployeeFilterFragment();
        showFragmentDialog(getString(R.string.filter_employees), R.layout.fragment_employee_filter, filterFragment);
    }

    @Override
    public void porpulateEmployeesList(List<EmployeeModel> employeesList, int currentUserId){
        EmployeesAdapter employeesAdapter = new EmployeesAdapter(this, R.layout.employee_view, employeesList, currentUserId);
        employeesListView.setAdapter(employeesAdapter);

        final Activity activity = this;
        final List<EmployeeModel> EMPLOYEELIST = employeesList;

        employeesListView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle selectedEmployee = new EmployeeProfileProvider().getEmployeeProfile(EMPLOYEELIST.get(position));
                goToProfile(selectedEmployee);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        super.onNavigationItemSelected(item);
        return true;
    }

    @Override
    public EmployeesDashboardPresenter getPresenter() {
        return (EmployeesDashboardPresenter)presenter;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}