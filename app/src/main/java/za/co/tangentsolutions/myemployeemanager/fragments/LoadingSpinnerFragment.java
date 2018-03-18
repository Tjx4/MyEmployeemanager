package za.co.tangentsolutions.myemployeemanager.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.providers.DialogFragmentProvider;

public class LoadingSpinnerFragment extends DialogFragmentProvider {
    private BaseActivity baseActivity;
    private TextView loadingTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setDimAmount(0.8f);

        View parentView = super.onCreateView( inflater,  container, savedInstanceState);
        loadingTxt = parentView.findViewById(R.id.txtLoading);

        String loadingMessage = getArguments().getString(Constants.TITLE);
        loadingTxt.setText(loadingMessage);

        return parentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) context;
    }


    @Override
    protected void onFragmentViewClickedEvent(View view) {

    }
}
