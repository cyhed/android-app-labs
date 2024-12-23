package com.example.lab4fragmentsandsqlite;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lab4fragmentsandsqlite.Fragments.AddFragment;
import com.example.lab4fragmentsandsqlite.Fragments.DelFragment;
import com.example.lab4fragmentsandsqlite.Fragments.ShowFragment;
import com.example.lab4fragmentsandsqlite.Fragments.UpdateFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    static final int PAGE_COUNT = 4;
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new ShowFragment();
            case 1: return new AddFragment();
            case 2: return new DelFragment();
            case 3: return new UpdateFragment();
            default: return null;
        }
    }
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    // при необходимости добавляем верхнее меню вкладок с заголовками
    @Override
    public CharSequence getPageTitle(int i) {
        switch (i){
            case 0: return "ShowFragment";
            case 1: return "AddFragment";
            case 2: return "DelFragment";
            case 3: return "UpdateFragment";
            default: return null;
        }
    }
}
