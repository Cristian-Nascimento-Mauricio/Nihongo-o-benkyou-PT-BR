package com.example.nihongoobenkyou.ViewPager.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.Interfaces.InterfaceDialogue;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.activity.OpenDialogueActivity;
import com.example.nihongoobenkyou.activity.OpenhtmlActivity;
import com.example.nihongoobenkyou.adpter.RecyclerViewAdpterVocabularyScreen;
import com.example.nihongoobenkyou.classes.Vocabulary_of_Vocabulary_Screen;
import com.example.nihongoobenkyou.databinding.FragmentVocabularyScreenBinding;

import java.util.ArrayList;
import java.util.List;


public class vocabularyScreenFragment extends Fragment implements InterfaceDialogue {

    private FragmentVocabularyScreenBinding binding;
    private List<Vocabulary_of_Vocabulary_Screen> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentVocabularyScreenBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext().getApplicationContext());
        binding.recyclerViewVocabulary.setLayoutManager(layoutManager);
        RecyclerViewAdpterVocabularyScreen adpter = new RecyclerViewAdpterVocabularyScreen(list,this::openDialogueActivity);

        binding.recyclerViewVocabulary.setAdapter(adpter);

        Controller controller = new Controller(view.getContext());

        list.addAll(controller.SelecionarVocabulario());

        return view;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void openDialogueActivity(String text) {

        Intent intent = new Intent(getActivity(), OpenDialogueActivity.class);

        startActivity(intent);

    }
}