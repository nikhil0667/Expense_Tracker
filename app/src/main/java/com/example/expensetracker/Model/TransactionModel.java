package com.example.expensetracker.Model;

import java.io.Serializable;

public class TransactionModel implements Serializable {
    private int id;
    private String category;
    private String title;
    private double amount;
    private String type; // "Credit" or "Debit"
    private String date;


    public TransactionModel(int id, String title, String category, double amount, String type, String date) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = Double.parseDouble(String.valueOf(amount));
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
}
