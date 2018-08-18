package com.example.viniciusoliveira.appglobocom;

import android.content.Context;
import android.content.SharedPreferences;



public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public Session(Context context){
        this.context = context;
        prefs = context.getSharedPreferences("AppGlobocom",Context.MODE_PRIVATE);
        editor = prefs.edit();

    }
    public void setLoggedin(Boolean loggedin){
        editor.putBoolean("loggedInmode",loggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode",false);
    }



}
