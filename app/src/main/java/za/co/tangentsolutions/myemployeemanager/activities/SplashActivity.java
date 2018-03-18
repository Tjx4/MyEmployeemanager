package za.co.tangentsolutions.myemployeemanager.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import za.co.tangentsolutions.myemployeemanager.R;

public class SplashActivity extends AppCompatActivity {

    private Context context;
    private ImageView appLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;

        appLogo = findViewById(R.id.imgLogo);
        appLogo.animate()
                .alpha(1.0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent i = new Intent(context, LoginActivity.class);
                        //LoginActivity.class
                        startActivity(i);
                        finish();
                    }
                });


    }
}
