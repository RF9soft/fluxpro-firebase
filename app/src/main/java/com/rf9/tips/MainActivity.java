package com.rf9.tips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.multidex.BuildConfig;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.rf9.tips.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabLayout = findViewById(R.id.tabLayoutID);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0, true);
        //tabLayout.getTabAt(0).setIcon(R.drawable.moneybag);
        //tabLayout.getTabAt(1).setIcon(R.drawable.medal);
        //tabLayout.setTabIconTint(null);
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed(); // Call super method to ensure default behavior (optional)
    }
}