package com.example.beau;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import androidx.appcompat.widget.Toolbar;

import com.example.beau.ui.main.SectionsPagerAdapter;

public class RoutineActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        toolbar=(Toolbar)findViewById(R.id.mytoolbar);
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        tabLayout=(TabLayout) findViewById(R.id.tabs);

        setSupportActionBar(toolbar);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new morningroutine(),"Morning");
        viewPagerAdapter.addFragment(new nightroutine(),"Night");
        viewPager.setAdapter(viewPagerAdapter);
    }
}