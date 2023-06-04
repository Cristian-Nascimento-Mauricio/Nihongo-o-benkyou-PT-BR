package com.example.nihongoobenkyou.DataBase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Standard_db extends SQLiteOpenHelper {

    private static final String DB_NAME = "Standard.db";
    private static final int DB_VERSION = 2;
    private  Context context;

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

}
