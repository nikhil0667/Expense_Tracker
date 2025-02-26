//package com.example.expensetracker;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.expensetracker.Model.RecyclerTransactionAdapter;
//import com.example.expensetracker.Model.TransactionModel;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//
//public class HomeFragment extends Fragment implements RecyclerTransactionAdapter.OnItemInteractionListener {
//
//    private Spinner spinnerCategoryFilter;
//    private RecyclerView rvTransactions;
//    private FloatingActionButton fabAddTransaction;
//    private TextView tvTotalBalance, tvIncome, tvExpenses;
//    private ArrayList<TransactionModel> fullTransactionList = new ArrayList<>();
//    private ArrayList<TransactionModel> filteredTransactionList = new ArrayList<>();
//    private RecyclerTransactionAdapter adapter;
//    private static final int REQUEST_ADD_TRANSACTION = 100;
//    private static final int REQUEST_UPDATE_TRANSACTION = 101;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//
//        spinnerCategoryFilter = root.findViewById(R.id.spinnerCategoryFilter);
//        rvTransactions = root.findViewById(R.id.rvExpenses);
//        fabAddTransaction = root.findViewById(R.id.fabAddExpense);
//        tvTotalBalance = root.findViewById(R.id.tvTotalBalance);
//        tvIncome = root.findViewById(R.id.tvIncome);
//        tvExpenses = root.findViewById(R.id.tvExpenses);
//
//        rvTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new RecyclerTransactionAdapter(getContext(), filteredTransactionList, this);
//        rvTransactions.setAdapter(adapter);
//        updateSummary();
//        // Spinner for filtering transactions
//        spinnerCategoryFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                filterTransactions();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) { }
//        });
//
//        // FAB launches AddTransactionActivity for a new transaction
//        fabAddTransaction.setOnClickListener(v -> {
//            Intent intent = new Intent(getContext(), AddTransactionActivity.class);
//            startActivityForResult(intent, REQUEST_ADD_TRANSACTION);
//        });
//
//        return root;
//    }
//
//    @Override
//    public void onUpdate(TransactionModel transaction) {
//        // Launch AddTransactionActivity in update mode with the selected transaction details
//        Intent intent = new Intent(getContext(), AddTransactionActivity.class);
//        intent.putExtra(AddTransactionActivity.EXTRA_UPDATE_MODE, true);
//        intent.putExtra(AddTransactionActivity.EXTRA_TRANSACTION, transaction);
//        startActivityForResult(intent, REQUEST_UPDATE_TRANSACTION);
//    }
//
//    @Override
//    public void onDelete(TransactionModel transaction, int position) {
//        fullTransactionList.remove(transaction);
//        filterTransactions();
//        updateSummary();
//        Toast.makeText(getContext(), "Transaction deleted", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == Activity.RESULT_OK && data != null) {
//            TransactionModel transaction = (TransactionModel) data.getSerializableExtra(AddTransactionActivity.EXTRA_TRANSACTION);
//            boolean updateMode = data.getBooleanExtra(AddTransactionActivity.EXTRA_UPDATE_MODE, false);
//            if (requestCode == REQUEST_ADD_TRANSACTION && !updateMode) {
//                fullTransactionList.add(transaction);
//                Toast.makeText(getContext(), "Transaction added", Toast.LENGTH_SHORT).show();
//            } else if (requestCode == REQUEST_UPDATE_TRANSACTION && updateMode) {
//                // Replace the existing transaction in fullTransactionList
//                for (int i = 0; i < fullTransactionList.size(); i++) {
//                    if (fullTransactionList.get(i).getId() == transaction.getId()) {
//                        fullTransactionList.set(i, transaction);
//                        break;
//                    }
//                }
//                Toast.makeText(getContext(), "Transaction updated", Toast.LENGTH_SHORT).show();
//            }
//            filterTransactions();
//            updateSummary();
//        }
//    }
//
//    // Filters fullTransactionList based on spinner selection and updates the adapter
//    private void filterTransactions() {
//        String selectedCategory = spinnerCategoryFilter.getSelectedItem().toString();
//        filteredTransactionList.clear();
//        if(selectedCategory.equals("All")){
//            filteredTransactionList.addAll(fullTransactionList);
//        } else {
//            for (TransactionModel transaction : fullTransactionList) {
//                if(transaction.getCategory().equalsIgnoreCase(selectedCategory)){
//                    filteredTransactionList.add(transaction);
//                }
//            }
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//    // Calculates total income, expenses, and balance then updates the TextViews
//    private void updateSummary() {
//        double totalIncome = 0, totalExpenses = 0;
//        for (TransactionModel transaction : fullTransactionList) {
//            if(transaction.getType().equalsIgnoreCase("Credit")){
//                totalIncome += transaction.getAmount();
//            } else {
//                totalExpenses += transaction.getAmount();
//            }
//        }
//        double totalBalance = totalIncome - totalExpenses;
//        tvIncome.setText("$" + String.format("%.2f", totalIncome));
//        tvExpenses.setText("$" + String.format("%.2f", totalExpenses));
//        tvTotalBalance.setText("$" + String.format("%.2f", totalBalance));
//    }
//}
package com.example.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.Model.RecyclerTransactionAdapter;
import com.example.expensetracker.DataModel.TransactionEntity;
import com.example.expensetracker.DataModel.TransactionViewModel;
import com.example.expensetracker.Model.TransactionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment implements RecyclerTransactionAdapter.OnItemInteractionListener {

    private Spinner spinnerDay, spinnerMonth, spinnerYear;

    private Spinner spinnerCategoryFilter;
    private RecyclerView rvTransactions;
    private FloatingActionButton fabAddTransaction;
    private TextView tvTotalBalance, tvIncome, tvExpenses;
    private RecyclerTransactionAdapter adapter;
    private TransactionViewModel transactionViewModel;
    private String currentFilter = "All";

    private static final int REQUEST_ADD_TRANSACTION = 100;
    private static final int REQUEST_UPDATE_TRANSACTION = 101;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        spinnerCategoryFilter = root.findViewById(R.id.spinnerCategoryFilter);
        rvTransactions = root.findViewById(R.id.rvExpenses);
        fabAddTransaction = root.findViewById(R.id.fabAddExpense);
        tvTotalBalance = root.findViewById(R.id.tvTotalBalance);
        tvIncome = root.findViewById(R.id.tvIncome);
        tvExpenses = root.findViewById(R.id.tvExpenses);

        rvTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerTransactionAdapter(getContext(), new java.util.ArrayList<>(), this);
        rvTransactions.setAdapter(adapter);

        // Obtain ViewModel instance
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        // Observe transactions LiveData based on filter
        spinnerCategoryFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                currentFilter = spinnerCategoryFilter.getSelectedItem().toString();
                loadTransactions();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        fabAddTransaction.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddTransactionActivity.class);
            startActivityForResult(intent, REQUEST_ADD_TRANSACTION);
        });

        // Observe all transactions initially
        transactionViewModel.getAllTransactions().observe(getViewLifecycleOwner(), new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> transactions) {
                updateSummary(transactions);
                // If "All" is selected, update the adapter; otherwise, filtering will be handled in loadTransactions()
                if (currentFilter.equals("All")) {
                    adapter.updateList(transactions);
                }
            }
        });

        // Also load transactions based on the current filter
        loadTransactions();

        return root;
    }

    private void loadTransactions() {
        if (currentFilter.equals("All")) {
            transactionViewModel.getAllTransactions().observe(getViewLifecycleOwner(), transactions -> {
                adapter.updateList(transactions);
                updateSummary(transactions);
            });
        } else {
            transactionViewModel.getTransactionsByCategory(currentFilter).observe(getViewLifecycleOwner(), transactions -> {
                adapter.updateList(transactions);
                updateSummary(transactions);
            });
        }
    }

    private void updateSummary(List<TransactionEntity> transactions) {
        double totalIncome = 0, totalExpenses = 0;
        for (TransactionEntity transaction : transactions) {
            if (transaction.getType().equalsIgnoreCase("Credit")) {
                totalIncome += transaction.getAmount();
            } else {
                totalExpenses += transaction.getAmount();
            }
        }
        double totalBalance = totalIncome - totalExpenses;
        tvIncome.setText("$" + String.format("%.2f", totalIncome));
        tvExpenses.setText("$" + String.format("%.2f", totalExpenses));
        tvTotalBalance.setText("$" + String.format("%.2f", totalBalance));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && data != null) {
            TransactionEntity transaction = (TransactionEntity) data.getSerializableExtra(AddTransactionActivity.EXTRA_TRANSACTION);
            boolean updateMode = data.getBooleanExtra(AddTransactionActivity.EXTRA_UPDATE_MODE, false);
            if(requestCode == REQUEST_ADD_TRANSACTION && !updateMode) {
                transactionViewModel.insert(transaction);
                Toast.makeText(getContext(), "Transaction added", Toast.LENGTH_SHORT).show();
            } else if(requestCode == REQUEST_UPDATE_TRANSACTION && updateMode) {
                transactionViewModel.update(transaction);
                Toast.makeText(getContext(), "Transaction updated", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onUpdate(TransactionEntity transaction) {

        Intent intent = new Intent(getContext(), AddTransactionActivity.class);
        intent.putExtra(AddTransactionActivity.EXTRA_UPDATE_MODE, true);
        intent.putExtra(AddTransactionActivity.EXTRA_TRANSACTION, transaction);
        startActivityForResult(intent, REQUEST_UPDATE_TRANSACTION);


    }

    @Override
    public void onDelete(TransactionEntity transaction, int position) {

        transactionViewModel.delete(transaction);
        Toast.makeText(getContext(), "Transaction deleted", Toast.LENGTH_SHORT).show();

    }
}
