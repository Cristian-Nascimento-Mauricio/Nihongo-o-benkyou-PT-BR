package com.example.nihongoobenkyou.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

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

            list.add(new ArrayList<String>());

            for(int i =1; i < 6;i++)
                list.get(0).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =6; i < 11;i++)
                list.get(1).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =11; i < 16;i++)
                list.get(2).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =16; i < 21;i++)
                list.get(3).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =21; i < 26;i++)
                list.get(4).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =26; i < 31;i++)
                list.get(5).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =31; i < 36;i++)
                list.get(6).add(listinha.get(i-1));
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


        } else{
            List<String> listinha = new ArrayList<>();
            listinha.addAll(getkatakaa());

            list.add(new ArrayList<String>());

            for(int i =1; i < 6;i++)
                list.get(0).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =6; i < 11;i++)
                list.get(1).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =11; i < 16;i++)
                list.get(2).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =16; i < 21;i++)
                list.get(3).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =21; i < 26;i++)
                list.get(4).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =26; i < 31;i++)
                list.get(5).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(int i =31; i < 36;i++)
                list.get(6).add(listinha.get(i-1));
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
