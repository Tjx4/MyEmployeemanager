package za.co.tangentsolutions.myemployeemanager.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeStatModel;

public class EmployeeStatsAdapter  extends ArrayAdapter {

    private final List<EmployeeStatModel> employeeDetails;
    private final int layout;
    private final Activity activity;

    public EmployeeStatsAdapter(Activity activity, int layout, List<EmployeeStatModel> employeeDetails) {
        super(activity, layout, employeeDetails);
        this.employeeDetails = employeeDetails;
        this.layout = layout;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View parentView = layoutInflater.inflate(layout, parent, false);

        TextView tagTxt = parentView.findViewById(R.id.lblTag);
        tagTxt.setText(employeeDetails.get(position).getKey());

        TextView valueTxt = parentView.findViewById(R.id.lblValue);
        valueTxt.setText(employeeDetails.get(position).getValue());

        return parentView;
    }
}
