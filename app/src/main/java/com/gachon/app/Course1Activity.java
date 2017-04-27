package com.gachon.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Course1Activity extends AppCompatActivity {

    ViewPager course1ViewPager;
    static int courseStepNum = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        course1ViewPager = (ViewPager)findViewById(R.id.course1ViewPager);
        course1ViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        course1ViewPager.setCurrentItem(1);

    }

    private class PagerAdapter extends FragmentStatePagerAdapter
    {
        public PagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 1:
                    return new Course1Step1Fragment();
                case 2:
                    return new Course1Step2Fragment();
                case 3:
                    return new Course1Step3Fragment();
                case 4:
                    return new Course1Step4Fragment();
                case 5:
                    return new Course1Step5Fragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return courseStepNum;
        }
    }
}
