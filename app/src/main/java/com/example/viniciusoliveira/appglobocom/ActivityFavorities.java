package com.example.viniciusoliveira.appglobocom;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


//Implementei o favorito como uma String[].
//O ideal era implementar no banco, conforme o comentário que deixei na Activity DBHelper.


public class ActivityFavorities extends AppCompatActivity implements View.OnClickListener {
    private Button btnVoltar;
    private Button btnAdicionar;
    private ListView listViewFavorities;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorities);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);
        btnAdicionar = (Button)findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(this);
        listViewFavorities =  (ListView)findViewById(R.id.listViewFavorities);
        String[] arrayNames;

        //Objeto bundle recebendo atributos de outra clase.
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            arrayNames = bundle.getStringArray("arrayFilms");
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayNames);
            listViewFavorities.setAdapter(adapter);



        }
    }













    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnVoltar:
                startActivity(new Intent(ActivityFavorities.this,MainActivity.class));
                break;
            case R.id.btnAdicionar:
                Intent intent = new Intent(ActivityFavorities.this,ActivityFilms.class);
                startActivity(intent);
                break;
        }

    }
}
