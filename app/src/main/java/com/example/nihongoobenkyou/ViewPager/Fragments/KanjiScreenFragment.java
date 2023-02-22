package com.example.nihongoobenkyou.ViewPager.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.adpter.RecyclerViewAdpterKanji;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.databinding.FragmentKanjiScreenBinding;
import com.example.nihongoobenkyou.databinding.FragmentVocabularyScreenBinding;

import java.util.ArrayList;
import java.util.List;


public class KanjiScreenFragment extends Fragment {

    private FragmentKanjiScreenBinding binding;
    private List<String> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentKanjiScreenBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext().getApplicationContext());
        binding.recyclerViewKanji.setLayoutManager(layoutManager);
        RecyclerViewAdpterKanji adpter = new RecyclerViewAdpterKanji(list);

        binding.recyclerViewKanji.setAdapter(adpter);

        list.add("NUMERAIS. 一 二 三");
        list.add("ELEMENTOS, 水 火 風");
        list.add("CORES, 赤 緑 青");
        list.add("FAMÍLIA, 母 父 妹");
        list.add("ANIMAIS, 鳥 猫 犬");
        list.add("DIREÇÃO, 左 右 上");
        list.add("TEMPO, 今 日 月");


        return view;
    }
}