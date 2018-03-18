package za.co.tangentsolutions.myemployeemanager.providers;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.R;

public class DialogFragmentProvider extends DialogFragment {
    protected View clickedView;

    static DialogFragmentProvider newInstance() {
        return new DialogFragmentProvider();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().getAttributes().windowAnimations =  R.style.DialogTheme;

        int layout = getArguments().getInt(Constants.LAYOUT);
        View v = inflater.inflate(layout, container, false);
        return v;
    }

    protected void setViewClickEvents(View[] views) {
        for(View view : views){
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onFragmentViewClickedEvent(v);
                }
            });
        }
    }

    protected void setListviewClickEvents(ListView listView) {
        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onFragmentViewClickedEvent(view);
            }
        });
    }

    protected void onFragmentViewClickedEvent(View view) {
       // getDialog().dismiss();
    }
}