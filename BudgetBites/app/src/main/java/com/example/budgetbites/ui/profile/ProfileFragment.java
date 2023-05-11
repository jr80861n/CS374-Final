package com.example.budgetbites.ui.profile;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.budgetbites.ChangePasswordActivity;
import com.example.budgetbites.MainActivity;
import com.example.budgetbites.R;
import com.example.budgetbites.databinding.ActivityProfileBinding;
import com.example.budgetbites.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private TextView BioEditText;
    private ImageView profileImageView;
    private View root;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        context = root.getContext();

//        fullNameEditText = root.findViewById(R.id.fullNameEdit);
//        emailEditText = root.findViewById(R.id.emailEditText);
        BioEditText = root.findViewById(R.id.Bio_Edit_Text);
        profileImageView = root.findViewById(R.id.profileImageView);
        Button saveButton = root.findViewById(R.id.Save_Button);
        Button deleteButton = root.findViewById(R.id.Delete_button);
        Button signOutButton = root.findViewById(R.id.Sign_out);
        Button changePasswordButton = root.findViewById(R.id.Change_password_button);

        //Loading the saved profile info
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        String fullName = sharedPreferences.getString("fullName", "");
        String email = sharedPreferences.getString("email", "");
        String profileImage = sharedPreferences.getString("profileImage", "");
//        fullNameEditText.setText(fullName);
//        emailEditText.setText(email);
        BioEditText.setText(BioEditText.getText());
        if (!profileImage.equals("")) {
            profileImageView.setImageURI(Uri.parse(profileImage));
        }// test the if statement



        //Setting up the Image feature that will allow you to pick a picture from your gallery
        profileImageView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //This is the intent that will allow you to pick a picture from your gallery
            context.startActivity(intent); //This is the code that will allow you to pick a picture from your gallery

        });

        saveButton.setOnClickListener(v -> saveProfileInfo());

        deleteButton.setOnClickListener(v -> deleteProfileInfo());

        signOutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        });
        changePasswordButton.setOnClickListener(v -> {
            //FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(context, ChangePasswordActivity.class);
            context.startActivity(intent);

        });



        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            profileImageView.setImageURI(selectedImage);

            //This is where you would save the user's profile image to the database
            SharedPreferences preferences = context.getSharedPreferences("profile_info", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("profileImage", selectedImage.toString());
            editor.apply();

        }



    }

    //Method that will give you the ability to save the user's profile data
    public void saveProfileInfo(){

//        String fullName = fullNameEditText.getText().toString();
//        String email = emailEditText.getText().toString();
        String Bio = BioEditText.getText().toString();

        //Validating the user's input
//        if(fullName.equals("") || email.equals("")){
//            Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show();
//            return;
//        }



        //This is where you would save the user's profile data to the database
        SharedPreferences preferences = context.getSharedPreferences("profile_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("fullName", fullName);
//        editor.putString("email", email);
        editor.putString("Bio", Bio);
        editor.apply();



        Toast.makeText(context, "Profile Saved, welcome :)", Toast.LENGTH_SHORT).show();


    }

    //Method that will give you the ability to delete the user's profile data
    public void deleteProfileInfo(){
        SharedPreferences preferences = context.getSharedPreferences("profile_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("fullName");
        editor.remove("email");
        editor.remove("bio");
        editor.apply();


        //Data that will be deleted from the database
//        fullNameEditText.setText("");
//        emailEditText.setText("");
        profileImageView.setImageResource(R.drawable.no_profile_picture_icon);//change the name
        BioEditText.setText("");

        Toast.makeText(context, "Profile Deleted :(", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}