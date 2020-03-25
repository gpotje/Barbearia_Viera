package com.example.barbeariavieira.Usuario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.barbeariavieira.R;

import com.example.barbeariavieira.helper.ConfiguracaoFirebase;
import com.example.barbeariavieira.helper.UsuarioFirebase;
import com.example.barbeariavieira.model.UsuarioCliente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button bacessar;
    private EditText eemail, esenha;
    private Switch sacesso;

    private FirebaseAuth auth;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaComponetes();


        bacessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sacesso.isChecked()) {


                    Intent i = new Intent(MainActivity.this, CadastroUsuario.class);
                    startActivity(i);

                } else {

                    String textoEmail = eemail.getText().toString();
                    String textoSenha = esenha.getText().toString();

                    if (!textoEmail.isEmpty()) {//verifica e-mail

                        if (!textoSenha.isEmpty()) {//verifica senha

                            UsuarioCliente usuarioCliente = new UsuarioCliente();
                            usuarioCliente.setEmail(textoEmail);
                            usuarioCliente.setSenha(textoSenha);

                            logarUsuario(usuarioCliente);


                        } else {

                            Toast.makeText(MainActivity.this, "Preencha a Senha"
                                    , Toast.LENGTH_LONG).show();

                        }

                    } else {

                        Toast.makeText(MainActivity.this, "Preencha o email"
                                , Toast.LENGTH_LONG).show();

                    }


                }

            }


        });


    }

    public void logarUsuario(UsuarioCliente usuarioCliente) {

        auth = ConfiguracaoFirebase.getFirebaseAuthFirebaseAuth();
        auth.signInWithEmailAndPassword(
                usuarioCliente.getEmail(),
                usuarioCliente.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    //verifica o tipo de usuario
                    // Adim ou usuario

                    UsuarioFirebase.redirecionaUsuarioLogado(MainActivity.this);


                } else {
                    String exececao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        exececao = "Usuario não esta cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exececao = "Email e senha não corresponde";

                    } catch (Exception e) {
                        exececao = "Erro ao cadastrar usuario" + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, exececao
                            , Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    // inicializa Componetes
    private void inicializaComponetes() {

        bacessar = findViewById(R.id.bLogar);
        eemail = findViewById(R.id.eEimail);
        esenha = findViewById(R.id.eSenha);

        sacesso = findViewById(R.id.switchAcesso);


    }

    @Override
    public void onBackPressed() {

    }
/*
    public void g(View view) {
        //Criançao do banco de dados

        DatabaseReference Datedia = reference.child("datames");


        for (int i = 1; i < 32; i++) {
            String dia = ("00" + i);
            for (int j = 10; j < 20; j++) {
                String hora = "hora" + j;
                String cliente = "clihora" + j;
                String pedido = "pedhora" + j;

                Datedia.child(dia).child(hora).setValue("Vago");
                Datedia.child(dia).child(cliente).setValue("Cliente Vago");
                Datedia.child(dia).child(pedido).setValue("Pedido Vago");
            }
        }


        // Intent i = new Intent(MainActivity.this,PerfilUsuario.class);
        //    startActivity(i);

        //   }

        // fim


    }*/
}