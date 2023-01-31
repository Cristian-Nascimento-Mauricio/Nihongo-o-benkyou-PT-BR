package com.example.nihongoobenkyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

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
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
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

        binding.buttonMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ViewPager.setCurrentItem(2);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_perfil:

                break;
            case R.id.menu_configuracao:

                break;
            case R.id.menu_apoiar:

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}