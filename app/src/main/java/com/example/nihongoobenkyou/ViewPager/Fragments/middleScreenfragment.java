package com.example.nihongoobenkyou.ViewPager.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongoobenkyou.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link middleScreenfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class middleScreenfragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_middle_screenfragment, container, false);
    }
}