package za.co.tangentsolutions.myemployeemanager.presenters;

import android.os.AsyncTask;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import za.co.geartronix.proto_app.Activities.BaseActivity;
import za.co.geartronix.proto_app.Activities.BaseAsyncActivity;
import za.co.geartronix.proto_app.Constants.Constants;

public abstract class BaseAsyncPresenter extends BasePresenter{

    protected int httpConTimeout = 7000;
    protected String currentenvironment = Constants.CURRENTENVIRONMENT;
    private String token;
    protected int asyncAction;
    public List<AsyncTask> myTasks;
    protected List<View> activeViews;
    protected View clickedView;
    protected boolean isFromServer, isCheckingUpdates;

    public BaseAsyncPresenter(BaseActivity activity) {
        super(activity);
        this.activity = activity;
        setToken(cacheProvider.getCachedToken());
        myTasks = new ArrayList<>();
        activeViews = new ArrayList<>();
    }

    public String getToken() {
        return token;
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

    protected void beforeAsyncCall(int actionIndex){
        activity.hidePhoneKeyboard();
    }

    protected void duringAsyncCall(int actionIndex){

    }
    protected Object doAsyncOperation(DoAsyncCall currentTusk, int actionIndex) throws Exception{
        currentTusk.publishTheProgress();
        this.asyncAction = actionIndex;
        return null;
    }

    protected void afterAsyncCall(int actionIndex){
        // TODO: fix
        if(clickedView != null)
            clickedView = null;

        isFromServer = false;

        BaseAsyncActivity baseAsyncActivity = (BaseAsyncActivity)activity;
        baseAsyncActivity.hideLoadingDialog();
        isFromServer = false;

    }

    protected boolean isCached() {
        return false;
    }

    public class DoAsyncCall extends AsyncTask<Integer, Integer, Object> {

        private View triggerView;
        private int actionIndex;

        public DoAsyncCall(int actionIndex, View...triggerViews) {
            if(triggerViews != null && triggerViews.length > 0)
                this.triggerView = triggerViews[0];

            this.actionIndex = actionIndex;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            beforeAsyncCall(actionIndex);
        }

        @Override
        protected void onProgressUpdate(Integer...values) {
            super.onProgressUpdate(values);
            duringAsyncCall(actionIndex);
        }

        public void publishTheProgress(){
            publishProgress(actionIndex);
        }

        @Override
        protected Object doInBackground(Integer...actionIndexs) {
            Object res;
            myTasks.add(this);

            try {
                res = doAsyncOperation(this, actionIndex);
            }
            catch (IOException e){
                res = "Error! "+e;
            }
            catch (InterruptedException e){
                res = "Error! "+e;
            } catch (Exception e) {
                res = "Error! "+e;
            }
            return res;
        }

        @Override
        protected void onPostExecute(Object outputData) {
            if(appDisposed())
                return;

            super.onPostExecute(outputData);
            afterAsyncCall(actionIndex);
            resetIfTriggeredByView(triggerView);
        }

        private boolean appDisposed() {
            return activity == null;
        }
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
        new DoAsyncCall(0).execute();
    }
}