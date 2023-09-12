package com.example.expensemanagerapp;

import androidx.room.*;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("SELECT * FROM transactions")
    List<Transaction> getAll();

    @Insert
    void insertAll(Transaction transactions);

    @Delete
    void delete(Transaction transaction);

    @Update
    void update(Transaction... transactions);

}
