package com.example.budgetbites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    private EditText fullNameEditText, emailEditText, BioEditText ;
    private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //Adding the info to the profile
        fullNameEditText = findViewById(R.id.fullNameEdit);
        emailEditText = findViewById(R.id.emailEditText);
        BioEditText = findViewById(R.id.Bio_Edit_Text);
        profileImageView = findViewById(R.id.profileImageView);
        Button saveButton = findViewById(R.id.Save_Button);
        Button deleteButton = findViewById(R.id.Delete_button);
        Button signOutButton = findViewById(R.id.Sign_out);
        Button changePasswordButton = findViewById(R.id.Change_password_button);

        //Loading the saved profile info
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        String fullName = sharedPreferences.getString("fullName", "");
        String email = sharedPreferences.getString("email", "");
        String profileImage = sharedPreferences.getString("profileImage", "");
        fullNameEditText.setText(fullName);
        emailEditText.setText(email);
        BioEditText.setText((CharSequence) BioEditText);
        if (!profileImage.equals("")) {
            profileImageView.setImageURI(Uri.parse(profileImage));
        }// test the if statement



        //Setting up the Image feature that will allow you to pick a picture from your gallery
        profileImageView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //This is the intent that will allow you to pick a picture from your gallery
startActivityForResult(intent, 1); //This is the code that will allow you to pick a picture from your gallery

        });

        saveButton.setOnClickListener(v -> saveProfileInfo());

        deleteButton.setOnClickListener(v -> deleteProfileInfo());

        signOutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);

        });
        changePasswordButton.setOnClickListener(v -> {
            //FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Profile.this, ChangePasswordActivity.class);
            startActivity(intent);

        });//test the change password button and make a change password activity/fragment

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            profileImageView.setImageURI(selectedImage);

            //This is where you would save the user's profile image to the database
            SharedPreferences preferences = getSharedPreferences("profile_info", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("profileImage", selectedImage.toString());
            editor.apply();

        }



    }

    //Method that will give you the ability to save the user's profile data
    public void saveProfileInfo(){

        String fullName = fullNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String Bio = BioEditText.getText().toString();

        //Validating the user's input
        if(fullName.equals("") || email.equals("")){
            Toast.makeText(this, "Please enter all the information", Toast.LENGTH_SHORT).show();
            return;
        }



        //This is where you would save the user's profile data to the database
        SharedPreferences preferences = getSharedPreferences("profile_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("fullName", fullName);
        editor.putString("email", email);
        editor.putString("Bio", Bio);
        editor.apply();



        Toast.makeText(this, "Profile Saved, welcome :)", Toast.LENGTH_SHORT).show();


    }

    //Method that will give you the ability to delete the user's profile data
    public void deleteProfileInfo(){
        SharedPreferences preferences = getSharedPreferences("profile_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("fullName");
        editor.remove("email");
        editor.remove("bio");
        editor.apply();


        //Data that will be deleted from the database
        fullNameEditText.setText("");
        emailEditText.setText("");
        profileImageView.setImageResource(R.drawable.no_profile_picture_icon);//change the name
        BioEditText.setText("");

        Toast.makeText(this, "Profile Deleted :(", Toast.LENGTH_SHORT).show();

    }




}
