package com.example.submission4.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmenTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        this.fragmentList.add(fragment);
        this.fragmenTitleList.add(title);
    }

    public CharSequence getPageTitle(int position){
        return fragmenTitleList.get(position);
    }

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
