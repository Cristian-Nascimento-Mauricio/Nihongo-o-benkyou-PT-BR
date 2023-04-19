package com.example.nihongoobenkyou;


import static com.example.nihongoobenkyou.R.color.black;
import static com.example.nihongoobenkyou.R.color.white;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.DataBase.AppDataBase;
import com.example.nihongoobenkyou.Interfaces.InterfaceHTML;
import com.example.nihongoobenkyou.ViewPager.Fragments.KanjiScreenFragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.articleScreenFragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.hiraganaScreenFragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.middleScreenfragment;
import com.example.nihongoobenkyou.ViewPager.Fragments.vocabularyScreenFragment;
import com.example.nihongoobenkyou.ViewPager.ViewPagerAdpter;
import com.example.nihongoobenkyou.activity.OpenhtmlActivity;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ViewPager2  viewPager2;
    private ActivityMainBinding binding;
    private androidx.appcompat.widget.Toolbar toolbar;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_VERSION_CODE_KEY = "version_code";
    private final int[] buttonPositions = {0, 1, 3, 4, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS},
                    1);
        }

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
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                binding.ViewPager.setCurrentItem(buttonPositions[position]);
            }
        };

        // Configurar o listener para cada botão
        binding.buttonArticle.setOnClickListener(buttonClickListener);
        binding.buttonVocabulary.setOnClickListener(buttonClickListener);
        binding.buttonKanji.setOnClickListener(buttonClickListener);
        binding.buttonHiragana.setOnClickListener(buttonClickListener);
        binding.buttonMiddle.setOnClickListener(buttonClickListener);

        // Definir a tag de posição para cada botão
        binding.buttonArticle.setTag(0);
        binding.buttonVocabulary.setTag(1);
        binding.buttonKanji.setTag(2);
        binding.buttonHiragana.setTag(3);
        binding.buttonMiddle.setTag(4);

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

        int currentVersionCode = BuildConfig.VERSION_CODE;
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, -1);

        if (currentVersionCode > savedVersionCode) {

            File database = getApplicationContext().getDatabasePath(AppDataBase.db_NAME);


            if (!database.exists()) {

                if (copyDB(this))
                    Toast.makeText(this, "Funcinou :)", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Não Funcinou :(", Toast.LENGTH_SHORT).show();

            }
            SQLiteDatabase DBold = SQLiteDatabase.openDatabase(getDatabasePath(AppDataBase.db_NAME).getPath(), null, SQLiteDatabase.OPEN_READONLY);

            if (getAssetDatabaseVersion(this) >= DBold.getVersion()) {
                File tempFolder = new File(getCacheDir(), "temporal");
                tempFolder.mkdirs();

                // Cria um objeto File para o arquivo temporário do banco de dados
                File tempFile = new File(tempFolder, AppDataBase.db_NAME);
                SQLiteDatabase DBnew = SQLiteDatabase.openDatabase(tempFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
                Controller controller = new Controller(getApplicationContext());

                List<Nivels_of_Screen_Middle> list = controller.Selecionar();

                ContentValues contentValues = new ContentValues();

                for (Nivels_of_Screen_Middle count : list) {
                    contentValues.put("level", count.getlevel());

                    DBnew.update("rcylevel", contentValues, "id=?", new String[]{String.valueOf(count.getIdOfdb())});

                    contentValues.clear();
                }

            }
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
        }
    }

    private boolean copyDB(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(AppDataBase.db_NAME);
            String outfile = context.getDatabasePath(AppDataBase.db_NAME).getPath();
            OutputStream outputStream = new FileOutputStream(outfile);
            byte[] buft = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buft)) > 0) {
                outputStream.write(buft, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    private int getAssetDatabaseVersion(Context context) {
        int version = 0;

        try {
            // Abre o arquivo do banco de dados na pasta "assets"
            InputStream inputStream = context.getAssets().open(AppDataBase.db_NAME);

            // Cria uma pasta temporária para o banco de dados
            File tempFolder = new File(context.getCacheDir(), "temporal");
            tempFolder.mkdirs();

            // Cria um objeto File para o arquivo temporário do banco de dados
            File tempFile = new File(tempFolder, AppDataBase.db_NAME);

            // Copia o arquivo do banco de dados para a pasta temporária
            OutputStream outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            // Abre o banco de dados a partir do arquivo temporário
            SQLiteDatabase db = SQLiteDatabase.openDatabase(tempFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);

            // Obtém a versão do banco de dados
            version = db.getVersion();

            // Fecha o banco de dados
            db.close();

            Log.i("eita", "version: " + version);
            // Deleta o arquivo temporário
            /*
            tempFile.delete();
            tempFolder.delete();
            */

        } catch (IOException e) {
            e.printStackTrace();
        }

        return version;

    }



}