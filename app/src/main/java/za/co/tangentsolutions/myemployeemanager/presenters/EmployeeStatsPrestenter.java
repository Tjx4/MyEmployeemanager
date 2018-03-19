package za.co.tangentsolutions.myemployeemanager.presenters;

import java.util.List;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatModel;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatsListModel;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeProfileView;

public class EmployeeStatsPrestenter extends BaseChildPresenter{
    private EmployeeProfileView employeeProfileView;
    private List<EmployeeStatModel> employeeDetailsList;
    private EmployeeModel currentEmplyee;
    private EmployeeStatsListModel employeeStatsListModel;

    public EmployeeStatsPrestenter(BaseActivity activity) {
        super(activity);
        employeeProfileView = (EmployeeProfileView)activity;
    }
}
