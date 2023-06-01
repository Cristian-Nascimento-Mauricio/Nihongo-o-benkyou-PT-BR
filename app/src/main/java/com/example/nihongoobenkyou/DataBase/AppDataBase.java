package com.example.nihongoobenkyou.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.nihongoobenkyou.classes.Articles_of_Article_Screen;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Speech;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.classes.Vocabulary_of_Vocabulary_Screen;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppDataBase extends SQLiteOpenHelper {

    private Context mContext;
    public static  String db_NAME = "tabela.db";
    private static final int db_version = 1;

    Cursor cursor;

    SQLiteDatabase db;

    public AppDataBase(@Nullable Context context) {
        super(context, null, null,db_version);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        mContext.deleteDatabase(db_NAME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String table, ContentValues dados){

        db.insert(table,null,dados);

    }
    public void update(String table, ContentValues dados){


        db.update(table,dados,"id=?", new String[]{Integer.toString(dados.getAsInteger("level"))});
    }

    public void delet(String table, ContentValues dados){

        db.delete(table,"id=?", new String[]{Integer.toString(dados.getAsInteger("level"))});

    }

    @SuppressLint("Range")
    public List<Nivels_of_Screen_Middle> getAll(){
        openDataBase();

        List<Nivels_of_Screen_Middle> list = new ArrayList<>();
        String SQL = "SELECT * FROM rcylevel ORDER by id";
        Nivels_of_Screen_Middle obj;

        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()){

            do{
                obj = new Nivels_of_Screen_Middle(
                        cursor.getInt(cursor.getColumnIndex("level")),
                        cursor.getString(cursor.getColumnIndex("text")),
                        BitmapFactory.decodeByteArray( cursor.
                                        getBlob(cursor.getColumnIndex("drawableUnblocked")),
                                0,
                                cursor.getBlob(cursor.getColumnIndex("drawableUnblocked")).length));
                obj.setIdOfdb(cursor.getInt(cursor.getColumnIndex("id")));
                list.add(obj);

            }while (cursor.moveToNext());

        }
        closeDataBase();

        return list;
    }

    @SuppressLint("Range")
    public List<Vocabulary_of_Vocabulary_Screen> getAllVocabulary(){

        openDataBase();

        List<Vocabulary_of_Vocabulary_Screen> list = new ArrayList<>();
        String SQL = "SELECT * FROM rcydialogue ORDER by id";
        Vocabulary_of_Vocabulary_Screen obj;

        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()) {
            do {
                obj = new Vocabulary_of_Vocabulary_Screen(
                        cursor.getString(cursor.getColumnIndex("titulo")),
                        cursor.getString(cursor.getColumnIndex("speech1")),
                        cursor.getString(cursor.getColumnIndex("speech2")),
                        cursor.getString(cursor.getColumnIndex("speech3")));

                    list.add(obj);
            } while (cursor.moveToNext());
        }

        closeDataBase();

        return list;

    }

    @SuppressLint("Range")
    public List<String> getAllKanjis(){

        openDataBase();
        String SQL = "SELECT * FROM rcykanji ORDER by id";
        List<String> list = new ArrayList<>();

        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()){

            do{
                list.add(cursor.getString(cursor.getColumnIndex("texto")));

            }while (cursor.moveToNext());

        }

        closeDataBase();

        return list;
    }
    @SuppressLint("Range")
    public List<Articles_of_Article_Screen> getAllArtigos(){

        openDataBase();

        String SQL = "SELECT * FROM rcyarticles ORDER by id";
        List<Articles_of_Article_Screen> list = new ArrayList<>();

        cursor = db.rawQuery(SQL,null);

        Articles_of_Article_Screen obj;

        if(cursor.moveToFirst()){

            do{
                obj = new Articles_of_Article_Screen(
                        cursor.getString(cursor.getColumnIndex("titulo")),
                        cursor.getString(cursor.getColumnIndex("previa")));

                list.add(obj);
            }while (cursor.moveToNext());

        }

        closeDataBase();

        return list;

    }
    public int getSize(){

        openDataBase();

        Cursor cursor = db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table'", null);
        int numTables = 0;

        if (cursor != null && cursor.moveToFirst()) {
            // Obter o número de tabelas do primeiro (e único) registro
            numTables = cursor.getInt(0);
        }


        cursor.close();

        db.close();

        return numTables;

    }


    @SuppressLint("Range")
    public List<String> getHiraganar(){

        openDataBase();
        String SQL = "SELECT * FROM rcykana ORDER by id";
        List<String> list = new ArrayList<>();

        cursor = db.rawQuery(SQL,null);


        if(cursor.moveToFirst()){

            do{
                list.add(cursor.getString(cursor.getColumnIndex("hiragana")));
            }while (cursor.moveToNext());

        }

        closeDataBase();

        return list;


    }    @SuppressLint("Range")
    public List<String> getkataka(){

        openDataBase();
        String SQL = "SELECT * FROM rcykana ORDER by id";
        List<String> list = new ArrayList<>();


        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()){

            do{
                list.add(cursor.getString(cursor.getColumnIndex("katakana")));
            }while (cursor.moveToNext());

        }

        closeDataBase();

        return list;

    }
    @SuppressLint("Range")
    public List<Speech> getTextAndAudio(){

        openDataBase();

        List<Speech> list = new ArrayList<>();
        String SQL = "SELECT * FROM texts_and_audios ORDER by id";
        Speech obj;

        cursor = db.rawQuery(SQL,null);
        Random random = new Random();

        if(cursor.moveToFirst()){

            do{
                obj = new Speech(random.nextBoolean() ? "Right" : "Left" , cursor.getString(cursor.getColumnIndex("text")) , cursor.getString(cursor.getColumnIndex("audio")));

                list.add(obj);

            }while (cursor.moveToNext());

        }

        closeDataBase();

        return list;
    }


    public void upVersionTable(){


    }

    private void openDataBase() {
        String dbPath = mContext.getDatabasePath(db_NAME).getPath();
        if(db != null && db.isOpen()){
            return;
        }
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    private void closeDataBase(){
        if (db != null) {
            db.close();
            db = null;
        }
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }



}

