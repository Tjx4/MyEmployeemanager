package za.co.tangentsolutions.myemployeemanager.presenters;

import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;

public abstract class BaseSlideMenuPresenter extends BaseAsyncPresenter {
    public BaseSlideMenuPresenter( ) {
        super();
    }
    public BaseSlideMenuPresenter(BaseActivity activity) {
        super(activity);
    }
}