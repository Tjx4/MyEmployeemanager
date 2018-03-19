package za.co.tangentsolutions.myemployeemanager.views;

import android.os.Bundle;
import android.widget.ImageView;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeDetailsModel;

public interface EmployeeProfileView extends BaseAsyncView {
    ImageView getUploadPicImg();
    ImageView getTakePicImg();
    Bundle getPayloadBundle();
    void showAdminTools();
    void showEmployeeDetails(List<EmployeeDetailsModel> employeeDetailsList);
    int getListViewItemCount();
}