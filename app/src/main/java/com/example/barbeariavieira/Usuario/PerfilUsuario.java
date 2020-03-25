package com.example.barbeariavieira.Usuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbeariavieira.Marcar.Admin;
import com.example.barbeariavieira.Marcar.Calendario;
import com.example.barbeariavieira.Marcar.Dia1;
import com.example.barbeariavieira.Marcar.TabelaSeServico;
import com.example.barbeariavieira.Marcar.Tabela_Servicos;
import com.example.barbeariavieira.R;
import com.example.barbeariavieira.helper.ConfiguracaoFirebase;
import com.example.barbeariavieira.helper.UsuarioFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class PerfilUsuario extends AppCompatActivity {

    //Buttons-----------------------
    private ImageView iagenda,isair,iequip,iservicos,ilocaliza,idados ;

    //-----TextView------------------------
    private TextView tnome;

    //--String----------------------------
    public String nome;
    //------------------------------

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        iniciaObjeto();
        autenticacao = ConfiguracaoFirebase.getFirebaseAuthFirebaseAuth();

        Bundle extras = getIntent().getExtras();


        nome = extras.getString("nome");
        tnome.setText(nome);


        iagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilUsuario.this, Calendario.class);
                i.putExtra("nome",nome);
                startActivity(i);
            }
        });

        isair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autenticacao.signOut();//desloga o usuario
                Toast.makeText(getApplicationContext()," Usuario: "+ nome +" Deslogado com sucesso ",Toast.LENGTH_LONG).show();
                Intent ia = new Intent(PerfilUsuario.this,MainActivity.class);
                startActivity(ia);
            }
        });
        iequip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this, "Desculpe Função não implementada", Toast.LENGTH_SHORT).show();
            }
        });
        iservicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ias = new Intent(PerfilUsuario.this, Tabela_Servicos.class);
                ias.putExtra("nome",nome);
                startActivity(ias);
            }
        });
        ilocaliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this, "Desculpe Função não implementada", Toast.LENGTH_SHORT).show();
            }
        });
        idados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this, "Desculpe Função não implementada", Toast.LENGTH_SHORT).show();
            }
        });









    }
    public void iniciaObjeto(){

        //Imagens------------------------------
       iagenda =findViewById(R.id.IAgenda);
       isair =findViewById(R.id.Isair);

       iequip = findViewById(R.id.IEquip);
       iservicos = findViewById(R.id.IServicos);

       ilocaliza = findViewById(R.id.ILocalizacao);
       idados = findViewById(R.id.IDadosUsuarios);

        //-------------------------------------------

        //TextView--------------------------------
        tnome = findViewById(R.id.tNome);
      //  bsair = findViewById(R.id.bSair);
    }

    @Override
    public void onBackPressed() {

    }



}
