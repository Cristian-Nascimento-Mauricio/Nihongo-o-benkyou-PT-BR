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

import androidx.annotation.Nullable;

import com.example.nihongoobenkyou.classes.Articles_of_Article_Screen;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;
import com.example.nihongoobenkyou.classes.Vocabulary_of_Vocabulary_Screen;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AppDataBase extends SQLiteOpenHelper {

    private Context mContext;
    public static  String db_NAME = "tabela.db";
    private static final int db_version = 1;
    private static final String Createtable = "CREATE TABLE \"tabelaTeste\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"level\"\tINTEGER DEFAULT -1,\n" +
            "\t\"text\"\tTEXT,\n" +
            "\t\"drawableBlocked\"\tBLOB,\n" +
            "\t\"drawableUnblocked\"\tBLOB,\n" +
            "\t\"drawableFinished\"\tBLOB,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")";

    private String SQL;

    Cursor cursor;

    SQLiteDatabase db;


    public AppDataBase(@Nullable Context context) {
        super(context, db_NAME, null, db_version);
        this.mContext = context;
        String dbPath = context.getDatabasePath(db_NAME).getPath();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

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
        String SQL = "SELECT * FROM tabelaTeste ORDER by id";
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
        String SQL = "SELECT * FROM dialogo ORDER by id";
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
        String SQL = "SELECT * FROM kanjis ORDER by id";
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
        String SQL = "SELECT * FROM artigos ORDER by id";
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


    @SuppressLint("Range")
    public List<String> getHiraganar(){

        openDataBase();
        String SQL = "SELECT * FROM hirakana ORDER by id";
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
        String SQL = "SELECT * FROM hirakana ORDER by id";
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

    private void openDataBase() {
        String dbPath = mContext.getDatabasePath(db_NAME).getPath();
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDataBase(){
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

