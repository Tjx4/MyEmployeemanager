package za.co.tangentsolutions.myemployeemanager.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.providers.DialogFragmentProvider;

public class EmployeeFilterFragment  extends DialogFragmentProvider {
    private BaseActivity baseActivity;
    private String gender;
    private EditText positionTxt, startDateRangeTxt, userTxt, birthDateRangeTxt, emailContainsTxt;
    private Button filterBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = super.onCreateView( inflater,  container, savedInstanceState);

        positionTxt = parentView.findViewById(R.id.txtPosition);
        startDateRangeTxt = parentView.findViewById(R.id.txtStartDateRange);
        userTxt = parentView.findViewById(R.id.txtUser);
        birthDateRangeTxt = parentView.findViewById(R.id.txtBirthDateRange);
        emailContainsTxt = parentView.findViewById(R.id.txtEmailContains);
        filterBtn = parentView.findViewById(R.id.btnFilter);

        setViewClickEvents(new View[]{filterBtn});
        return parentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) context;
    }

    public void onGenderSelected(View view) {
        gender = view.getTag().toString();
    }

    @Override
    protected void onFragmentViewClickedEvent(View view) {
        baseActivity.showShortToast("Click works");
    }
}