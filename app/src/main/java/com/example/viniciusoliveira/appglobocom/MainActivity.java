package com.example.viniciusoliveira.appglobocom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Session session;
    private Button btnLogout;
    private Button btnCatalogo;
    private TextView tvMensagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(this);

        if(!session.loggedin()){
            logout();
        }

        //Forma de receber o atributo de outra classe.
        //Mudar para o nome cadastrado.
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){


            String email = bundle.getString("email");
            tvMensagem = (TextView)findViewById(R.id.tvMensagem);
            tvMensagem.setText("    Olá "+ email + " seja bem vindo(a) ao sistema de catalogo de filmes online.\n    Escolha" +
                    " uma das opções para navegar:");
        }

        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnCatalogo = (Button)findViewById(R.id.btnCatalogo);
        btnCatalogo.setOnClickListener(this);
        btnLogout.setOnClickListener(this);



    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnLogout:
                logout();
                break;
            case R.id.btnCatalogo:
                startActivity(new Intent(MainActivity.this,ActivityFilms.class));
                break;
            default:
        }


    }
    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

}
