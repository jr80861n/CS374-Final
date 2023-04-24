package com.example.budgetbites.ui.notifications;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetbites.R;

public class Profile extends AppCompatActivity {
    private EditText firstNameEditText, lastnameEditText, emailEditText;
    private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Adding the info to the profile
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastnameEditText = findViewById(R.id.lastnameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        profileImageView = findViewById(R.id.profileImageView);

        //Setting up the Image feature that will allow you to pick a picture from your gallery

    }
}
