package com.example.budgetbites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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


    }
}
