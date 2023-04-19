package com.example.nihongoobenkyou.ViewPager.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.ViewPager.FragmentHiraganaKatakana.HiraganaFragment;
import com.example.nihongoobenkyou.ViewPager.FragmentHiraganaKatakana.KatakanaFragment;
import com.example.nihongoobenkyou.ViewPager.ViewPagerAdpter;
import com.example.nihongoobenkyou.databinding.FragmentHiraganaScreenBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class hiraganaScreenFragment extends Fragment {

    private FragmentHiraganaScreenBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHiraganaScreenBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        configViewPager();

        return view;
    }

    private void configViewPager(){
        ViewPagerAdpter adpter = new ViewPagerAdpter(getActivity());
        binding.HiraganaViewPager.setAdapter(adpter);

        adpter.addFragment(new HiraganaFragment(),"Hiragana");
        adpter.addFragment(new KatakanaFragment(),"Katakana");

        binding.HiraganaViewPager.setUserInputEnabled(false);

        binding.HiraganaViewPager.setOffscreenPageLimit(adpter.getItemCount());

        TabLayoutMediator mediator = new TabLayoutMediator(binding.tabLayout,binding.HiraganaViewPager,
                ((tab, position) -> {
                  tab.setText(adpter.getTitle(position));

                }));
        mediator.attach();


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}