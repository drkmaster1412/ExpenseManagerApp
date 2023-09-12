package com.example.expensemanagerapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import androidx.core.widget.NestedScrollView;
import androidx.room.Room;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddTransactionActivity extends AppCompatActivity {

    private EditText labelInput;
    private EditText descriptionInput;
    private EditText amountInput;
    private Button addTransactionBtn;
    private ImageButton closeBtn;

    private TextInputLayout labelLayout;
    private TextInputLayout amountLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        labelInput = findViewById(R.id.labelInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        amountInput = findViewById(R.id.amountInput);
        addTransactionBtn = findViewById(R.id.addTransactionBtn);
        closeBtn = findViewById(R.id.closeBtn);
        labelLayout = findViewById(R.id.labelLayout);
        amountLayout = findViewById(R.id.amountLayout);


        labelInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    // Clear the error message
                    labelLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        amountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    // Clear the error message
                    amountLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        addTransactionBtn.setOnClickListener(view -> {
            String label = labelInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String amountText = amountInput.getText().toString();

            if (label.isEmpty()) {
                labelLayout.setError("Please enter a valid label");
            } else if (amountText.isEmpty()) {
                amountLayout.setError("Please enter a valid amount");
            } else {
                double amount = Double.parseDouble(amountText);
                Transaction transaction = new Transaction(0, label, amount, description);
                insert(transaction);
            }
        });

        closeBtn.setOnClickListener(view -> finish());
    }private void insert(Transaction transaction) {
        AppDatabase db = Room.databaseBuilder(this,
                AppDatabase.class,
                "transactions").build();

        new Thread(() -> {
            db.transactionDao().insertAll(transaction);
            runOnUiThread(this::finish);
        }).start();
    }
}
