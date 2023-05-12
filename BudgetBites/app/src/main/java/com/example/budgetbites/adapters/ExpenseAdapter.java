package com.example.budgetbites.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetbites.Expenses;
import com.example.budgetbites.R;
import com.example.budgetbites.models.Values;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class ExpenseAdapter extends FirebaseRecyclerAdapter<Values,expenseHolder> {
    DatabaseReference reference;

    public ExpenseAdapter(@NonNull FirebaseRecyclerOptions<Values> op, DatabaseReference ref) {
        super(op);
        reference = ref;
    }

    @Override
    protected void onBindViewHolder(@NonNull expenseHolder holder, int position, @NonNull Values model) {
        if(holder==null||model==null){
            return;
        }
        holder.dateHolder.setText(model.date);
        holder.valueHolder.setText(model.value);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = reference.orderByChild("Id").equalTo(model.Id);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        long c = dataSnapshot.getChildrenCount();
                        Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                        for(int i = 0; i < c-1; i++){
                            it.next();
                        }
                        it.next().getRef().removeValue();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("NAMEADAPTER", "onCancelled", databaseError.toException());
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public expenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.expenses, parent, false);
        return new expenseHolder(view);
    }
}

class expenseHolder extends RecyclerView.ViewHolder{
    AppCompatButton button;
    TextView dateHolder, valueHolder;
    public expenseHolder(@NonNull View itemView) {
        super(itemView);
        button = itemView.findViewById(R.id.delete_button);
        dateHolder = itemView.findViewById(R.id.date_text);
        valueHolder = itemView.findViewById(R.id.value_text);
    }
}
