package za.co.tangentsolutions.myemployeemanager.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;
import za.co.tangentsolutions.myemployeemanager.providers.GlideProvider;
import za.co.tangentsolutions.myemployeemanager.R;

public class EmployeesFilterAdapter extends ArrayAdapter {

    private final List<EmployeeFilterModel> filters;
    private final int layout;
    private final Activity activity;
    private final GlideProvider glideProvider;

    public EmployeesFilterAdapter(Activity activity, int layout, List<EmployeeFilterModel> filters) {
        super(activity, layout, filters);
        this.filters = filters;
        this.layout = layout;
        this.activity = activity;
        glideProvider = new GlideProvider(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View parentView = layoutInflater.inflate(layout, parent, false);

        ImageView filterIcon = parentView.findViewById(R.id.imgIcon);
        glideProvider.loadImageFromResourceId(filters.get(position).getIcon(), filterIcon, R.drawable.add_icon);

        TextView fiterNameTxt = parentView.findViewById(R.id.txtFilterType);
        fiterNameTxt.setText(filters.get(position).getDisplayText());
        return parentView;
    }
}