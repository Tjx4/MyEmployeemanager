package za.co.tangentsolutions.myemployeemanager.activities;

import android.os.Bundle;
import za.co.tangentsolutions.myemployeemanager.R;

public class EmloyeeStatsActivity extends BaseChildActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emloyee_stats);
        currentActionBar.setTitle("Employee stats");
    }
}