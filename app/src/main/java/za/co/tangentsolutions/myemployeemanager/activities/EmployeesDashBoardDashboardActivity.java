package za.co.tangentsolutions.myemployeemanager.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.adapters.EmployeesAdapter;
import za.co.tangentsolutions.myemployeemanager.adapters.EmployeesFilterAdapter;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeesDashboardPresenter;
import za.co.tangentsolutions.myemployeemanager.providers.EmployeeProfileProvider;
import za.co.tangentsolutions.myemployeemanager.views.EmployeesDashBoardView;

public class EmployeesDashBoardDashboardActivity extends BaseSlideMenuActivity implements EmployeesDashBoardView {
    private ListView employeesListView, filtersSpnr;
    private ImageView dropArraw;
    private TextView filterTitleTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_dashboard);
        setSlideMenuDependencies(this, "Employee list", R.layout.content_employees);
        parentLayout = getMainLayout();
        employeesListView = findViewById(R.id.lstEmployeeList);
        filtersSpnr = findViewById(R.id.lstFilters);
        dropArraw = findViewById(R.id.imgDropArraw);
        filterTitleTxt = findViewById(R.id.txtFilterTitle);
        setPresenter(new EmployeesDashboardPresenter(this));
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
                    // Open advanced filter dialog
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
}