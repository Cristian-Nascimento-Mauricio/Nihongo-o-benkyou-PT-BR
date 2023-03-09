package com.example.nihongoobenkyou.ViewPager.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.adpter.RecylerViewAdpterScreenMiddle;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.databinding.FragmentMiddleScreenfragmentBinding;

import java.util.ArrayList;
import java.util.List;
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



        addInList(0 ,"Texto 0", R.drawable.fire);
        AddInList2(10,"texto 1",R.drawable.fire,
                   15, "Texto 2", R.drawable.fire);
        AddInList3(14,"texto 3",R.drawable.fire,
                   16, "Texto 4", R.drawable.fire,
                   20,"texto 5",R.drawable.fire);
        AddInList3(7,"texto 3",R.drawable.fire,
                6, "Texto 4", R.drawable.fire,
                7,"texto 5",R.drawable.fire);
        AddInList3(8,"texto 3",R.drawable.fire,
                9, "Texto 4", R.drawable.fire,
                10,"texto 5",R.drawable.fire);
        AddInList3(10,"texto 3",R.drawable.fire,
                10, "Texto 4", R.drawable.fire,
                10,"texto 5",R.drawable.fire);
        AddInList3(10,"texto 3",R.drawable.fire,
                10, "Texto 4", R.drawable.fire,
                20,"texto 5",R.drawable.fire);
        addInList(20 ,"Texto 0",R.drawable.fire);
        AddInList2(20,"texto 1",R.drawable.fire,
                0, "Texto 2", R.drawable.fire);
        addInList(-1 ,"Texto 0",R.drawable.fire);
        AddInList2(-1,"texto 1",R.drawable.fire,
                -1, "Texto 2", R.drawable.fire);
        addInList(-1 ,"Texto 0",R.drawable.fire);
        AddInList2(-1,"texto 1",R.drawable.fire,
                -1, "Texto 2", R.drawable.fire);
        addInList(-1     ,"Texto 19",R.drawable.fire);
        AddInList2(-1,"texto 1",R.drawable.fire,
                -1, "Texto 2", R.drawable.fire);
        addInList(-1 ,"Texto 0",R.drawable.fire);
        AddInList2(-1,"texto 1",R.drawable.fire,
                -1, "Texto 2", R.drawable.fire);
        addInList(-1 ,"Texto 0",R.drawable.fire);
        AddInList2(-1,"texto 1",R.drawable.fire,
                -1, "Texto 2", R.drawable.fire);
        addInList(-1 ,"Texto 0",R.drawable.fire);
        AddInList2(-1,"texto 1",R.drawable.fire,
                -1, "Texto 2", R.drawable.fire);
        addInList(-1 ,"Texto 0",R.drawable.fire);
        AddInList2(-1,"texto 1",R.drawable.fire,
                -1, "Texto 2", R.drawable.fire);
        addInList(-1 ,"Texto 0",R.drawable.fire);
        AddInList2(-1,"texto 1",R.drawable.fire,
                -1, "Texto 2", R.drawable.fire);



        return view;

    }
    private void addInList(int progressBar,String textView,int imagemView){
        Nivels_of_Screen_Middle nivel = new Nivels_of_Screen_Middle(progressBar,textView, imagemView);

        nivelsList.add(new ArrayList<Nivels_of_Screen_Middle>(1));
        this.nivelsList.get(nivelsList.size() -1 ).add(nivel);

    }
    private void AddInList2(int progressBar,String textView,int imagemView,
                            int progressBar2,String textView2,int imagemView2){

        List<Nivels_of_Screen_Middle> listinha = new ArrayList<>(2);

        Nivels_of_Screen_Middle nivel = new Nivels_of_Screen_Middle(progressBar,textView, imagemView);
        listinha.add(nivel);
        Nivels_of_Screen_Middle nivel2 = new Nivels_of_Screen_Middle(progressBar2,textView2, imagemView2);
        listinha.add(nivel2);

        nivelsList.add(new ArrayList<Nivels_of_Screen_Middle>());
        this.nivelsList.get(nivelsList.size()-1).addAll(listinha);

    }
    private void AddInList3(int progressBar ,String textView ,int imagemView ,
                            int progressBar2,String textView2,int imagemView2,
                            int progressBar3,String textView3,int imagemView3){

        List<Nivels_of_Screen_Middle> listinha = new ArrayList<>( 3);

        Nivels_of_Screen_Middle nivel = new Nivels_of_Screen_Middle(progressBar,textView, imagemView);
        listinha.add(nivel);
        Nivels_of_Screen_Middle nivel2 = new Nivels_of_Screen_Middle(progressBar2,textView2, imagemView2);
        listinha.add(nivel2);
        Nivels_of_Screen_Middle nivel3 = new Nivels_of_Screen_Middle(progressBar3,textView3, imagemView3);
        listinha.add(nivel3);

        nivelsList.add(new ArrayList<Nivels_of_Screen_Middle>());
        this.nivelsList.get(nivelsList.size()-1).addAll(listinha);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}