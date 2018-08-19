package com.example.viniciusoliveira.appglobocom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.inputmethod.CursorAnchorInfo;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = DBHelper.class.getSimpleName();
    public static final String DB_NAME = "MeuAplicativo.db";
    public static final int DB_VERSION = 1;

    //Constantes para o nome das colunas
    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_NAME = "name";

    // Query para criar a tabela de usuários.
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT,"
            + COLUMN_NAME + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME,null, DB_VERSION);
    }

    //Cria a tabela de usuarios caso ela não exista.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }


    //Deleta e cria de novo caso ja exista
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public void addUser(String email,String password,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PASS,password);

        long id = db.insert(USER_TABLE,null,values);

        Log.d(TAG,"Usuário inserido" + id);
    }

    public boolean getUser(String email,String pass){
        //Query que devolve o Email e a senha para conferir se o Login pode ser aceito(Caso ja tenha sido cadastrado)
        String selectQuery = "select * from "+ USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";


        SQLiteDatabase db = this.getReadableDatabase();
        //Devolve o cursor na posicao que corresponde a Query.
        Cursor cursor = db.rawQuery(selectQuery,null);
        //Move o cursor para a primeira linha da tabela
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            return true;
        }

        cursor.close();
        db.close();

        return false;
    }

}
