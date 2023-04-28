package com.example.budgetbites.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.budgetbites.MainActivity;
import com.example.budgetbites.R;
import com.example.budgetbites.databinding.ActivityIntroBinding;
import com.example.budgetbites.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Intro extends AppCompatActivity {
    private ActivityIntroBinding binding;
    private FirebaseAuth fa;
    private FirebaseUser fu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fa = FirebaseAuth.getInstance();
        fu = fa.getCurrentUser();

        Button cont = findViewById(R.id.continueButton);

        cont.setOnClickListener(v -> {
            Intent i = new Intent(Intro.this, Intro2.class);
            startActivity(i);
        });

        Button skip = findViewById(R.id.SkipButton);

        skip.setOnClickListener(v -> {
            Intent i = new Intent(Intro.this, MainActivity.class);
            startActivity(i);
        });

    }

}
