package com.example.expensemanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


public class MainActivity extends AppCompatActivity {

    private List<Transaction> transactions;
    private TransactionAdapter transactionAdapter;
    private LinearLayoutManager linearLayoutManager;

    private AppDatabase db;

    private FloatingActionButton addBtn;
//    private TextView balance;
//    private TextView budget;
//    private TextView expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(this,
                AppDatabase.class,
                "transactions").build();

        transactionAdapter = new TransactionAdapter(transactions);
        linearLayoutManager = new LinearLayoutManager(this);


        fetchAll();

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recyclerview); // Assuming R.id.recyclerview is the ID of your RecyclerView in XML.
        recyclerView.setAdapter(transactionAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    private void updateDashboard() {
        double totalAmount = 0.0;
        double budgetAmount = 0.0;

        for (Transaction transaction : transactions) {
            totalAmount += transaction.getAmount();
            if (transaction.getAmount() > 0) {
                budgetAmount += transaction.getAmount();
            }
        }

        double expenseAmount = totalAmount - budgetAmount;

        TextView balance = findViewById(R.id.balance);

        TextView budget = findViewById(R.id.budget);
        TextView expense = findViewById(R.id.expense);

        balance.setText(String.format("$ %.2f", totalAmount));
        budget.setText(String.format("$ %.2f", budgetAmount));
        expense.setText(String.format("$ %.2f", expenseAmount));
    }

    private void fetchAll() {
        new Thread(() -> {
            transactions = db.transactionDao().getAll();

            runOnUiThread(() -> {
                updateDashboard();
                transactionAdapter.setData(transactions);
            });
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchAll();
    }



}