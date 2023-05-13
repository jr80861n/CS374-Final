package com.example.budgetbites;

import static android.view.View.*;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignupFragment extends AppCompatActivity
{

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.fragment_signup);

      EditText editTextName = findViewById(R.id.edit_name);
      EditText editTextEmail = findViewById(R.id.edit_email);
      EditText editTextPassword = findViewById(R.id.edit_password);
      Button buttonCreateAccount = findViewById(R.id.button_signup);
      buttonCreateAccount.setOnClickListener(v -> {

         String name = editTextName.getText().toString();
         String email = editTextEmail.getText().toString();
         String password = editTextPassword.getText().toString();

            if (name.isEmpty()) {
                editTextName.setError("Please enter your name");
                editTextName.requestFocus();
            } else if (email.isEmpty()) {
                editTextEmail.setError("Please enter your email");
                editTextEmail.requestFocus();
            } else if (password.isEmpty()) {
                editTextPassword.setError("Please enter your password");
                editTextPassword.requestFocus();
            } else if (name.isEmpty() && email.isEmpty() && password.isEmpty()) {
                editTextName.setError("Please enter your name");
                editTextEmail.setError("Please enter your email");
                editTextPassword.setError("Please enter your password");
                editTextName.requestFocus();
            } else if (!(name.isEmpty() && email.isEmpty() && password.isEmpty())) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Toast.makeText(SignupFragment.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference().child(Objects.requireNonNull("Users")).setValue(FirebaseAuth.getInstance().getCurrentUser().getUid()).addOnCompleteListener(task1 -> {
                        Toast.makeText(this, "Account Made", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                }
                });
            } else {
                Toast.makeText(SignupFragment.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
            }
      });

   }
}
