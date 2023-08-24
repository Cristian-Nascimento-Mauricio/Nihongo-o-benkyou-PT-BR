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
    List<Inters_of_dialogues> dialogs = null;

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

        try {
            dialogs = lerXML(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Boolean) {
                    if (positon < dialogs.size()) {
                        list.add(dialogs.get(positon));
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

    private static List<Inters_of_dialogues> lerXML(Context context) throws Exception{

        List<Inters_of_dialogues> list = new ArrayList<>();


        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("dialogues/conversation_01/conversation.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputStream);

        doc.getDocumentElement().normalize();

        // Obter a raiz do documento
        Element root = doc.getDocumentElement();

        // Percorrer os elementos filhos da raiz
        NodeList nodeList = root.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Log.i("XML", "" + element.getNodeName() + " position: " + i);
                if(element.getNodeName().equals("Speech")){
                    list.add(new Speech(
                            element.getElementsByTagName("direction").item(0).getTextContent(),
                            element.getElementsByTagName("text").item(0).getTextContent(),
                            element.getElementsByTagName("audio").item(0).getTextContent()));
                } else if(element.getNodeName().equals("Audio")){
                    list.add(new Audio(
                            element.getElementsByTagName("path").item(0).getTextContent(),
                            element.getElementsByTagName("direction").item(0).getTextContent())
                    );
                } else if(element.getNodeName().equals("Question_version_1")){
                    String[] Arraywords = element.getElementsByTagName("words").item(0).getTextContent().split(",");
                    List<String> words = new ArrayList<>();
                    for (String word : Arraywords) {
                        words.add(word.trim());
                    }
                    list.add(new Question_version_1(words));
                } else if(element.getNodeName().equals("Question_version_2")){
                    String[] Arraywords = element.getElementsByTagName("words").item(0).getTextContent().split(",");
                    List<String> words = new ArrayList<>();
                    for (String word : Arraywords) {
                        words.add(word.trim());
                    }
                    list.add(new Question_version_2( element.getElementsByTagName("text").item(0).getTextContent(),words ));
                }
            }
        }

        return list;
    }

}