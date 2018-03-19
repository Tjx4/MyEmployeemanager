package za.co.tangentsolutions.myemployeemanager.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.adapters.EmployeeDetailsAdapter;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeDetailsModel;
import za.co.tangentsolutions.myemployeemanager.presenters.EmployeeProfilePresenter;
import za.co.tangentsolutions.myemployeemanager.views.EmployeeProfileView;

public class EmployeeProfileActivity extends BaseChildActivity implements EmployeeProfileView {
    private ListView employeeInfoLstV;
    private ImageView uploadPicImg, takePicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);
        currentActionBar.setTitle("Employee details");
        uploadPicImg = findViewById(R.id.imgTakePic);
        takePicImg = findViewById(R.id.imgUploadPic);
        setPresenter(new EmployeeProfilePresenter(this));
    }

    @Override
    public ImageView getUploadPicImg() {
        return uploadPicImg;
    }

    @Override
    public ImageView getTakePicImg() {
        return takePicImg;
    }

    @Override
    public void showAdminTools() {
        uploadPicImg.setVisibility(View.VISIBLE);
        takePicImg.setVisibility(View.VISIBLE);
    }

    @Override
    public Bundle getPayloadBundle() {
        return getIntent().getBundleExtra(Constants.PAYLOAD_KEY);
    }

    @Override
    public void porpulateDetailsListView(List<EmployeeDetailsModel> employeeDetailsList) {
        EmployeeDetailsAdapter employeeDetailsAdapter = new EmployeeDetailsAdapter(this, R.layout.employee_details_view, employeeDetailsList);

        employeeInfoLstV = findViewById(R.id.lstvEmployeeInfo);
        employeeInfoLstV.setAdapter(employeeDetailsAdapter);
    }
    @Override
    public int getListViewItemCount() {
        return employeeInfoLstV.getCount();
    }
}