package com.example.budgetbites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        lottie = findViewById(R.id.lottie);

        new android.os.Handler().postDelayed(() -> {
            android.content.Intent i = new android.content.Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }, 2700);
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }


}
