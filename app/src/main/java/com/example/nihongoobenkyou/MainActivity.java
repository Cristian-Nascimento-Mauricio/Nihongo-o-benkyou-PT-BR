package com.example.nihongoobenkyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.nihongoobenkyou.ViewPager.Fragments.KanjiScreenFragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.articleScreenFragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.hiraganaScreenFragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.middleScreenfragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.vocabularyScreenFragment;
import com.example.nihongoobenkyou.ViewPager.ViewPagerAdpter;
import com.example.nihongoobenkyou.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ViewPager2  viewPager2;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager2  = findViewById(R.id.ViewPager);

        configViewPager();



        binding.buttonArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ViewPager.setCurrentItem(0);

            }
        });
        binding.buttonVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ViewPager.setCurrentItem(1);

            }
        });

        binding.buttonKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ViewPager.setCurrentItem(3);

            }
        });

        binding.buttonHiragana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ViewPager.setCurrentItem(4);

            }
        });

    }

    private void configViewPager(){
        ViewPagerAdpter adpter = new ViewPagerAdpter(this);
        binding.ViewPager.setAdapter(adpter);

        adpter.addFragment(new articleScreenFragment());
        adpter.addFragment(new KanjiScreenFragment());
        adpter.addFragment(new middleScreenfragment());
        adpter.addFragment(new vocabularyScreenFragment());
        adpter.addFragment(new hiraganaScreenFragment());

        binding.ViewPager.setCurrentItem(2);

        binding.ViewPager.setOffscreenPageLimit(adpter.getItemCount());

    }


}