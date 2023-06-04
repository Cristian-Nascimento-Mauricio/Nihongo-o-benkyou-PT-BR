package com.example.nihongoobenkyou.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.Interfaces.InterfaceCondition;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.adpter.RecyclerViewActivityDialogue;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Audio;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Inters_of_dialogues;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Question_version_1;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Question_version_2;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Speech;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class OpenDialogueActivity extends AppCompatActivity implements InterfaceCondition {
    Button button;
    int positon = 0;
    Boolean Boolean= true;
    List<Inters_of_dialogues> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_dialogue);

        button = findViewById(R.id.btnAddInList);

        RecyclerView recyclerView = findViewById(R.id.rcyDialogue);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewActivityDialogue adpter = new RecyclerViewActivityDialogue(list , this::Codition,this);

        recyclerView.setAdapter(adpter);

        List<String> n1 = Arrays.asList("初めまして、さくらさん。", "初めまして、アンナ。",
                "初まして、アンナ。", "初めまして、さくらさん。", "初めまして、ぼろ疎なる");
        List<String> n2 = Arrays.asList("ブラジル人、","日本語", "を","勉強", "しますのために","行きましたへ","日本。");
        List<String> n3 = Arrays.asList("字幕なしでアニメを見ます。", "字幕でアニメを見ます。" ,"字幕なしでanimeを見ます。","字幕なしでアニメを見ました。","字幕なしであにめを見ます。" );
        List<String> n4 = Arrays.asList("すごい、あなたのお気に入りのアニメは何ですか。" , "あなたのお気に入りのアニメは何ですか。" , "すごい、あなたのお気にりのアニメは何ですか。",
                "すごい、あなたのお気に入りのアニメは何です。" , "あなたのお気に入りのアニメは何です。");
        List<String> n5 =  Arrays.asList("私もそのアニメが大好きです。" , "私はそのアニメが大好きです。" , "僕もそのアニメが大好きです。" , "僕はそのアニメが大好きです。" , "さくらもそのアニメが大好きです。" );


        Inters_of_dialogues[] dialogs = new Inters_of_dialogues[17];


        /*
        dialogs[0] = new Speech("Left","すみません、あなたは新しい学生ですか。","audios/speechs/sakura_1.mp3");
        dialogs[1] = new Speech("Right","はい、私は新しい学生ですとアンナと言います。","audios/speechs/anna_1.mp3");
        dialogs[2] = new Speech("Left","初めまして、私はさくらです。","audios/speechs/sakura_2.mp3");
        dialogs[3] = new Audio("audios/speechs/anna_2.mp3","Right");
        dialogs[4] = new Question_version_1(n1);
        dialogs[5] = new Speech("Left","アンナはどこ出身ですか。","audios/speechs/sakura_3.mp3");
        dialogs[6] = new Audio("audios/speechs/anna_3.mp3","Right");
        dialogs[7] = new Question_version_2("ブラジル人、日本語を勉強しますのために行きましたへ日本。",n2);
        dialogs[8] = new Speech("Left","すごいです、私はいつもブラジルについてたくさん聞きます、サンバとカーニバルとリオデジャネイロ。なぜはアンナ日本語をべんきょうしますほしいですか。",
                "audios/speechs/sakura_4.mp3");
        dialogs[9] = new Audio("audios/speechs/anna_4.mp3","Right");
        dialogs[10] = new Question_version_1(n3);
        dialogs[11] = new Audio("audios/speechs/sakura_5.mp3","Left");
        dialogs[12] = new Question_version_1(n4);
        dialogs[13] = new Speech("Right","私のお気に入りのアニメは、伸びる力を持つ海賊についてのものです。","audios/speechs/anna_5.mp3");
        dialogs[14] = new Audio("audios/speechs/sakura_6.mp3","Left");
        dialogs[15] = new Question_version_1(n5);
        dialogs[16] = new Speech("Right", "Para béns, você completou o desafio ","");
        */

        try {
            dialogs[0] = lerXML(this);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("XML", "Deu pau");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Boolean) {
                    if (positon < dialogs.length) {
                        list.add(dialogs[positon]);
                        adpter.notifyItemInserted(positon);
                        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1);

                        positon++;
                    }
                }
            }
        });

    }

    @Override
    public void Codition(Boolean aBoolean) {
        Boolean = aBoolean;
    }
    private static Inters_of_dialogues lerXML(Context context) throws Exception{

        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("dialogues/conversation_01/conversation.xml");        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputStream);

        Log.i("XML", "Testando ");
        System.out.println("Root do elemento: " + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("Speeach");
        /*
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            //System.out.println("\nElemento corrente :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

            }
        }
        */
        Node node = nList.item(0);
        Element element = (Element) node;

        Inters_of_dialogues n = new Speech(
                element.getElementsByTagName("direction").item(0).getTextContent(),
                element.getElementsByTagName("text").item(0).getTextContent(),
                element.getElementsByTagName("audio").item(0).getTextContent());



        return n;
    }

}