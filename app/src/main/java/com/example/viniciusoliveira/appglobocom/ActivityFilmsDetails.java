package com.example.viniciusoliveira.appglobocom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityFilmsDetails extends AppCompatActivity implements View.OnClickListener {

    private  ArrayList<String> arrayFilms;
    private Button btnVoltar;
    private TextView textDetails;
    private String[] vectorNames;
    private String[] arr;
    private String[] vector;
    private Button addFav;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films_details);

        btnVoltar = (Button)findViewById(R.id.button);
        btnVoltar.setOnClickListener(this);
        addFav = (Button)findViewById(R.id.addFav);
        addFav.setOnClickListener(this);
        arrayFilms = new ArrayList<>();

        //Objeto Bundle para receber os atributos da outra classe.
        Bundle bundle = getIntent().getExtras();
        position  = bundle.getInt("id");

        vector = bundle.getStringArray("vector");
        vectorNames = bundle.getStringArray("names");

        // Imprimo na tela as informações detalhadas do filme que foi clicado na tela anterior
        textDetails = (TextView)findViewById(R.id.textDetails);
        textDetails.setText(vector[position]);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.button:
                startActivity(new Intent(ActivityFilmsDetails.this,ActivityFilms.class));
                break;
            case R.id.addFav:
                //Não adiciona o filme caso ele ja esteja adicionado como favorito.
                if(arrayFilms.contains(this.vectorNames[this.position])) {
                    Toast.makeText(getApplicationContext(),"Esse filme ja é seu favorito",Toast.LENGTH_SHORT).show();
                }
                else {
                    arrayFilms.add(this.vectorNames[this.position]);
                    arr = arrayFilms.toArray(new String[arrayFilms.size()]);
                    Intent intent = new Intent(ActivityFilmsDetails.this, ActivityFavorities.class);
                    intent.putExtra("arrayFilms", arr);
                    startActivity(intent);
                }
                break;


        }
    }


}
