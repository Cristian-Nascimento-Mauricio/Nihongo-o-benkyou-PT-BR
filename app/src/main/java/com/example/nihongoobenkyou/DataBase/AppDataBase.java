package com.example.nihongoobenkyou.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;

import java.util.ArrayList;
import java.util.List;

public class AppDataBase extends SQLiteOpenHelper {

    private static final String db_NAME = "db_infor_recycler.sqlite";
    private static final int db_version = 1;
    private static final String Createtable = "CREATE TABLE \"tabelaTeste\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"level\"\tINTEGER DEFAULT -1,\n" +
            "\t\"text\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")";

    private String SQL;

    Cursor cursor;

    SQLiteDatabase db;
    Drawable drawable;


    public AppDataBase(@Nullable Context context) {
        super(context, db_NAME, null, db_version);

        db = getWritableDatabase();
        drawable = context.getDrawable(R.drawable.fire);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Createtable);
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

        List<Nivels_of_Screen_Middle> list = new ArrayList<>();
        String SQL = "SELECT * FROM tabelaTeste ORDER by id";
        Nivels_of_Screen_Middle obj;


        cursor = db.rawQuery(SQL,null);

        if(cursor.moveToFirst()){

            do{
                obj = new Nivels_of_Screen_Middle(
                        cursor.getInt(cursor.getColumnIndex("level")),
                        cursor.getString(cursor.getColumnIndex("text")),
                        this.drawable);

                list.add(obj);

            }while (cursor.moveToNext());

        }

        return list;
    }


}

