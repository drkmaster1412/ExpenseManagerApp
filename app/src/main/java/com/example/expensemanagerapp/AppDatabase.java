package com.example.expensemanagerapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Transaction.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();
}
