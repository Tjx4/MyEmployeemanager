package za.co.tangentsolutions.myemployeemanager.presenters;

import android.os.Bundle;

import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.providers.CacheProvider;

public abstract class BasePresenter {
    protected BaseActivity activity;
    public CacheProvider cacheProvider;
    public abstract void handleBackButtonPressed();

    public BasePresenter() {

    }

    public BasePresenter(BaseActivity activity) {
        this.activity = activity;
        cacheProvider = new CacheProvider(activity);
    }
}