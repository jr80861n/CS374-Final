package com.example.budgetbites;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottie;

    private FirebaseAuth mfa;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        lottie = findViewById(R.id.lottie);
        mfa = FirebaseAuth.getInstance();

        new android.os.Handler().postDelayed(() -> {
            if(mfa.getCurrentUser()==null){
                Toast.makeText(SplashScreen.this, "Please login to continue", Toast.LENGTH_SHORT).show();
                startActivity(new android.content.Intent(SplashScreen.this, Intro.class));
                finish();
            }
            else{
                Toast.makeText(SplashScreen.this, "Welcome back!", Toast.LENGTH_SHORT).show();
                startActivity(new android.content.Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 2700);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        lottie.animate();




    }


}
