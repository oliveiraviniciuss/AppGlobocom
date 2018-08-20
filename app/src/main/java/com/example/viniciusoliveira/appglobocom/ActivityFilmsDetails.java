package com.example.viniciusoliveira.appglobocom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityFilmsDetails extends AppCompatActivity implements View.OnClickListener {
    private Button btnVoltar;
    private TextView textDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films_details);

        btnVoltar = (Button)findViewById(R.id.button);
        btnVoltar.setOnClickListener(this);

        //Objeto Bundle para receber os atributos da outra classe.
        Bundle bundle = getIntent().getExtras();
        int position  = bundle.getInt("id");
        String[] vector = bundle.getStringArray("vector");

        // Imprimo na tela as informações detalhadas do filme que foi clicado na tela anterior
        textDetails = (TextView)findViewById(R.id.textDetails);
        textDetails.setText(vector[position]);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button:
                startActivity(new Intent(ActivityFilmsDetails.this,ActivityFilms.class));

        }
    }
}
