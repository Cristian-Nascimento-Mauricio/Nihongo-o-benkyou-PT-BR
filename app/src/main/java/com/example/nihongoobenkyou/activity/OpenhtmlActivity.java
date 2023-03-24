package com.example.nihongoobenkyou.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nihongoobenkyou.R;

import java.io.IOException;
import java.io.InputStream;

public class OpenhtmlActivity extends AppCompatActivity {

    TextView textView;
    ImageButton buttonReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.openhtml);

        textView = findViewById(R.id.TextviewHTML);
        buttonReturn = findViewById(R.id.buttonReturn);

        try {
            // Abrir o arquivo HTML
            InputStream is = getAssets().open(returnPath());
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            
            // Converter o conteúdo HTML para String
            String htmlContent = new String(buffer);

            textView.setText(Html.fromHtml(htmlContent));
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }
    private String returnPath(){
        Intent intent = getIntent();

        Log.i("folder", ""+intent.getExtras().getString("folder") + intent.getExtras().getString("key") +".html");

        return intent.getExtras().getString("folder") + intent.getExtras().getString("key") +".html";

    }

}