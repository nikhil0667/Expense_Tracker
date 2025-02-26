package com.example.expensetracker.DataModel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    long insert(TransactionEntity transaction);

    @Update
    void update(TransactionEntity transaction);

    @Delete
    void delete(TransactionEntity transaction);

    @Query("SELECT * FROM transactions ORDER BY id ASC")
    LiveData<List<TransactionEntity>> getAllTransactions();

    @Query("SELECT * FROM transactions WHERE category = :category ORDER BY id ASC")
    LiveData<List<TransactionEntity>> getTransactionsByCategory(String category);


    @Query("SELECT date, " +
            "SUM(CASE WHEN type = 'Credit' THEN amount ELSE 0 END) as totalIncome, " +
            "SUM(CASE WHEN type = 'Debit' THEN amount ELSE 0 END) as totalExpense " +
            "FROM transactions GROUP BY date ORDER BY date ASC")
    LiveData<List<DateIncomeExpense>> getTotalIncomeExpenseByDate();

}
