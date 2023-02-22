package com.example.nihongoobenkyou.ViewPager;



import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdpter extends FragmentStateAdapter {

    private List<Fragment> FragmentList = new ArrayList<>();
    private List<String> StringList = new ArrayList<>();

    public ViewPagerAdpter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public String getTitle(int position){

        return StringList.get(position);
    }


    public void addFragment(Fragment fragment){
        FragmentList.add(fragment);

    }


    public void addFragment(Fragment fragment,String string){
        FragmentList.add(fragment);
        StringList.add(string);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return FragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return FragmentList.size();
    }
}


