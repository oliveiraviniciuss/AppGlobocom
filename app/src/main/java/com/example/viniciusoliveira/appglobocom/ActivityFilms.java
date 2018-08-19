package com.example.viniciusoliveira.appglobocom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;




public class ActivityFilms extends AppCompatActivity {
    private TextView tvTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        tvTeste = findViewById(R.id.tvTeste);
        tvTeste.setText("Testando app");


    }
}
