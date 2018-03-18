package za.co.tangentsolutions.myemployeemanager.activities;

import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.fragments.LoadingSpinnerFragment;
import za.co.tangentsolutions.myemployeemanager.views.BaseAsyncView;

public abstract class BaseAsyncActivity extends BaseActivity implements BaseAsyncView {

    public void showLoadingDialogFragment(String loadingMessage) {
        showFragmentDialog(loadingMessage, R.layout.fragment_loading_spinner, new LoadingSpinnerFragment());
    }

    public void hideLoadingDialogFragment() {
        if(dialogFragment != null)
            dialogFragment.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.handleBackButtonPressed();
    }

    @Override
    public void showLoadingDialog(String loadingMessage) {
        showLoadingDialogFragment(loadingMessage);
    }

    @Override
    public void hideLoadingDialog() {
        hideLoadingDialogFragment();
    }

    @Override
    public void showHttpCallError(String errorMessage) {

        if(errorMessage.isEmpty())
            errorMessage = getString(R.string.generic_error_message);

        showAlertDialogErrorMessage(getString(R.string.error_alert_title), errorMessage);
    }
}