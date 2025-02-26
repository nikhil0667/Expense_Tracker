package com.example.expensetracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.expensetracker.DataModel.DateIncomeExpense;
import com.example.expensetracker.DataModel.TransactionViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {

//    private BarChart barChart;
private LineChart lineChart;

    private TransactionViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

//        barChart = view.findViewById(R.id.chart);
lineChart = view.findViewById(R.id.chart);
        // Use ViewModelProvider with Fragment context
        viewModel = new ViewModelProvider(requireActivity()).get(TransactionViewModel.class);


        viewModel.getTotalIncomeExpenseByDate().observe(getViewLifecycleOwner(), new Observer<List<DateIncomeExpense>>() {
            @Override
            public void onChanged(List<DateIncomeExpense> dateIncomeExpenses) {
                ArrayList<Entry> incomeEntries = new ArrayList<>();
                ArrayList<Entry> expenseEntries = new ArrayList<>();
                ArrayList<String> labels = new ArrayList<>();

                for (int i = 0; i < dateIncomeExpenses.size(); i++) {
                    DateIncomeExpense data = dateIncomeExpenses.get(i);

                    // Set entries for Income and Expense
                    incomeEntries.add(new Entry(i, (float) data.getTotalIncome()));
                    expenseEntries.add(new Entry(i, (float) data.getTotalExpense()));

                    labels.add(data.getDate());
                }

                LineDataSet incomeDataSet = new LineDataSet(incomeEntries, "Income");
                incomeDataSet.setColor(getResources().getColor(R.color.greenBack));
                incomeDataSet.setCircleColor(getResources().getColor(R.color.greenBack));
                incomeDataSet.setLineWidth(2f);
                incomeDataSet.setValueTextColor(getResources().getColor(R.color.black));
                incomeDataSet.setValueTextSize(10f);

                LineDataSet expenseDataSet = new LineDataSet(expenseEntries, "Expense");
                expenseDataSet.setColor(getResources().getColor(R.color.red));
                expenseDataSet.setCircleColor(getResources().getColor(R.color.red));
                expenseDataSet.setLineWidth(2f);
                expenseDataSet.setValueTextColor(getResources().getColor(R.color.black));
                expenseDataSet.setValueTextSize(10f);

                LineData lineData = new LineData(incomeDataSet, expenseDataSet);
                lineChart.setData(lineData);

                // Customize X-Axis
                XAxis xAxis = lineChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setGranularity(1f);
                xAxis.setGranularityEnabled(true);

                // Customize Y-Axis
                lineChart.getAxisRight().setEnabled(false); // Disable right Y-Axis

                // Enable interactions
                lineChart.setDragEnabled(true);        // Enable dragging (scrolling)
                lineChart.setScaleEnabled(true);       // Enable zoom
                lineChart.setVisibleXRangeMaximum(5);  // Show maximum of 5 entries at a time
                lineChart.moveViewToX(labels.size());  // Move to the latest entry

                lineChart.getDescription().setEnabled(false); // Disable description label
                lineChart.invalidate(); // Refresh the chart
            }
        });


        return view;
    }
}
