package com.example.viniciusoliveira.appglobocom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener
{
    private Button reg;
    private TextView tvLogin;
    private EditText etEmail,etPass;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Instanciando a classe DBHelper
        db = new DBHelper(this);
        reg = (Button)findViewById(R.id.btnReg);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        reg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnReg:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(ActivityRegister.this,LoginActivity.class));
                finish();
                break;
            default:

        }
    }
    private void register(){
        String email = etEmail.getText().toString();
        String pass= etPass.getText().toString();

        if(email.isEmpty() && pass.isEmpty()){
            displayPrint("Email ou Senha em branco.");
        }
        else{
            db.addUser(email,pass);
            displayPrint("Usuario cadastrado");
            finish();
        }
    }
    private void displayPrint(String mensagem){
        Toast.makeText(getApplicationContext(),mensagem,Toast.LENGTH_SHORT).show();
    }
}
