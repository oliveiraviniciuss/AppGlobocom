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
    private EditText etEmail,etPass,etName;
    private DBHelper db;
    TextView teste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Instancia a classe que utiliza o SQLite e inicializa as variáveis
        db = new DBHelper(this);
        reg = (Button)findViewById(R.id.btnReg);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        etName = (EditText)findViewById(R.id.etName);
        reg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);



    }

    //Método que toma uma determinada ação caso um dos botões seja acionado.
    //Intent é usado pra solicitar essa ação
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnReg: //Exemplo: caso seja acionado o botão de Registrar(na tela de login),o metodo register é chamada.
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(ActivityRegister.this,LoginActivity.class));
                finish();
                break;
            default:

        }
    }

    // O Método pega o que está escrito na caixa de texto de email, password e nome. Se nenhum dos campos for nulo, ele cadastra no banco.
    //Falta implementar com que o usuário não consiga cadastrar o mesmo e-mail duas vezes.
    private void register(){
        String email = etEmail.getText().toString();
        String pass= etPass.getText().toString();
        String name = etName.getText().toString();

        if(email.isEmpty() || pass.isEmpty() || name.isEmpty() ){
            displayPrint("Favor preencher todos os campos.");
        }
        else{
            db.addUser(email,pass,name);
            displayPrint("Usuario cadastrado");



            finish();
        }
    }
    //Mostra na tela em um popup a mensagem digitada.
    private void displayPrint(String mensagem){
        Toast.makeText(getApplicationContext(),mensagem,Toast.LENGTH_SHORT).show();
    }
}
