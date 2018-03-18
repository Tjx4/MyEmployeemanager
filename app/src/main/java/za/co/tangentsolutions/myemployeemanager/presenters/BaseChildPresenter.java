package za.co.tangentsolutions.myemployeemanager.presenters;

import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;

public abstract class BaseChildPresenter extends BaseAsyncPresenter {
    public BaseChildPresenter(BaseActivity activity) {
        super(activity);
    }
}
