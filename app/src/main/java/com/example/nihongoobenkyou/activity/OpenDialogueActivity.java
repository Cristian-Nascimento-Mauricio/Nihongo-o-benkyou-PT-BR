package com.example.nihongoobenkyou.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.adpter.RecyclerViewActivityDialogue;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Audio;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Inters_of_dialogues;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Question_version_1;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Question_version_2;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Speech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpenDialogueActivity extends AppCompatActivity {
    Button button;
    int positon = 0;
    List<Inters_of_dialogues> list = new ArrayList();
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

        recyclerView.setAdapter(adpter);

        listinha.add(new Pair<String, ArrayList>("SpeechLeft",new ArrayList()));
        listinha.add(new Pair<String, ArrayList>("SpeechRight",new ArrayList()));
        listinha.add(new Pair<String, ArrayList>("Audio",new ArrayList()));
        listinha.add(new Pair<String, ArrayList>("Question_version_1",new ArrayList()));
        listinha.add(new Pair<String, ArrayList>("Question_version_2",new ArrayList<>()));

        List<String> n1 = Arrays.asList("私はAnnaですよろしくお願いします。", "僕はAnnaですよろしくお願いいたします。。",
                "私はSakuraでよろしくお願いします。", "私はAnnnaですよろしくお願いいたします。", "僕はSakuraですよろしくお願いいたします。");
        List<String> n2 = Arrays.asList("watashi", "ha", "roku", "sakura", "boku","wo","Anna","yoroshiku onegaishimasu" , "arigatou" , "sensei" , "-san" , "Nihon" );


        Controller controller = new Controller(this);

        List<Speech> listSpeech = controller.SelecionarTextoEAudio();

        Inters_of_dialogues[] dialogs = new Inters_of_dialogues[6];
        dialogs[0] = listSpeech.get(0);
        dialogs[1] = listSpeech.get(1);
        dialogs[2] = listSpeech.get(2);
        dialogs[3] = new Audio("audios/speechs/audio_1.mp3");
        dialogs[4] = new Audio("audios/speechs/audio_1.mp3");
        //dialogs[4] = new Question_version_1(n1);
        dialogs[5] = new Question_version_2("watashi ha sakura-san",n2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(positon < 6) {
                    list.add(dialogs[positon]);
                    adpter.notifyItemInserted(positon);

                    positon++;
                }
            }
        });


    }

}