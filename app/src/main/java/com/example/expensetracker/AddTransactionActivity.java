//package com.example.expensetracker;
//
//import android.app.DatePickerDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.DatePicker;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.expensetracker.DataModel.TransactionEntity;
//import com.google.android.material.button.MaterialButton;
//import com.google.android.material.textfield.TextInputEditText;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Locale;
//
//public class AddTransactionActivity extends AppCompatActivity {
//
//    public static final String EXTRA_TRANSACTION = "transaction";
//    public static final String EXTRA_UPDATE_MODE = "update_mode";
//
//    private TextInputEditText etTitle, etAmount, etDate;
//    private Spinner spinnerTransactionCategory, spinnerTransactionType;
//    private MaterialButton btnAddTransaction;
//    private boolean isUpdateMode = false;
//    private TransactionEntity transactionToUpdate;
//    private DatePicker datePicker;
//    private TextView tvSelectedDate;
//    private Calendar calendar; // To hold the current date
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_transaction);
//        tvSelectedDate = findViewById(R.id.tvSelectedDate);
//        calendar = Calendar.getInstance();
//
//        etTitle = findViewById(R.id.etTransactionTitle);
//        etAmount = findViewById(R.id.etTransactionAmount);
////        etDate = findViewById(R.id.etTransactionDate);
//        spinnerTransactionCategory = findViewById(R.id.spinnerTransactionCategory);
//        spinnerTransactionType = findViewById(R.id.spinnerTransactionType);
//        btnAddTransaction = findViewById(R.id.btnAddTransaction);
//        Calendar calendar = Calendar.getInstance();
//        updateDateInView();
//        tvSelectedDate.setOnClickListener(v -> {
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH);
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//            // Create and show the DatePickerDialog
//            DatePickerDialog datePickerDialog = new DatePickerDialog(
//                    AddTransactionActivity.this,
//                    (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
//                        // Update the calendar with the selected date
//                        calendar.set(selectedYear, selectedMonth, selectedDay);
//                        // Update the TextView with the selected date
//                        updateDateInView();
//                    },
//                    year,
//                    month,
//                    day
//            );
//            datePickerDialog.show();
//        });
//        // Set up spinners
//        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
//                this, R.array.categories, android.R.layout.simple_spinner_item);
//        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerTransactionCategory.setAdapter(categoryAdapter);
//
//        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(
//                this, R.array.transaction_type, android.R.layout.simple_spinner_item);
//        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerTransactionType.setAdapter(typeAdapter);
//
//        // Check for update mode
//        Intent intent = getIntent();
//        if (intent != null && intent.getBooleanExtra(EXTRA_UPDATE_MODE, false)) {
//            isUpdateMode = true;
//            transactionToUpdate = (TransactionEntity) intent.getSerializableExtra(EXTRA_TRANSACTION);
//            if (transactionToUpdate != null) {
//                etTitle.setText(transactionToUpdate.getTitle());
//                etAmount.setText(String.valueOf(transactionToUpdate.getAmount()));
//                etDate.setText(transactionToUpdate.getDate());
//                setSpinnerSelection(spinnerTransactionCategory, transactionToUpdate.getCategory());
//                setSpinnerSelection(spinnerTransactionType, transactionToUpdate.getType());
//                btnAddTransaction.setText("Update Transaction");
//            }
//        }
//
//        btnAddTransaction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int day = datePicker.getDayOfMonth();
//                int month = datePicker.getMonth(); // 0-based (January is 0)
//                int year = datePicker.getYear();
//
//                String selectedDate = String.format("%02d/%02d/%04d", day, (month+1), year);
//
//                String title = etTitle.getText().toString().trim();
//                String amountStr = etAmount.getText().toString().trim();
//                String date = selectedDate;
////                String date = etDate.getText().toString().trim();
//                String category = spinnerTransactionCategory.getSelectedItem().toString();
//                String type = spinnerTransactionType.getSelectedItem().toString();
//
//                if (title.isEmpty() || amountStr.isEmpty() || date.isEmpty()) {
//                    Toast.makeText(AddTransactionActivity.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                double amount = Double.parseDouble(amountStr);
//                TransactionEntity transaction;
//                if (isUpdateMode && transactionToUpdate != null) {
//                    transactionToUpdate.setTitle(title);
//                    transactionToUpdate.setAmount(amount);
//                    transactionToUpdate.setDate(date);
//                    transactionToUpdate.setCategory(category);
//                    transactionToUpdate.setType(type);
//                    transaction = transactionToUpdate;
//                } else {
//                    transaction = new TransactionEntity(title, category, amount, type, date);
//                }
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra(EXTRA_TRANSACTION, transaction);
//                resultIntent.putExtra(EXTRA_UPDATE_MODE, isUpdateMode);
//                setResult(RESULT_OK, resultIntent);
//                finish();
//            }
//        });
//    }
//
//    private void setSpinnerSelection(Spinner spinner, String value) {
//        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
//        int position = adapter.getPosition(value);
//        if (position >= 0) {
//            spinner.setSelection(position);
//        }
//    } private void updateDateInView() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        tvSelectedDate.setText(sdf.format(calendar.getTime()));
//    }
//}
package com.example.expensetracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetracker.DataModel.TransactionEntity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTransactionActivity extends AppCompatActivity {

    public static final String EXTRA_TRANSACTION = "transaction";
    public static final String EXTRA_UPDATE_MODE = "update_mode";

    private TextInputEditText etTitle, etAmount;
    // Removed etDate since we now use tvSelectedDate for date selection
    private Spinner spinnerTransactionCategory, spinnerTransactionType;
    private MaterialButton btnAddTransaction;
    private TextView tvSelectedDate;

    private boolean isUpdateMode = false;
    private TransactionEntity transactionToUpdate;

    // Calendar instance to hold the selected date
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Initialize views
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        etTitle = findViewById(R.id.etTransactionTitle);
        etAmount = findViewById(R.id.etTransactionAmount);
        spinnerTransactionCategory = findViewById(R.id.spinnerTransactionCategory);
        spinnerTransactionType = findViewById(R.id.spinnerTransactionType);
        btnAddTransaction = findViewById(R.id.btnAddTransaction);

        // Initialize Calendar and update tvSelectedDate
        calendar = Calendar.getInstance();
        updateDateInView();

        // Set click listener on tvSelectedDate to open DatePickerDialog
        tvSelectedDate.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddTransactionActivity.this,
                    (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                        calendar.set(selectedYear, selectedMonth, selectedDay);
                        updateDateInView();
                    },
                    year,
                    month,
                    day
            );
            datePickerDialog.show();
        });

        // Set up spinners
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this, R.array.categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTransactionCategory.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(
                this, R.array.transaction_type, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTransactionType.setAdapter(typeAdapter);

        // Check for update mode if launched with intent extras
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra(EXTRA_UPDATE_MODE, false)) {
            isUpdateMode = true;
            transactionToUpdate = (TransactionEntity) intent.getSerializableExtra(EXTRA_TRANSACTION);
            if (transactionToUpdate != null) {
                etTitle.setText(transactionToUpdate.getTitle());
                etAmount.setText(String.valueOf(transactionToUpdate.getAmount()));
                // Parse the saved date into the calendar and update the TextView
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    calendar.setTime(sdf.parse(transactionToUpdate.getDate()));
                    updateDateInView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setSpinnerSelection(spinnerTransactionCategory, transactionToUpdate.getCategory());
                setSpinnerSelection(spinnerTransactionType, transactionToUpdate.getType());
                btnAddTransaction.setText("Update Transaction");
            }
        }

        // Handle Add/Update button click
        btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Use the formatted date from the calendar
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String selectedDate = sdf.format(calendar.getTime());

                String title = etTitle.getText().toString().trim();
                String amountStr = etAmount.getText().toString().trim();
                String category = spinnerTransactionCategory.getSelectedItem().toString();
                String type = spinnerTransactionType.getSelectedItem().toString();

                if (title.isEmpty() || amountStr.isEmpty() || selectedDate.isEmpty()) {
                    Toast.makeText(AddTransactionActivity.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(amountStr);
                TransactionEntity transaction;
                if (isUpdateMode && transactionToUpdate != null) {
                    transactionToUpdate.setTitle(title);
                    transactionToUpdate.setAmount(amount);
                    transactionToUpdate.setDate(selectedDate);
                    transactionToUpdate.setCategory(category);
                    transactionToUpdate.setType(type);
                    transaction = transactionToUpdate;
                } else {
                    transaction = new TransactionEntity(title, category, amount, type, selectedDate);
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_TRANSACTION, transaction);
                resultIntent.putExtra(EXTRA_UPDATE_MODE, isUpdateMode);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    // Helper method to set spinner selection based on a given value
    private void setSpinnerSelection(Spinner spinner, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        int position = adapter.getPosition(value);
        if (position >= 0) {
            spinner.setSelection(position);
        }
    }

    // Update the tvSelectedDate with the current date from calendar
    private void updateDateInView() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        tvSelectedDate.setText(sdf.format(calendar.getTime()));
    }
}
