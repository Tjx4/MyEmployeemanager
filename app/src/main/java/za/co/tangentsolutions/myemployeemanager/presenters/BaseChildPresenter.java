package za.co.tangentsolutions.myemployeemanager.presenters;

import za.co.geartronix.proto_app.Activities.BaseActivity;

public abstract class BaseChildPresenter extends BaseAsyncPresenter {
    public BaseChildPresenter(BaseActivity activity) {
        super(activity);
    }
}
