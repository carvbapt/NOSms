package com.example.sauca.nosms;


import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.Date;

/**
 * Created by Sauca on 04-02-2018.
 */

public class DadosDB extends SQLiteOpenHelper {

    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dadosDB.db";

    public  static final String TAG="DadosDB";
    public static final String TABLE_DADOS = "Dados";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_GENERO = "Genero";
    public static final String COLUMN_ORDEM = "Ordem";
    public static final String COLUMN_DATA = "Data";
    public static final String COLUMN_ESTADO = "Estado";
    public static final String COLUMN_RESUTADO = "Resultado";
    public static final String COLUMN_RESPONSABILIDADE = "Responsabilidade";
    public static final String COLUMN_DESCRICAO = "Descricao";

    public DatabaseErrorHandler dbh;

    private  static  final  String TABLE_CREATE = "create table " + TABLE_DADOS + " ("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_GENERO + " text not null, "
            + COLUMN_ORDEM+  " text not null, "
            + COLUMN_DATA + " text not null, "
            + COLUMN_ESTADO + " text not null, "
            + COLUMN_RESUTADO + " text not null, "
            + COLUMN_RESPONSABILIDADE + " text not null, "
            + COLUMN_DESCRICAO + " text not null " + ");";

    //initialize the database
    public DadosDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database", "Table Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL ( TABLE_CREATE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(TAG, "Upgrading the database from version " + i + " to " + i1);
        // clear all data
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DADOS);
    }

    // ################################# Metodos ADD,FIND,UPDATE,DELETE ##################################################
    public void addDados(Dados dados) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, dados.getID());
        values.put(COLUMN_ORDEM, dados.getOrdem ());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_DADOS, null, values);
        db.close();
    }

    public Dados findDados(String gen) {
        String query = "Select * FROM " + TABLE_DADOS + "WHERE" + COLUMN_GENERO + " = " + "'" + gen + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Dados dados = new Dados ();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            dados.setID(Integer.parseInt(cursor.getString(0)));
            dados.setGenero (  cursor.getString ( 1 ).charAt ( 0 ) );
            cursor.close();
        } else {
            dados = null;
        }
        db.close();
        return dados;
    }

    public boolean updateDados(int ID, String gen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_GENERO, gen);
        return db.update(TABLE_DADOS, args, COLUMN_ID + "=" + ID, null) > 0;
    }

    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM" + TABLE_DADOS + "WHERE" + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Dados dados = new Dados ();
        if (cursor.moveToFirst()) {
            dados.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_DADOS, COLUMN_ID + "=?",new String[] {String.valueOf(dados.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }



}
