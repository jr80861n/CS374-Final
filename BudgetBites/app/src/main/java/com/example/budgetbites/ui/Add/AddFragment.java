package com.example.budgetbites.ui.Add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.budgetbites.Intro.Intro2;
import com.example.budgetbites.MainActivity;
import com.example.budgetbites.databinding.FragmentAddBinding;
import com.example.budgetbites.models.Values;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.type.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class AddFragment extends Fragment
{

   private FragmentAddBinding binding;
   Calendar calendar = Calendar.getInstance();
   int month = calendar.get(Calendar.MONTH) + 1;
   int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
   int year = calendar.get(Calendar.YEAR);

   // Create a date format and convert the date to a string
   SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
   String currentDate = dateFormat.format(calendar.getTime());
   private String date = currentDate;

   public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState)
   {
      binding = FragmentAddBinding.inflate(inflater, container, false);
      View root = binding.getRoot();

      binding.btnAdd.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null)
            {
               Toast.makeText(root.getContext(), "Please Login First", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(root.getContext(), Intro2.class));
               return;
            }
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            DatabaseReference favsRef;
            String selctOption = binding.spinnerOptions.getSelectedItem().toString();

            if (selctOption.equals("Income"))
            {
               favsRef = reference.child(user.getUid()).child("Incomes");
            } else if (selctOption.equals("Expense"))
            {
               favsRef = reference.child(user.getUid()).child("Expenses");
            } else
            {
               Toast.makeText(root.getContext(), "No option selected", Toast.LENGTH_SHORT).show();
               return;
            }
            String rand = String.valueOf(new Random().nextInt());
            if (date==null||date.isEmpty())
            {
               Toast.makeText(root.getContext(), "Add a date", Toast.LENGTH_SHORT).show();
               return;
            }
            Values values = new Values(rand, date, binding.editValue.getText().toString());
            Boolean flag = favsRef.push().setValue(values).isSuccessful();
            if (flag)
            {
               Toast.makeText(root.getContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
            } else
            {
               Toast.makeText(root.getContext(), "Successful", Toast.LENGTH_SHORT).show();
            }
         }
      });

      CalendarView calendarView = binding.calendarView;

      calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
      {
         @Override
         public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
         {
            date = "" + month + "/" + dayOfMonth + "/" + year;
         }
      });


      return root;
   }

   @Override
   public void onDestroyView()
   {
      super.onDestroyView();
      binding = null;
   }
}