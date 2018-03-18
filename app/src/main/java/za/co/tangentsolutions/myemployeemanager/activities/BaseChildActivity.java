package za.co.tangentsolutions.myemployeemanager.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import za.co.tangentsolutions.myemployeemanager.presenters.BasePresenter;

public abstract class BaseChildActivity extends BaseAsyncActivity {

    protected ActionBar currentActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActionBar = getSupportActionBar();
        currentActionBar.setDisplayUseLogoEnabled(false);
        currentActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }
}