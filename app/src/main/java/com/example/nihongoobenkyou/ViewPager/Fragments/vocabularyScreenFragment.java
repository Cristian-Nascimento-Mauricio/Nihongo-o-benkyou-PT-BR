package com.example.nihongoobenkyou.ViewPager.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.adpter.RecyclerViewAdpterVocabularyScreen;
import com.example.nihongoobenkyou.classes.Vocabulary_of_Vocabulary_Screen;
import com.example.nihongoobenkyou.databinding.FragmentVocabularyScreenBinding;

import java.util.ArrayList;
import java.util.List;


public class vocabularyScreenFragment extends Fragment {

    private FragmentVocabularyScreenBinding binding;
    private List<Vocabulary_of_Vocabulary_Screen> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentVocabularyScreenBinding.inflate(inflater,container,false);
        View view = binding.getRoot();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext().getApplicationContext());
        binding.recyclerViewVocabulary.setLayoutManager(layoutManager);
        RecyclerViewAdpterVocabularyScreen adpter = new RecyclerViewAdpterVocabularyScreen(list);

        binding.recyclerViewVocabulary.setAdapter(adpter);

        Vocabulary_of_Vocabulary_Screen n = new Vocabulary_of_Vocabulary_Screen(
                "Título",
                "さくらさん: あなたはだれですか" ,
                "アナさん　: 私はアナです",
                "さくらさん: おろしくお願いしまうすアナさん" );

        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);

        return view;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}