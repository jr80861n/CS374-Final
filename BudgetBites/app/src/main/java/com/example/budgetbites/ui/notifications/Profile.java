package com.example.budgetbites.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //This is the intent that will allow you to pick a picture from your gallery
startActivityForResult(intent, 1); //This is the code that will allow you to pick a picture from your gallery

            }

         });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            profileImageView.setImageURI(selectedImage);

        }

    }


}
