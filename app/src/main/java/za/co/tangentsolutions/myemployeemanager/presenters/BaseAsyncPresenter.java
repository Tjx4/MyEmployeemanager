package za.co.tangentsolutions.myemployeemanager.presenters;

import android.os.AsyncTask;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.providers.UserClient;

public abstract class BaseAsyncPresenter extends BasePresenter{

    public BaseAsyncPresenter(){
        super();
    }

    protected int httpConTimeout = 7000;
    protected String currentenvironment = Constants.CURRENTENVIRONMENT;
    private String token;
    protected int asyncAction;
    public List<AsyncTask> myTasks;
    protected List<View> activeViews;
    protected View clickedView;
    protected boolean isFromServer, isCheckingUpdates;
    private UserClient userClient;

    public BaseAsyncPresenter(BaseActivity activity) {
        super(activity);
        this.activity = activity;
        setToken(cacheProvider.getCachedToken());
        myTasks = new ArrayList<>();
        activeViews = new ArrayList<>();
        setUserClient();
    }

    public String getToken() {
        return "Token "+token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    protected void resetButtonState(View view) {
        activeViews.remove(view);
    }
    protected void resetIfTriggeredByView(View triggerView) {
        if(triggerView != null)
            resetButtonState(triggerView);
    }

    public boolean handleNavigationItemSelected(MenuItem item) {
        killAllTasks();
        return false;
    }
    protected boolean isCached() {
        return false;
    }

    @Override
    public void handleBackButtonPressed(){
        killAllTasks();
    }

    protected void killAllTasks(){
        for(AsyncTask currentTask : myTasks){
            currentTask.cancel(true);
        }
    }

    protected void checkAndUpdate() {
        isCheckingUpdates = true;
    }


    public void setUserClient(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(currentenvironment)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        userClient = retrofit.create(UserClient.class);
    }

    public UserClient getUserClient(){
        return userClient;
    }
}