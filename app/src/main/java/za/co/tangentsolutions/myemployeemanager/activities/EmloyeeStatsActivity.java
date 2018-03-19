package za.co.tangentsolutions.myemployeemanager.activities;

import android.os.Bundle;
import android.widget.ListView;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.adapters.EmployeeStatsAdapter;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatModel;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeeStatsPrestenter;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeStatsView;

public class EmloyeeStatsActivity extends BaseChildActivity implements EmployeeStatsView {

    private ListView employeeStatLstV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emloyee_stats);
        currentActionBar.setTitle(R.string.employee_stats);
        employeeStatLstV = findViewById(R.id.lstVEmployeeStats);
        setPresenter(new EmployeeStatsPrestenter(this));
    }

    @Override
    public void porpulateStatsListView(List<EmployeeStatModel> employeeDetailsList) {
        EmployeeStatsAdapter employeeStatAdapter = new EmployeeStatsAdapter(this, R.layout.employee_stat_view, employeeDetailsList);

        employeeStatLstV.setAdapter(employeeStatAdapter);
    }

    @Override
    public int getListViewItemCount() {
        return employeeStatLstV.getCount();
    }
}