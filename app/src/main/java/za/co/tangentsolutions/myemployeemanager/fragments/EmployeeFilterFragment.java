package za.co.tangentsolutions.myemployeemanager.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.providers.DialogFragmentProvider;

public class EmployeeFilterFragment  extends DialogFragmentProvider {
    private BaseActivity baseActivity;
    private String gender;
    private EditText positionTxt, raceTxt, startDateRangeTxt, userTxt, birthDateRangeTxt, emailContainsTxt;
    private Button filterBtn;
    private RadioButton maleRdo, femaleRdo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = super.onCreateView( inflater,  container, savedInstanceState);

        femaleRdo = parentView.findViewById(R.id.rdoFemale);
        maleRdo = parentView.findViewById(R.id.rdoMale);
        positionTxt = parentView.findViewById(R.id.txtPosition);
        raceTxt = parentView.findViewById(R.id.txtRace);
        startDateRangeTxt = parentView.findViewById(R.id.txtStartDateRange);
        userTxt = parentView.findViewById(R.id.txtUser);
        birthDateRangeTxt = parentView.findViewById(R.id.txtBirthDateRange);
        emailContainsTxt = parentView.findViewById(R.id.txtEmailContains);
        filterBtn = parentView.findViewById(R.id.btnFilter);
        setViewClickEvents(new View[]{maleRdo, femaleRdo});
        return parentView;
    }

    public String getGenderFilter(){
        return (gender == null)? "" : gender;
    }

    public String getRaceFilter(){
        return raceTxt.getText().toString();
    }

    public String getPositionFilter(){
        return positionTxt.getText().toString();
    }

    public String getStartDateRangeFilter(){
        return startDateRangeTxt.getText().toString();
    }

    public String getUserFilter(){
        return userTxt.getText().toString();
    }

    public String getBirthDateRangeFilter(){
        return birthDateRangeTxt.getText().toString();
    }

    public String emailContainsFilter(){
        return emailContainsTxt.getText().toString();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) context;
    }

    @Override
    protected void onFragmentViewClickedEvent(View view) {
         gender = view.getTag().toString();
    }
}