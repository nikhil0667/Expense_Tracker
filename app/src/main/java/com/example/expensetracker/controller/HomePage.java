package com.example.expensetracker.controller;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.expensetracker.ChartFragment;
import com.example.expensetracker.HomeFragment;
import com.example.expensetracker.ProfileFragment;
import com.example.expensetracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked },
                new int[] { -android.R.attr.state_checked }
        };

        int[] colors = new int[] {
                getResources().getColor(R.color.selected_color),
                getResources().getColor(R.color.unselected_color)
        };

        ColorStateList colorStateList = new ColorStateList(states, colors);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setItemIconTintList(colorStateList);
        Fragment homeFragment = new HomeFragment();
        Fragment chartFragment = new ChartFragment();
        Fragment profileFragment = new ProfileFragment();
        setCurrentFragment(homeFragment);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.homeIcon) {
                setCurrentFragment(homeFragment);

            } else if (item.getItemId() == R.id.chartIcon) {
                setCurrentFragment(chartFragment);
            } else if (item.getItemId() == R.id.profileIcon) {
                setCurrentFragment(profileFragment);
            }

            return true;
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}