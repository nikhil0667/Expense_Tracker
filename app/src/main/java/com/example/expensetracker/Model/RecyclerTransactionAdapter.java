//package com.example.expensetracker.Model;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.expensetracker.DataModel.TransactionEntity;
//import com.example.expensetracker.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RecyclerTransactionAdapter extends RecyclerView.Adapter<RecyclerTransactionAdapter.ViewHolder> {
//    Context context;
//    ArrayList<TransactionModel> arrTransaction;
//    OnItemInteractionListener listener;
//
//    public interface OnItemInteractionListener {
//        void onUpdate(TransactionModel transaction);
//        void onDelete(TransactionModel transaction, int position);
//    }
//
//    public RecyclerTransactionAdapter(Context context, ArrayList<TransactionModel> arrTransaction, OnItemInteractionListener listener) {
//        this.context = context;
//        this.arrTransaction = arrTransaction;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // Inflate the card layout (e.g., item_transaction_card.xml)
//        View view = LayoutInflater.from(context).inflate(R.layout.item_transaction_history, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        TransactionModel transaction = arrTransaction.get(position);
//        holder.tvId.setText(String.valueOf(transaction.getId()));
//        holder.tvTitle.setText(transaction.getTitle());
//        holder.tvDate.setText(transaction.getDate());
//
//        // Format amount with plus or minus sign
//        String amountText;
//        if(transaction.getType().equalsIgnoreCase("Credit")){
//            amountText = "+ $" + String.format("%.2f", transaction.getAmount());
//            holder.tvAmount.setTextColor(context.getResources().getColor(R.color.green)); // Assuming R.color.green is defined
//        } else {
//            amountText = "- $" + String.format("%.2f", transaction.getAmount());
//            holder.tvAmount.setTextColor(context.getResources().getColor(R.color.red));   // Assuming R.color.red is defined
//        }
//        holder.tvAmount.setText(amountText);
//
//        // Single click to update
//        holder.itemView.setOnClickListener(v -> {
//            if (listener != null) {
//                listener.onUpdate(transaction);
//            }
//        });
//
//        // Long click to delete
//        holder.itemView.setOnLongClickListener(v -> {
//            new AlertDialog.Builder(context)
//                    .setTitle("Delete Transaction")
//                    .setMessage("Are you sure you want to delete this transaction?")
//                    .setPositiveButton("Yes", (dialog, which) -> {
//                        if (listener != null) {
//                            listener.onDelete(transaction, position);
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//            return true;
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrTransaction.size();
//    }
//
//    // Method to update list (for filtering, etc.)
//    public void updateList(List<TransactionEntity> filteredList) {
//        arrTransaction = filteredList;
//        notifyDataSetChanged();
//    }
//
//    // Method to remove a single item
//    public void removeItem(int position) {
//        arrTransaction.remove(position);
//        notifyItemRemoved(position);
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView tvId, tvTitle, tvDate, tvAmount;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            tvId = itemView.findViewById(R.id.tvId);
//            tvTitle = itemView.findViewById(R.id.tvTitle);
//            tvDate = itemView.findViewById(R.id.tvDate);
//            tvAmount = itemView.findViewById(R.id.tvAmount);
//        }
//    }
//}

package com.example.expensetracker.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.DataModel.TransactionEntity;
import com.example.expensetracker.R;
import java.util.List;

public class RecyclerTransactionAdapter extends RecyclerView.Adapter<RecyclerTransactionAdapter.ViewHolder> {
    private Context context;
    private List<TransactionEntity> transactions;
    private OnItemInteractionListener listener;

    public interface OnItemInteractionListener {
        void onUpdate(TransactionEntity transaction);
        void onDelete(TransactionEntity transaction, int position);
    }

    public RecyclerTransactionAdapter(Context context, List<TransactionEntity> transactions, OnItemInteractionListener listener) {
        this.context = context;
        this.transactions = transactions;
        this.listener = listener;
    }

    public void updateList(List<TransactionEntity> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaction_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionEntity transaction = transactions.get(position);
        holder.tvId.setText(String.valueOf(position+1));
        String title = transaction.getTitle();
        if(title != null && !title.isEmpty()){
            String formattedTitle = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
            holder.tvTitle.setText(formattedTitle);
        } else {
            holder.tvTitle.setText("");
        }
        holder.tvDate.setText(transaction.getDate());
        String amountText;
        if (transaction.getType().equalsIgnoreCase("Credit")) {
            amountText = "+ $" + String.format("%.2f", transaction.getAmount());
            holder.tvAmount.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            amountText = "- $" + String.format("%.2f", transaction.getAmount());
            holder.tvAmount.setTextColor(context.getResources().getColor(R.color.red));
        }
        holder.tvAmount.setText(amountText);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onUpdate(transaction);
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Transaction")
                    .setMessage("Are you sure you want to delete this transaction?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if (listener != null) {
                            listener.onDelete(transaction, position);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvTitle, tvDate, tvAmount;
        public ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvAmount = itemView.findViewById(R.id.tvAmount);
        }
    }
}
