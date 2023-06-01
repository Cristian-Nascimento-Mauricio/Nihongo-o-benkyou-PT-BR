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
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Speech;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.classes.Vocabulary_of_Vocabulary_Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Controller extends AppDataBase  {
    ContentValues dados;
    List<Nivels_of_Screen_Middle> nivels;

    public Controller(@Nullable Context context) {
        super(context);

    }

    public void Salvar(Nivels_of_Screen_Middle obj) {

        dados = new ContentValues();

        dados.put("text",obj.getTextView());

        insert("rcylevel",dados);

    }

    public void Alterar(Nivels_of_Screen_Middle obj) {

        dados = new ContentValues();

        dados.put("level",obj.getIdOfdb());

        update("rcylevel",dados);

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


            for(byte i = 0, n= 6; i < 7;i++) {
                list.add(new ArrayList<String>());
                for (byte j = (byte) (n-6); j < n-1; j++)
                    list.get(i).add(listinha.get(j));
                n+=5;
            }

            list.add(new ArrayList<String>());
            for(byte i =36; i < 39;i++)
                list.get(7).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(byte i =39; i < 44;i++)
                list.get(8).add(listinha.get(i-1));
            list.add(new ArrayList<String>());
            for(byte i =44; i < 46;i++)
                list.get(9).add(listinha.get(i-1));

            list.add(new ArrayList<>());
            list.get(10).add(listinha.get(45));

            list.add(new ArrayList<>());
            for(byte i =47; i < 52;i++)
                list.get(11).add(listinha.get(i-1));
            list.add(new ArrayList<>());
            for(byte i =52; i < 57;i++)
                list.get(12).add(listinha.get(i-1));
            list.add(new ArrayList<>());
            for(byte i =57; i < 62;i++)
                list.get(13).add(listinha.get(i-1));
            list.add(new ArrayList<>());
            for(byte i =62; i < 67;i++)
                list.get(14).add(listinha.get(i-1));
            list.add(new ArrayList<>());
            for(byte i =67; i < 72;i++)
                list.get(15).add(listinha.get(i-1));

            list.add(0,new ArrayList<>());
            list.add(12,new ArrayList<>());

        } else {
            List<String> listinha = new ArrayList<>();
            listinha.addAll(getkataka());

            for (String count:listinha ) {
                if(count.getBytes()[1] == -125)
                    if (count.getBytes()[2] % 2 != 0)
                        Log.i("numero", "case \"TEXTO\" : return R.raw.".replace("TEXTO",count.substring(0,1))
                                 + count.substring(3,count.length()).toLowerCase() + ";");
            }
            for(byte i = 0, n= 6; i < 7;i++) {
                list.add(new ArrayList<String>());
                for (byte j = (byte) (n-6); j < n-1; j++)
                    list.get(i).add(listinha.get(j));
                n+=5;
            }

            list.add(new ArrayList<String>());
            for(byte i =36; i < 39;i++)
                list.get(7).add(listinha.get(i-1));

            list.add(new ArrayList<String>());
            for(byte i =39; i < 44;i++)
                list.get(8).add(listinha.get(i-1));

            list.add(new ArrayList<String>());
            for(byte i =44; i < 46;i++)
                list.get(9).add(listinha.get(i-1));

            list.add(new ArrayList<>());
            list.get(10).add(listinha.get(45));

            list.add(new ArrayList<>());
            for(byte i =47; i < 52;i++)
                list.get(11).add(listinha.get(i-1));
            list.add(new ArrayList<>());
            for(byte i =52; i < 57;i++)
                list.get(12).add(listinha.get(i-1));
            list.add(new ArrayList<>());
            for(byte i =57; i < 62;i++)
                list.get(13).add(listinha.get(i-1));
            list.add(new ArrayList<>());
            for(byte i =62; i < 67;i++)
                list.get(14).add(listinha.get(i-1));
            list.add(new ArrayList<>());
            for(byte i =67; i < 72;i++)
                list.get(15).add(listinha.get(i-1));


            list.add(0,new ArrayList<>());
            list.add(12,new ArrayList<>());

        }
        return list;
    }

    public List<Speech> SelecionarTextoEAudio(){

        return getTextAndAudio();

    }

    public void Deletar(Nivels_of_Screen_Middle obj) {
        dados = new ContentValues();

        dados.put("level",obj.getIdOfdb());

        delet("rcylevel",dados);

    }

    public void filtar() {

    }

}
