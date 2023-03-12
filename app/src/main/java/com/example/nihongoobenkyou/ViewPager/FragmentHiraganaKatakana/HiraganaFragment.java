package com.example.nihongoobenkyou.ViewPager.FragmentHiraganaKatakana;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongoobenkyou.Controllers.Controller;
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

        Controller controller = new Controller(view.getContext());

        list.addAll(controller.SelecionarHiragana("hiragana"));


        return view;
    }



}