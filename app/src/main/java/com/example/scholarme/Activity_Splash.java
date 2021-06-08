package com.example.scholarme;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The first window when the app is being loaded
 */
public class Activity_Splash extends AppCompatActivity {

   private final int ANIMATION_DURATION = 2000;
   private TextView splash_LBL_name;
   private ImageView main_background_IMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__splash);

        findViews();
        startAnimation(splash_LBL_name);
    }

   /**
 * The animation function which makes the text comes from down-up
 */
    private void startAnimation(View view1) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        view1.setY(height / 2);
        view1.animate()
                .translationY(0)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Intent myIntent = new Intent(Activity_Splash.this, MainActivity.class);
                        startActivity(myIntent);
                        finish();
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) { }
                    @Override
                    public void onAnimationRepeat(Animator animator) { }
                });
    }


    private void findViews() {
        splash_LBL_name = findViewById(R.id.splash_LBL_name);
        main_background_IMG=findViewById(R.id.main_background_IMG);
    }
}
