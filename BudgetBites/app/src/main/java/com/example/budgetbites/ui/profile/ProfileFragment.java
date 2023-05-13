package com.example.budgetbites.ui.profile;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.budgetbites.ChangePasswordActivity;
import com.example.budgetbites.MainActivity;
import com.example.budgetbites.R;
import com.example.budgetbites.databinding.FragmentProfileBinding;
import com.example.budgetbites.models.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private EditText fullNameEditText, emailEditText, BioEditText,GoalsEditText, ChallengesEditText, SuccessesEditText;
    private ImageView profileImageView;
    private View root;
    private Context context;

    private DatabaseReference ref;
    private FirebaseUser user;
    String oldImage = "";
    String newImage = "";
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        context = root.getContext();

        fullNameEditText = root.findViewById(R.id.fullNameEdit);
        emailEditText = root.findViewById(R.id.emailEditText);
        BioEditText = root.findViewById(R.id.Bio_Edit_Text);
        profileImageView = root.findViewById(R.id.profileImageView);
        Button changePic = root.findViewById(R.id.Change_Picture);
        Button saveButton = root.findViewById(R.id.Save_Button);
        Button signOutButton = root.findViewById(R.id.Sign_out);
        Button changePasswordButton = root.findViewById(R.id.Change_password_button);
        GoalsEditText = root.findViewById(R.id.Financial_Goals_Edit_Text);
        ChallengesEditText = root.findViewById(R.id.Financial_Challenges_Edit_Text);
        SuccessesEditText = root.findViewById(R.id.Financial_Succes_Edit_Text);

        profileImageView.setImageResource(R.drawable.no_profile_picture_icon);


        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Profile");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserData data = snapshot.getValue(UserData.class);
                if(data!=null){
                    emailEditText.setText(data.Email);
                    fullNameEditText.setText(data.Name);
                    BioEditText.setText(data.Bio);
                    GoalsEditText.setText(data.Goals);
                    ChallengesEditText.setText(data.Challenges);
                    SuccessesEditText.setText(data.Succ);
                    oldImage = data.imageURL;

                    if(!oldImage.equals("")){
                        Picasso.get().load(oldImage).into(profileImageView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "No data to load", Toast.LENGTH_SHORT).show();
            }
        });

        //Setting up the Image feature that will allow you to pick a picture from your gallery
        changePic.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //This is the intent that will allow you to pick a picture from your gallery
            startActivityForResult(intent,1); //This is the code that will allow you to pick a picture from your gallery
        });

        saveButton.setOnClickListener(v -> saveProfileInfo());


        signOutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        });
        changePasswordButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChangePasswordActivity.class);
            context.startActivity(intent);

        });



        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            newImage = selectedImage.toString();
            profileImageView.setImageURI(selectedImage);

            Toast.makeText(context, "Image Changed", Toast.LENGTH_SHORT).show();

        }



    }

    public void saveProfileInfo(){

        String fullName = fullNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String Bio = BioEditText.getText().toString();
        String goal = GoalsEditText.getText().toString();
        String challenge = ChallengesEditText.getText().toString();
        String success = SuccessesEditText.getText().toString();
        if(newImage.equals("")){
            newImage = oldImage;
        }
        if(fullName.equals("") || email.equals("")){
            Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show();
            return;
        }

        UserData data = new UserData(fullName, email, Bio, goal, challenge, success, newImage);
        ref.setValue(data);


        Toast.makeText(context, "Profile Saved, welcome :)", Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}