package za.co.tangentsolutions.myemployeemanager.providers;

import android.app.Activity;
import android.widget.ImageView;

public class GlideProvider {

    private Activity activity;

    public GlideProvider(Activity activity) {
        this.activity = activity;
    }

    public void loadImageFromInternet(String url, ImageView imageView, int placeHolderPic) {
        GlideApp.with(activity).load(url).placeholder(placeHolderPic).centerCrop().into(imageView);
    }

    public void loadImageFromResourceId(int resourceId, ImageView imageView, int placeHolderPic) {
        GlideApp.with(activity).load(resourceId).placeholder(placeHolderPic).centerCrop().into(imageView);
    }
}
