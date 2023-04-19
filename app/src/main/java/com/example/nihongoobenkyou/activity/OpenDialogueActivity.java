package com.example.nihongoobenkyou.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.adpter.RecyclerViewActivityDialogue;
import com.example.nihongoobenkyou.adpter.RecyclerViewAdpterArticles;

import java.util.ArrayList;
import java.util.List;

public class OpenDialogueActivity extends AppCompatActivity {
    Button button;
    int positon = 0;
    List<Pair<String,ArrayList>> list = new ArrayList();
    List<Pair<String,ArrayList>> listinha = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_dialogue);

        button = findViewById(R.id.btnAddInList);

        RecyclerView recyclerView = findViewById(R.id.rcyDialogue);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewActivityDialogue adpter = new RecyclerViewActivityDialogue(list);

        Controller controller = new Controller(getApplicationContext());

        recyclerView.setAdapter(adpter);

        listinha.add(new Pair<String, ArrayList>("SpeechLeft",new ArrayList()));
        listinha.add(new Pair<String, ArrayList>("SpeechRight",new ArrayList()));
        listinha.add(new Pair<String, ArrayList>("Audio",new ArrayList()));
        listinha.add(new Pair<String, ArrayList>("Question_version_1",new ArrayList()));
        listinha.add(new Pair<String, ArrayList>("Question_version_2",new ArrayList<>()));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(positon < listinha.size()) {
                    list.add(listinha.get(positon));
                    adpter.notifyDataSetChanged();

                    positon++;
                }
            }
        });



    }

}