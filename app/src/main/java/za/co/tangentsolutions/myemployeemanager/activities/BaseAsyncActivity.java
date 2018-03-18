package za.co.tangentsolutions.myemployeemanager.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import za.co.tangentsolutions.myemployeemanager.R;

public class BaseAsyncActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_async);
    }
}
