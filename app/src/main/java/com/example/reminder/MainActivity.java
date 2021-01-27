package com.example.reminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionPagerAdapter sectionPagerAdapter=new SectionPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(sectionPagerAdapter);

        TabLayout tabLayout=(TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    public void createSchedule(View view){
        Intent intent=new Intent(this,CreateScheduleActivity.class);
        startActivity(intent);
    }

    private class SectionPagerAdapter extends FragmentPagerAdapter{

        public SectionPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                return new HomeFragment();
                case 1:
                        return new ArmsFragment();
                case 2:
                    return new AbdominalsFragment();
                case 3:
                    return new BackFragment();
                case 4:
                    return new ChestFragment();
                case 5:
                    return new LegsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Home";
                case 1:
                    return "Arms";
                case 2:
                    return "Back";
                case 3:
                    return "Chest";
                case 4:
                    return "Legs";

            }
            return null;
        }
    }
}