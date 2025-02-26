//package com.example.expensetracker.DataModel;
//
//import android.app.Application;
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import com.example.expensetracker.Model.TransactionModel;
//
//import java.util.List;
//
//public class TransactionViewModel extends AndroidViewModel {
//    private TransactionRepository repository;
//    private LiveData<List<TransactionEntity>> allTransactions;
//
//    public TransactionViewModel(@NonNull Application application) {
//        super(application);
//        repository = new TransactionRepository(application);
//        allTransactions = repository.getAllTransactions();
//    }
//
//    public LiveData<List<TransactionEntity>> getAllTransactions() {
//        return allTransactions;
//    }
//
//    public LiveData<List<TransactionEntity>> getTransactionsByCategory(String category) {
//        return repository.getTransactionsByCategory(category);
//    }
//
//    public void insert(TransactionEntity transaction) {
//        repository.insert(transaction);
//    }
//
//    public void update(TransactionEntity transaction) {
//        repository.update(transaction);
//    }
//
//    public void delete(TransactionModel transaction) {
//        repository.delete(transaction);/
//    }
//}
package com.example.expensetracker.DataModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.expensetracker.DataModel.TransactionEntity;
import com.example.expensetracker.DataModel.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository repository;
    private LiveData<List<TransactionEntity>> allTransactions;
    private LiveData<List<DateIncomeExpense>> totalIncomeExpenseByDate;


    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionRepository(application);
        allTransactions = repository.getAllTransactions();
        totalIncomeExpenseByDate = repository.getTotalIncomeExpenseByDate();
    }

    public LiveData<List<TransactionEntity>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<List<TransactionEntity>> getTransactionsByCategory(String category) {
        return repository.getTransactionsByCategory(category);
    }


        public LiveData<List<DateIncomeExpense>> getTotalIncomeExpenseByDate() {

        return totalIncomeExpenseByDate;
    }



    public void insert(TransactionEntity transaction) {
        repository.insert(transaction);
    }

    public void update(TransactionEntity transaction) {
        repository.update(transaction);
    }

    public void delete(TransactionEntity transaction) {
        repository.delete(transaction);
    }
}
