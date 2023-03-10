package com.example.nihongoobenkyou.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import com.example.nihongoobenkyou.DataBase.AppDataBase;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;

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

    public void Deletar(Nivels_of_Screen_Middle obj) {
        dados = new ContentValues();

        dados.put("level",obj.getIdOfdb());

        delet("tabelaTeste",dados);


    }

    public void filtar() {

    }
}
