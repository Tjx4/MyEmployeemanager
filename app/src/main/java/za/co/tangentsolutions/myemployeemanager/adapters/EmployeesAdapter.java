package za.co.tangentsolutions.myemployeemanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.providers.GlideProvider;
import za.co.tangentsolutions.myemployeemanager.R;

public class EmployeesAdapter extends ArrayAdapter {

    private final List<EmployeeModel> employeeList;
    private final int layout, currentUserId;
    private final BaseActivity activity;
    private final GlideProvider glideProvider;

    public EmployeesAdapter(BaseActivity activity, int layout, List<EmployeeModel> employeeList, int currentUserId) {
        super(activity, layout, employeeList);
        this.employeeList = employeeList;
        this.layout = layout;
        this.activity = activity;
        this.currentUserId = currentUserId;
        glideProvider = new GlideProvider(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View parentView = layoutInflater.inflate(layout, parent, false);

       /*
       //Skip if is current user
        if(currentUserId == employeeList.get(position).getUser().getId()) {
            parentView.setVisibility(View.GONE);
            return parentView;
        }
        */

        String firstName = employeeList.get(position).getUser().getFirst_name();
        String sirName = employeeList.get(position).getUser().getLast_name();
        String fullName = firstName+" "+sirName;

        TextView employeeNameTxt = parentView.findViewById(R.id.txtEmployeeName);
        employeeNameTxt.setText(fullName);

        ImageView emplyeeProfilePic = parentView.findViewById(R.id.imgProfPic);
        String picUrl = "";
        glideProvider.loadImageFromInternet(picUrl, emplyeeProfilePic, R.mipmap.user_icon);

        return parentView;
    }
}