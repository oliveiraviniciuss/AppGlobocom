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


/*A ideia inicial era implementar os favoritos com uma tabela de FilmsFavoritos no SQLite.
* Essa tabela guardaria o id do usuário e o id do filme. Dessa forma seria possível pegar os ID's e formar os favoritos para exibir.
* */
public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = DBHelper.class.getSimpleName();
    public static final String DB_NAME = "MeuAplicativo.db";
    public static final int DB_VERSION = 1;

    //Constantes para o nome das colunas do banco de usuarios
    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_NAME = "name";

    //Constantes para o nome das colunas do banco de filmes favoritos
    public static final String FILMS_FAVORITIES = "FilmsFavorities";
    public static final String COLUMN_ID_PESSOA = "id_people";
    public static final String COLUMN_ID_FILM = "id_film";



    // Query para criar a tabela de usuários.
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT,"
            + COLUMN_NAME + " TEXT);";

  /*  //Query para criar tabela de filmes favoritos
    public static final String CREATE_TABLE_FILMS_FAVORITIES = "CREATE TABLE " + FILMS_FAVORITIES + "("
            + COLUMN_ID_PESSOA + " INTEGER PRIMARY KEY,"
            + COLUMN_ID_FILM + " INTEGER);";// continuar aqui
*/
    public DBHelper(Context context) {

        super(context, DB_NAME,null, DB_VERSION);
    }

    //Cria a tabela de usuarios caso ela não exista.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        //db.execSQL(CREATE_TABLE_FILMS_FAVORITIES);

    }


    //Deleta e cria de novo caso ja exista
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FILMS_FAVORITIES);
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


    /*
    public int getIdPessoa(String email){
        //Query que devolve o id correspondente ao email passado

        String selectQuery = "select * from "+ USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        //Devolve o cursor na posicao que corresponde a Query.
        Cursor cursor = db.rawQuery(selectQuery,null);

        //Move o cursor para a primeira linha da tabela
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            int id_pessoa = cursor.getInt(0);

            return id_pessoa;
        }
        cursor.close();
        db.close();

        return;
    }
*/
    /*
    public void addFavorities(int id_fav,int id_film){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_PESSOA,id_fav);
        values.put(COLUMN_ID_FILM,id_film);

        long id = db.insert(USER_TABLE,null,values);
    }
    */
    /*
    public boolean getFavorities(int id_fav){
        String selectQuery = "select * from "+ FILMS_FAVORITIES + " where " +
                COLUMN_ID_PESSOA + " = " + "'"+id_fav+"'";


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
    */

    /*
    public int countFavorities(int id_fav){
        String selectQuery = "select count(*) from "+ FILMS_FAVORITIES + " where " +
                COLUMN_ID_PESSOA + " = " + "'"+id_fav+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        //Devolve o cursor na posicao que corresponde a Query.
        Cursor cursor = db.rawQuery(selectQuery,null);
        int count = cursor.getColumnIndex("count(*)");
        //Move o cursor para a primeira linha da tabela
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            return count;
        }
        cursor.close();
        db.close();

        return count;

    }
*/

}
