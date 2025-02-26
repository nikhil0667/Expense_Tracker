package com.example.expensetracker.DataModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "transactions")
public class TransactionEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String category;
    private double amount;
    private String type; // "Credit" or "Debit"
    private String date;

    public TransactionEntity(String title, String category, double amount, String type, String date) {
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
