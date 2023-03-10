package com.example.nihongoobenkyou.ViewPager.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.adpter.RecylerViewAdpterScreenMiddle;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.databinding.FragmentMiddleScreenfragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;

public class middleScreenfragment extends Fragment {

    private FragmentMiddleScreenfragmentBinding binding;
    private List<List<Nivels_of_Screen_Middle>> nivelsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMiddleScreenfragmentBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext().getApplicationContext());
        binding.recyclerViewMiddle.setLayoutManager(layoutManager);

        RecylerViewAdpterScreenMiddle adpter = new RecylerViewAdpterScreenMiddle(nivelsList);

        binding.recyclerViewMiddle.setAdapter(adpter);

        Controller controller = new Controller(view.getContext());

        Drawable drawable = view.getContext().getResources().getDrawable(R.drawable.fire);

        orderList(controller.Selecionar());

        return view;

    }

    private void orderList(List<Nivels_of_Screen_Middle> list){
        byte[] ordenacao = { 1 , 2 , 3 , 3 , 2 , 2 , 1 , 1 , 2 , 3 , 1, 3 , 3 , 3 , 2 , 2 , 2 , 3 , 1 , 2 , 3 , 2 , 2 , 2 , 1 , 1 };
        int postion = 0;

        for (int item:ordenacao) {
            List<Nivels_of_Screen_Middle> organization = new ArrayList<>(3);

            for(int i = 0; i < item;i++)
                organization.add(list.get(postion+i));

            nivelsList.add(new ArrayList<Nivels_of_Screen_Middle>());
            this.nivelsList.get(nivelsList.size()-1).addAll(organization);

            organization.clear();

            postion+=item;
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}