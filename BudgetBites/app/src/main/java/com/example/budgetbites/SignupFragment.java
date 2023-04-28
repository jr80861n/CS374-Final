package com.example.budgetbites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class SignupFragment extends Fragment
{

   public SignupFragment() {
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_signup, container, false);

      Button buttonCreateAccount = view.findViewById(R.id.button_signup);
      buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            EditText editTextName = view.findViewById(R.id.edit_name);
            EditText editTextEmail = view.findViewById(R.id.edit_email);
            EditText editTextPassword = view.findViewById(R.id.edit_password);

            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

         }
      });

      return view;
   }
}
