package com.example.viniciusoliveira.appglobocom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login,register;
    private EditText etEmail,etPass;
    private DBHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        db = new DBHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnReg);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

    }

    //Método que toma uma determinada ação caso um dos botões seja acionado.
    //Intent é usado pra solicitar essa ação
    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(LoginActivity.this,ActivityRegister.class));
                break;

            default:

        }
    }
    //Pega as informações digitas nos campos de Email e Senha e confere eles existem no banco. Caso exista, é autorizado a entrada no sistema.
    private void login(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if(db.getUser(email,pass)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("email",email);
            session.setLoggedin(true);
            startActivity(intent);
            finish();
        }
        else{ //Caso não exista, um popup aparece para o usuário.
            Toast.makeText(getApplicationContext(),"Email ou Senha incorretos",Toast.LENGTH_SHORT).show();
        }
    }
}
