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


public class ActivityFilms extends AppCompatActivity implements View.OnClickListener{
    private TextView mTextResult;
    private RequestQueue mQueue;

    private Button btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        mTextResult = findViewById(R.id.text_view_result);
        mTextResult.setOnClickListener(this);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

        mQueue = Volley.newRequestQueue(this);
        jsonParse();




    }
    private void jsonParse(){
        String url = "https://api.myjson.com/bins/btv4c";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Filmes");

                    for(int i = 0; i < jsonArray.length();i++){
                        JSONObject Filmes = jsonArray.getJSONObject(i);

                        String tittle = Filmes.getString("tittle");
                        String subtittle = Filmes.getString("subtittle");
                        String duracao = Filmes.getString("duracao");
                        String sinopse = Filmes.getString("sinopse");
                        String image = Filmes.getString("image");

                        mTextResult.append(tittle + ", " + subtittle +", " + duracao + ", " + sinopse + ", " + image +"\n\n");





                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.text_view_result:
                Toast.makeText(getApplicationContext(),"Teste",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnVoltar:
                startActivity(new Intent(ActivityFilms.this,MainActivity.class));
        }
    }
}
