package za.co.tangentsolutions.myemployeemanager.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.presenters.BasePresenter;
import za.co.tangentsolutions.myemployeemanager.providers.DialogFragmentProvider;
import za.co.tangentsolutions.myemployeemanager.views.BaseView;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected BasePresenter presenter;
    protected DialogFragmentProvider dialogFragment;
    protected boolean isInView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getClass() == EmployeesDashBoardActivity.class) {
            slideInActivity();
        } else {
            slideOutActivity();
        }
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }


    public void slideInActivity() {
        overridePendingTransition(R.anim.slide_right, R.anim.nothing);

    }

    public void slideOutActivity() {
        overridePendingTransition(R.anim.slide_right, R.anim.nothing);
    }

    public void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected AlertDialog.Builder setupBasicMessage(String title, String message, String posiTiveButtonText, boolean showNagativeButton, boolean showNutralButton) {

        AlertDialog.Builder ab = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        ab.setMessage(message)
                .setTitle(title)
                .setPositiveButton(posiTiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onPositiveDialogButtonClicked(dialogInterface, i);
                    }
                });

        if (showNagativeButton) {
            ab.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onNagativeButtonClicked(dialogInterface, i);
                }
            });
        }

        return ab;
    }

    protected void onPositiveDialogButtonClicked(DialogInterface dialogInterface, int i){

    }

    protected void onNagativeButtonClicked(DialogInterface dialogInterface, int i){

    }

    private void showAlertMessage(AlertDialog.Builder ab) {
        AlertDialog a = ab.create();
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.show();

        a.getButton(a.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.textWhite));
        a.getButton(a.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.textWhite));
        a.getButton(a.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.textWhite));
    }

    protected void showAlertDialogSuccessMessage(String title, String message, String...buttonText) {
        String posiTiveButtonText = getResources().getString(R.string.ok);

        if (buttonText != null && buttonText.length > 0)
            posiTiveButtonText = buttonText[0];

        message = (message != null && !message.isEmpty()) ? message : getString(R.string.generic_success_message);
        AlertDialog.Builder ab = setupBasicMessage(message, title, posiTiveButtonText,false, false);
        ab.setIcon(R.drawable.success_icon);
        ab.setCancelable(false);
        showAlertMessage(ab);
    }

    public void showAlertDialogErrorMessage(String title, String message, String... buttonText) {
        String posiTiveButtonText = getResources().getString(R.string.ok);

        if (buttonText != null && buttonText.length > 0)
            posiTiveButtonText = buttonText[0];

        message = (message != null && !message.isEmpty()) ? message : getResources().getString(R.string.generic_error_message);
        AlertDialog.Builder ab = setupBasicMessage(title, message, posiTiveButtonText, false, false);
        ab.setIcon(R.drawable.error_icon);
        showAlertMessage(ab);
    }

    protected void showAlertDialogConfirmMessage(String message, String title, boolean showNagativeButton, boolean showNutralButton) {
        String posiTiveButtonText = getResources().getString(R.string.yes);
        AlertDialog.Builder ab = setupBasicMessage(message, title, posiTiveButtonText, showNagativeButton, showNutralButton);
        ab.setIcon(R.drawable.confirm_icon);
        showAlertMessage(ab);
    }

    protected void goToActivityWithPayload(Class activity, Bundle... payload) {
        Intent intent = new Intent(this, activity);

        if (payload != null && payload.length > 0)
            intent.putExtra(Constants.PAYLOAD_KEY, payload[0]);

        startActivity(intent);
    }

    protected void goToEmployeeStats(Bundle...payload) {
        goToActivityWithPayload(EmloyeeStatsActivity.class, payload);
    }

    protected void goToProfile(Bundle...payload) {
        goToActivityWithPayload(EmployeeProfileActivity.class, payload);
    }

    protected void goToLogin(Bundle...payload) {
        goToActivityWithPayload(LoginActivity.class, payload);
    }

    protected void goToEmployeesDashboard(Bundle... payload) {
        goToActivityWithPayload(EmployeesDashBoardActivity.class, payload);
    }

    protected void showFragmentDialog(String title, int Layout, DialogFragmentProvider newFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle payload = new Bundle();
        payload.putString(Constants.TITLE, title);
        payload.putInt(Constants.LAYOUT, Layout);

        newFragment.setArguments(payload);
        newFragment.show(ft, "dialog");
        dialogFragment = newFragment;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(dialogFragment != null)
            dialogFragment.dismiss();

        //Dismiss tasks
    }

    protected void clearAllCache(){
        // Clear all cache
    }

    public void hidePhoneKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    protected long imageAnimationDuration = 300;

    public void fadeInOverlay(View view) {
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1.0f).setDuration(imageAnimationDuration);
        isInView = true;
    }

    protected void fadeOutOverlay(View view) {
        isInView = false;
        final View ofverlay = view;

        ofverlay.animate()
                .alpha(0.0f)
                .setDuration(imageAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(isInView)
                            return;

                        ofverlay.setVisibility(View.GONE);
                    }
                });
    }
}