package com.example.barbeariavieira.Marcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.barbeariavieira.R;
import com.example.barbeariavieira.Usuario.PerfilUsuario;

public class Tabela_Servicos extends AppCompatActivity {

    public String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela__servicos);
        iniciaObjetos();


    }

    public void iniciaObjetos(){

        Bundle extras = getIntent().getExtras();
        nome = extras.getString("nome"); // nome do ususario
    }

    @Override
    public void onBackPressed() {

        Intent ias = new Intent(Tabela_Servicos.this, PerfilUsuario.class);
        ias.putExtra("nome",nome);
        startActivity(ias);

    }
}
