package com.example.nihongoobenkyou.ViewPager.FragmentHiraganaKatakana;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.adpter.RecyclerViewAdpterHiragana;
import com.example.nihongoobenkyou.databinding.FragmentHiraganaBinding;
import com.example.nihongoobenkyou.databinding.FragmentHiraganaScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class HiraganaFragment extends Fragment {

    private FragmentHiraganaBinding binding;
    private List<List<String>> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHiraganaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext().getApplicationContext());
        binding.recyclerViewHiragana.setLayoutManager(layoutManager);

        RecyclerViewAdpterHiragana adpter = new RecyclerViewAdpterHiragana(list);
        binding.recyclerViewHiragana.setAdapter(adpter);

        addList("1","2","3","4","5");
        addList2("1","2","3");
        addList3("1","2");
        addList4("1");

        return view;
    }
    private  void addList(String text_1 , String text_2 , String text_3 , String text_4 , String text_5){
        List<String> listinha = new ArrayList<>();

        listinha.add(text_1);
        listinha.add(text_2);
        listinha.add(text_3);
        listinha.add(text_4);
        listinha.add(text_5);

        list.add(new ArrayList<String>());
        list.get(list.size()-1).addAll(listinha);

    }
    private  void addList2(String text_1 , String text_2, String text_3){
        List<String> listinha = new ArrayList<>();

        listinha.add(text_1);
        listinha.add(text_2);
        listinha.add(text_3);

        list.add(new ArrayList<String>());
        list.get(list.size()-1).addAll(listinha);

    }
    private  void addList3(String text_1 , String text_2){
        List<String> listinha = new ArrayList<>();

        listinha.add(text_1);
        listinha.add(text_2);

        list.add(new ArrayList<String>());
        list.get(list.size()-1).addAll(listinha);

    }
    private  void addList4(String text_1){
        List<String> listinha = new ArrayList<>();

        listinha.add(text_1);

        list.add(new ArrayList<String>());
        list.get(list.size()-1).addAll(listinha);

    }



}