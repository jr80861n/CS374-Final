package com.example.budgetbites;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends AppCompatActivity
{


   @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

       EditText email = findViewById(R.id.edit_email);
       EditText password = findViewById(R.id.edit_password);
       Button login = findViewById(R.id.button_login);

       login.setOnClickListener(v-> {
           String emailInput = email.getText().toString();
           String passwordInput = password.getText().toString();
           if (emailInput.isEmpty()) {
               email.setError("Please enter your email");
               email.requestFocus();
           } else if (passwordInput.isEmpty()) {
               password.setError("Please enter your password");
               password.requestFocus();
           } else if (emailInput.isEmpty() && passwordInput.isEmpty()) {
               email.setError("Please enter your email and password");
                  email.requestFocus();
           } else if (!(emailInput.isEmpty() && passwordInput.isEmpty())) {
               FirebaseAuth.getInstance().signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(task -> {
                   if (!task.isSuccessful()) {
                       Toast.makeText(LoginFragment.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(this, "Login Succesful", Toast.LENGTH_SHORT).show();
                       finish();
                   }
               });
           } else {
               Toast.makeText(LoginFragment.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
           }
       });
   }
}
