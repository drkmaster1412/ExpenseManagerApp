package com.example.expensemanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder>
{

    private List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions) {
        if (transactions != null) {
            this.transactions = transactions;
        } else {
            this.transactions = new ArrayList<>(); // Initialize with an empty list
        }
    }

    public class TransactionHolder extends RecyclerView.ViewHolder {
        TextView label;
        TextView amount;

        public TransactionHolder(View view) {
            super(view);
            label = view.findViewById(R.id.label);
            amount = view.findViewById(R.id.amount);
        }
    }

    @Override
    public TransactionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_layout, parent, false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(final TransactionHolder holder, final int position) {
        final Transaction transaction = transactions.get(position);
        final Context context = holder.amount.getContext();

        if (transaction.getAmount() >= 0) {
            holder.amount.setText(String.format("+ $%.2f", transaction.getAmount()));
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.green));
        } else {
            holder.amount.setText(String.format("- $%.2f", Math.abs(transaction.getAmount())));
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.red));
        }

        holder.label.setText(transaction.getLabel());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailedActivity.class);
//                intent.putExtra("transaction", transaction);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setData(List<Transaction> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }
}


