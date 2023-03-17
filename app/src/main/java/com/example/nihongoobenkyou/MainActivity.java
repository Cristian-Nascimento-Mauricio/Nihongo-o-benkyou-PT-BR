package com.example.nihongoobenkyou;


import static com.example.nihongoobenkyou.R.color.black;
import static com.example.nihongoobenkyou.R.color.white;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.nihongoobenkyou.DataBase.AppDataBase;
import com.example.nihongoobenkyou.Interfaces.InterfaceHTML;
import com.example.nihongoobenkyou.ViewPager.Fragments.KanjiScreenFragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.articleScreenFragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.hiraganaScreenFragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.middleScreenfragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.vocabularyScreenFragment;
import com.example.nihongoobenkyou.ViewPager.ViewPagerAdpter;
import com.example.nihongoobenkyou.activity.OpenhtmlActivity;
import com.example.nihongoobenkyou.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ViewPager2  viewPager2;
    private ActivityMainBinding binding;
    private androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        StartDataBase();


        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

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

        binding.ofensiva.setText(String.valueOf(ofensivas()));


    }

    private int ofensivas(){

        final String ARQUIVO = "dateOfUser";
        final String LastDaykey = "LastDayOfStreak";
        final String Daykey = "DayStreak";

        SharedPreferences preferences = getSharedPreferences(ARQUIVO,0);
        SharedPreferences.Editor editor = preferences.edit();
        Boolean sixth_year = false;

        if(preferences.contains(LastDaykey) & preferences.contains(Daykey)) {

            Calendar calendar = Calendar.getInstance();

            int Last_day_of_streak = preferences.getInt(LastDaykey,-1);
            int dayStreak = preferences.getInt(Daykey,-1);

            int today = calendar.get(calendar.DAY_OF_YEAR);

            if (today == Last_day_of_streak)
                return preferences.getInt(Daykey,-1);

            if(calendar.get(calendar.YEAR) % 4 == 0)
                sixth_year = true;

            if ( ((Last_day_of_streak == 366 && today == 1 )&& sixth_year) || (today - Last_day_of_streak == 1) || (Last_day_of_streak == 365 && today == 1) )
                    dayStreak++;

            else
                dayStreak = 1;

            Last_day_of_streak = today;


            editor.putInt(LastDaykey,Last_day_of_streak);
            editor.putInt(Daykey,dayStreak);
            editor.commit();

            editor.commit();
            }else{

            editor.putInt(LastDaykey,1);
            editor.putInt(Daykey,1);

            editor.commit();

            ofensivas();

        }

        return preferences.getInt(Daykey,-1);

    }

    private void configViewPager(){
        ViewPagerAdpter adpter = new ViewPagerAdpter(this);
        binding.ViewPager.setAdapter(adpter);

        adpter.addFragment(new articleScreenFragment());
        adpter.addFragment(new vocabularyScreenFragment());
        adpter.addFragment(new middleScreenfragment());
        adpter.addFragment(new KanjiScreenFragment());
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
    private void StartDataBase( ){
        AppDataBase appDataBase = new AppDataBase(this);

        
        File database = getApplicationContext().getDatabasePath(AppDataBase.db_NAME);

        if(!database.exists()){
            appDataBase.getReadableDatabase();

            if(copyDB(this))
                Toast.makeText(this, "Funcinou :)", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Não Funcinou :(", Toast.LENGTH_SHORT).show();

        }

    }

    private boolean copyDB(Context context) {

        try {
            // Abrir o banco de dados da pasta asset
            InputStream inputStream = context.getAssets().open(AppDataBase.db_NAME);
            // Criar uma cópia do banco de dados na pasta database
            String outFile = context.getDatabasePath(AppDataBase.db_NAME).getPath();
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            // Abrir os bancos de dados (asset e database)
            SQLiteDatabase assetDB = SQLiteDatabase.openDatabase(context.getDatabasePath(AppDataBase.db_NAME).getPath(), null, SQLiteDatabase.OPEN_READWRITE);
            SQLiteDatabase database = SQLiteDatabase.openDatabase(outFile, null, SQLiteDatabase.OPEN_READWRITE);

            // Copiar todas as tabelas e seus valores
            Cursor cursor = assetDB.rawQuery("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tableName = cursor.getString(0);
                database.execSQL("DROP TABLE IF EXISTS " + tableName);
                assetDB.execSQL("ATTACH DATABASE '" + outFile + "' AS newDb");
                assetDB.execSQL("CREATE TABLE newDb." + tableName + " AS SELECT * FROM " + tableName);
                assetDB.execSQL("DETACH DATABASE newDb");
                cursor.moveToNext();
            }
            cursor.close();
            assetDB.close();
            database.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}