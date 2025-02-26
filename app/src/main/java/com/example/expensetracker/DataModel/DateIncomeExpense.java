package com.example.expensetracker.DataModel;

public class DateIncomeExpense {
    private String date;
    private double totalIncome;
    private double totalExpense;

    public DateIncomeExpense(String date, double totalIncome, double totalExpense) {
        this.date = date;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
