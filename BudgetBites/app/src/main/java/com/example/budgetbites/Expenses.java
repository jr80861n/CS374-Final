package com.example.budgetbites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetbites.Intro.Intro2;
import com.example.budgetbites.adapters.ExpenseAdapter;
import com.example.budgetbites.models.Values;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Expenses extends AppCompatActivity {

    RecyclerView rec;
    FirebaseRecyclerAdapter adapter;
    RecyclerView.LayoutManager manager;
    FirebaseUser user;
    DatabaseReference userRef;
    FirebaseRecyclerOptions<Values> options;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user==null){
            Toast.makeText(this, "How did you get here without signing in?", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Intro2.class);
            startActivity(i);
        }

        userRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        DatabaseReference expenseRef = userRef.child("Expenses");
        options = new FirebaseRecyclerOptions.Builder<Values>().setQuery(expenseRef,Values.class).build();
        rec = findViewById(R.id.expense_recycler);
        adapter = new ExpenseAdapter(options, expenseRef);
        manager = new LinearLayoutManager(this);

        rec.setAdapter(adapter);
        rec.setLayoutManager(manager);

        findViewById(R.id.expense_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
