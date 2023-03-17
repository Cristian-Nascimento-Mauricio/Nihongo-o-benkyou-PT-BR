package com.example.nihongoobenkyou.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nihongoobenkyou.DataBase.AppDataBase;
import com.example.nihongoobenkyou.classes.Articles_of_Article_Screen;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.classes.Vocabulary_of_Vocabulary_Screen;

import java.util.ArrayList;
import java.util.List;

public class Controller extends AppDataBase  {
    ContentValues dados;
    List<Nivels_of_Screen_Middle> nivels;


    public Controller(@Nullable Context context) {
        super(context);

    }

    public void Salvar(Nivels_of_Screen_Middle obj) {

        dados = new ContentValues();

        dados.put("text",obj.getTextView());

        insert("tabelaTeste",dados);

    }

    public void Alterar(Nivels_of_Screen_Middle obj) {

        dados = new ContentValues();

        dados.put("level",obj.getIdOfdb());

        update("tabelaTeste",dados);

    }

    public List<Nivels_of_Screen_Middle> Selecionar( ) {

        nivels = getAll();

        return nivels;
    }
    public List<Vocabulary_of_Vocabulary_Screen> SelecionarVocabulario(){

        return getAllVocabulary();
    }
    public List<String> SelecionarKanjis(){

        return getAllKanjis();
    }
    public List<Articles_of_Article_Screen> SelecionarArtigo(){

        return getAllArtigos();
    }
    public List<List<String>> SelecionarHiragana(String text){
        List<List<String>> list = new ArrayList<>();

        if(text.equals("hiragana")){
            List<String> listinha = new ArrayList<>();
            listinha.addAll(getHiraganar());


            for (String count: listinha) {

                byte[] bytes = count.getBytes();

            }


            for(byte i = 0, n= 6; i < 7;i++) {
                list.add(new ArrayList<String>());
                for (byte j = (byte) (n-6); j < n-1; j++)
                    list.get(i).add(listinha.get(j));

                n+=5;
            }

            list.add(new ArrayList<String>());
            for(int i =36; i < 39;i++)
                list.get(7).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =39; i < 44;i++)
                list.get(8).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =44; i < 46;i++)
                list.get(9).add(listinha.get(i-1));

            list.add(new ArrayList<>());
            list.get(10).add(listinha.get(45));


        } else {
            List<String> listinha = new ArrayList<>();
            listinha.addAll(getkataka());


            for (String count: listinha) {

                byte[] bytes = count.getBytes();



            }


            for(byte i = 0, n= 6; i < 7;i++) {
                list.add(new ArrayList<String>());
                for (byte j = (byte) (n-6); j < n-1; j++)
                    list.get(i).add(listinha.get(j));

                n+=5;
            }

            list.add(new ArrayList<String>());
            for(int i =36; i < 39;i++)
                list.get(7).add(listinha.get(i-1));


            list.add(new ArrayList<String>());
            for(int i =39; i < 44;i++)
                list.get(8).add(listinha.get(i-1));

            list.add(new ArrayList<String>());
            for(int i =44; i < 46;i++)
                list.get(9).add(listinha.get(i-1));


            list.add(new ArrayList<>());
            list.get(10).add(listinha.get(45));


        }
        return list;
    }

    public void Deletar(Nivels_of_Screen_Middle obj) {
        dados = new ContentValues();

        dados.put("level",obj.getIdOfdb());

        delet("tabelaTeste",dados);


    }


    public void filtar() {

    }

}
