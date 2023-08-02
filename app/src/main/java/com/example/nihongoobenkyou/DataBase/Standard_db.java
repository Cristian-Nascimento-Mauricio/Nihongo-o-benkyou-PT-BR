package com.example.nihongoobenkyou.DataBase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nihongoobenkyou.classes.Articles_of_Article_Screen;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.classes.Vocabulary_of_Vocabulary_Screen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Standard_db extends SQLiteOpenHelper {

    private static final String DB_NAME = "Standard.db";
    private static final int DB_VERSION = 2;
    private Context context;
    private Cursor cursor;
    private SQLiteDatabase db;

    public Standard_db(@Nullable Context context) {
        super(context, context.getDatabasePath(DB_NAME).getPath() , null, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    @SuppressLint("Range")
    public String get() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM rcydialogue",null);

        cursor.moveToFirst();

        return cursor.getString(cursor.getColumnIndex("titulo"));

    }
    public void copyDatabase() throws IOException {
        String dbPath = this.context.getDatabasePath(DB_NAME).getPath();

        // Verifica se o banco de dados já existe para evitar copiar novamente.
        if (new File(dbPath).exists()) {
            return;
        }

        // Abre o banco de dados da pasta "assets" como um InputStream.
        InputStream inputStream = this.context .getAssets().open(DB_NAME);

        // Cria um OutputStream para o destino do banco de dados no diretório de dados do aplicativo.
        OutputStream outputStream = new FileOutputStream(dbPath);

        // Copia o conteúdo do arquivo da pasta "assets" para o diretório de dados do aplicativo.
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        // Fecha os streams.
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
    @SuppressLint("Range")
    public List<Nivels_of_Screen_Middle> getrcylevel(){
        List<Nivels_of_Screen_Middle> list = new ArrayList<>();

        String SQL = "SELECT * FROM rcylevel ORDER by id";
        Nivels_of_Screen_Middle obj;

        db = getReadableDatabase();
        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()){

            do{
                obj = new Nivels_of_Screen_Middle(
                        cursor.getInt(cursor.getColumnIndex("level")),
                        cursor.getString(cursor.getColumnIndex("text")),
                        cursor.getString(cursor.getColumnIndex("drawableUnblocked")));

                list.add(obj);

            }while (cursor.moveToNext());

        }

        return list;

    }
    @SuppressLint("Range")
    public List<Articles_of_Article_Screen> getrcyarticles(){
        List<Articles_of_Article_Screen> list = new ArrayList<>();

        String SQL = "SELECT * FROM rcyarticles ORDER by id";
        Articles_of_Article_Screen obj;

        db = getReadableDatabase();
        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()){

            do{
                obj = new Articles_of_Article_Screen(
                        cursor.getString(cursor.getColumnIndex("titulo")),
                        cursor.getString(cursor.getColumnIndex("previa")));

                list.add(obj);
            }while (cursor.moveToNext());

        }

        return list;
    }
    @SuppressLint("Range")
    public List<Vocabulary_of_Vocabulary_Screen> getrcydialogue(){
        List<Vocabulary_of_Vocabulary_Screen> list = new ArrayList<>();

        String SQL = "SELECT * FROM rcydialogue ORDER by id";
        Vocabulary_of_Vocabulary_Screen obj;

        db = getReadableDatabase();
        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()){
            do{
                obj = new Vocabulary_of_Vocabulary_Screen(
                        cursor.getString(cursor.getColumnIndex("titulo")),
                        cursor.getString(cursor.getColumnIndex("speech1")),
                        cursor.getString(cursor.getColumnIndex("speech2")),
                        cursor.getString(cursor.getColumnIndex("speech3")));

                list.add(obj);
            }while (cursor.moveToNext());

        }

        return list;
    }

    @SuppressLint("Range")
    public List<String> getcrykanji(){
        List<String> list = new ArrayList<>();

        String SQL = "SELECT * FROM rcykanji ORDER by id";
        Articles_of_Article_Screen obj;

        db = getReadableDatabase();
        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()){

            do{
                list.add(cursor.getString(cursor.getColumnIndex("texto")));
            }while (cursor.moveToNext());

        }

        return list;

    }

    @SuppressLint("Range")
    public List<String> getrcykana(){

        List<String> list = new ArrayList<>();

        String SQL = "SELECT * FROM rcykana ORDER by id";

        db = getReadableDatabase();
        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()){

            do{

                list.add( cursor.getString(cursor.getColumnIndex("hiragana")) + "," + cursor.getString(cursor.getColumnIndex("katakana")));

                Log.i("Valor", ""  + cursor.getString(cursor.getColumnIndex("hiragana")) + "," + cursor.getString(cursor.getColumnIndex("katakana")));
            }while (cursor.moveToNext());

        }

        return list;
    }

}
