package com.example.nihongoobenkyou.ViewPager.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.controls.Control;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.Interfaces.InterfaceHTML;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.activity.OpenhtmlActivity;
import com.example.nihongoobenkyou.adpter.RecyclerViewAdpterKanji;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.databinding.FragmentKanjiScreenBinding;
import com.example.nihongoobenkyou.databinding.FragmentVocabularyScreenBinding;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class KanjiScreenFragment extends Fragment implements InterfaceHTML {

    private FragmentKanjiScreenBinding binding;
    private List<String> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        binding = FragmentKanjiScreenBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext().getApplicationContext());
        binding.recyclerViewKanji.setLayoutManager(layoutManager);
        RecyclerViewAdpterKanji adpter = new RecyclerViewAdpterKanji(list,this::openActivityHTML);

        Controller controller = new Controller(view.getContext());

        binding.recyclerViewKanji.setAdapter(adpter);

        list.addAll(controller.SelecionarKanjis());

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void openActivityHTML(String text) {
        Intent intent = new Intent(getActivity(), OpenhtmlActivity.class);

        intent.putExtra("folder","kanjis/");
        intent.putExtra("key",deAccent(text).toLowerCase().trim());

        startActivity(intent);
    }
    private String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}