package com.example.nihongoobenkyou.ViewPager.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.adpter.RecyclerViewAdpterArticles;
import com.example.nihongoobenkyou.adpter.RecyclerViewAdpterKanji;
import com.example.nihongoobenkyou.classes.Articles_of_Article_Screen;
import com.example.nihongoobenkyou.databinding.FragmentArticleScreenBinding;
import com.example.nihongoobenkyou.databinding.FragmentKanjiScreenBinding;

import java.util.ArrayList;
import java.util.List;


public class articleScreenFragment extends Fragment {

    private FragmentArticleScreenBinding binding;
    private List<Articles_of_Article_Screen> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentArticleScreenBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext().getApplicationContext());
        binding.recyclerViewArticle.setLayoutManager(layoutManager);
        RecyclerViewAdpterArticles adpter = new RecyclerViewAdpterArticles(list);

        Controller controller = new Controller(view.getContext());

        binding.recyclerViewArticle.setAdapter(adpter);

        list.addAll(controller.SelecionarArtigo());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}
