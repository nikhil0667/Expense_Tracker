package com.example.expensetracker.DataModel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionRepository {
    private TransactionDao transactionDao;
    private LiveData<List<TransactionEntity>> allTransactions;
    private ExecutorService executorService;

    public TransactionRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        transactionDao = db.transactionDao();
        allTransactions = transactionDao.getAllTransactions();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<TransactionEntity>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<List<TransactionEntity>> getTransactionsByCategory(String category) {
        return transactionDao.getTransactionsByCategory(category);
    }



    public void insert(TransactionEntity transaction) {
        executorService.execute(() -> transactionDao.insert(transaction));
    }

    public void update(TransactionEntity transaction) {
        executorService.execute(() -> transactionDao.update(transaction));
    }

    public void delete(TransactionEntity transaction) {
        executorService.execute(() -> transactionDao.delete(transaction));
    }
    public LiveData<List<DateIncomeExpense>> getTotalIncomeExpenseByDate() {
        return transactionDao.getTotalIncomeExpenseByDate();
    }


}
