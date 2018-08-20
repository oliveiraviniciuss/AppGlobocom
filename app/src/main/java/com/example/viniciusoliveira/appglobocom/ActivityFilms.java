package com.example.viniciusoliveira.appglobocom;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

    private RequestQueue mQueue;
    private ListView listView;
    private Button btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);
        listView =  (ListView)findViewById(R.id.listview);
        mQueue = Volley.newRequestQueue(this);

        jsonParse();

    }

    private void printJson(String[] onlyNames, final String[] dados){



        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,onlyNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityFilms.this,ActivityFilmsDetails.class);
                //Passo os atributos pra outra classe.
                intent.putExtra("id",position);
                intent.putExtra("vector",dados);
                startActivity(intent);

            }
        });
    }
    private void jsonParse(){

        //Url onde está meu JSON.
        String url = "https://api.myjson.com/bins/14veuw";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Filmes");

                    //Criando String do tamanho do jsonArray para guardar as informacoes do json como String

                    //Criei 2 vetores. Em um eu vou guardar somente os nomes dos filmes e no outro a estrutura completa do json
                    String[] dados = new String[jsonArray.length()];
                    String[] onlyNames = new String[jsonArray.length()];

                    for(int i = 0; i < jsonArray.length();i++){
                        JSONObject Filmes = jsonArray.getJSONObject(i);

                        String title = Filmes.getString("title");
                        String subtitle = Filmes.getString("subtitle");
                        String duracao = Filmes.getString("duracao");
                        String sinopse = Filmes.getString("sinopse");


                        onlyNames[i] = (title + "\n");
                        dados[i] = ("Titulo: " + title+"\n\n" + "Legendas: " + subtitle+"\n\n" + "Duracao: " + duracao+"\n\n" + "Sinopse: " + sinopse+"\n\n");

                    }
                    //Método que printa os dados do Json na tela.
                    printJson(onlyNames,dados);
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
            case R.id.btnVoltar:
                startActivity(new Intent(ActivityFilms.this,MainActivity.class));
        }
    }


}
