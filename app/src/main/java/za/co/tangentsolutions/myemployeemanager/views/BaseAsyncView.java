package za.co.tangentsolutions.myemployeemanager.views;

public interface BaseAsyncView extends BaseView{
    void showLoadingDialog(String loadingMessage);
    void hideLoadingDialog();
    void showHttpCallError(String errorMessage);
}