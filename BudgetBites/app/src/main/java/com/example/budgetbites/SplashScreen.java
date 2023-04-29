package com.example.budgetbites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.budgetbites.Intro.Intro;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottie;

    private FirebaseAuth mfa;
    private FirebaseAnalytics mFirebaseAnalytics;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        lottie = findViewById(R.id.lottie);
        mfa = FirebaseAuth.getInstance();
       // FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        new android.os.Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 2700);


        //FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

      


        lottie.animate();



    }


}
