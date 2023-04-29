package com.example.budgetbites.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetbites.LoginFragment;
import com.example.budgetbites.MainActivity;
import com.example.budgetbites.R;
import com.example.budgetbites.SignupFragment;
import com.example.budgetbites.databinding.ActivityIntroAboutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Intro2 extends AppCompatActivity {
    private ActivityIntroAboutBinding binding;
    private FirebaseAuth fa;
    private FirebaseUser fu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fa = FirebaseAuth.getInstance();
        fu = fa.getCurrentUser();

         Button login = findViewById(R.id.LoginButton);

         login.setOnClickListener(v -> {
             Intent i = new Intent(Intro2.this, LoginFragment.class);
             startActivity(i);
             finish();
         });

         Button signup = findViewById(R.id.SignupButton);

        signup.setOnClickListener(v -> {
            Intent i = new Intent(Intro2.this, SignupFragment.class);
            startActivity(i);
            finish();
        });

    }

}