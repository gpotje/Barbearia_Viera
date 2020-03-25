package com.example.barbeariavieira.Usuario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barbeariavieira.Marcar.TabelaSeServico;
import com.example.barbeariavieira.R;
import com.example.barbeariavieira.helper.ConfiguracaoFirebase;
import com.example.barbeariavieira.helper.UsuarioFirebase;
import com.example.barbeariavieira.model.UsuarioCliente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroUsuario extends AppCompatActivity {

        private EditText enome,elogin,esenha;
        private Button bcadastro;

        public String snome,slogin,ssenha;


        private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);


        iniciObjeto();

        bcadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                snome = enome.getText().toString();
                slogin = elogin.getText().toString();
                ssenha = esenha.getText().toString();

            // verificação de dados

            if(!snome.isEmpty()){ //verificar se o campo não esta vazio

                if(!slogin.isEmpty()){ //verificar se o campo não esta Email

                    if(!ssenha.isEmpty()){ //verificar se o campo não esta Email

                        //estanciando usuario

                        UsuarioCliente usuarioCliente = new UsuarioCliente();
                        usuarioCliente.setNome(snome);
                        usuarioCliente.setEmail(slogin);
                        usuarioCliente.setSenha(ssenha);
                        usuarioCliente.setTipo("U");

                        // salva e cria o novo usuario
                        cadastrarUsuario(usuarioCliente);


                    }else{



                        Toast.makeText(CadastroUsuario.this,"Preencha o Senha"
                                ,Toast.LENGTH_LONG).show();

                    }

                }else{

                    Toast.makeText(CadastroUsuario.this,"Preencha o Email"
                            ,Toast.LENGTH_LONG).show();

                }


            }else{

                Toast.makeText(CadastroUsuario.this,"Preencha o nome"
                        ,Toast.LENGTH_LONG).show();

            }



            //



            }
        });
    }




    public void iniciObjeto(){
        enome = findViewById(R.id.eNome);
        elogin = findViewById(R.id.eEmail);
        esenha = findViewById(R.id.eSenha);

        bcadastro= findViewById(R.id.bCadastro);




    }




    public void cadastrarUsuario(final UsuarioCliente usuarioCliente){

        autenticacao = ConfiguracaoFirebase.getFirebaseAuthFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(
                usuarioCliente.getEmail(),
                usuarioCliente.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                  try {

                      // pega o id do usuario
                      String idUsuario = task.getResult().getUser().getUid();
                      usuarioCliente.setId(idUsuario);
                      usuarioCliente.salvar();


                      //Atualizar o nome no UserProfile
                      UsuarioFirebase.atualizarNOmeUsuatio(usuarioCliente.getNome());


                      startActivity(new Intent(CadastroUsuario.this,MainActivity.class));
                      finish();
                      Toast.makeText(CadastroUsuario.this,"Sucesso a cadastrar o usuario"
                              ,Toast.LENGTH_LONG).show();

                  }catch (Exception e ){
                    e.printStackTrace();
                  }


                }else{
                    String exececao = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e ){
                        exececao = "Digite uma senha mais forte" ;
                    }catch (FirebaseAuthInvalidCredentialsException e ){
                        exececao = "Por favor digite um e-mail valido" ;

                    }catch (FirebaseAuthUserCollisionException e){
                        exececao = "Esta conta ja foi cadastrada" ;
                    } catch (Exception e) {
                        exececao = "Erro ao cadastrar usuario" + e.getMessage();
                        e.printStackTrace();
                    }


                }

            }
        });

    }
    @Override
    public void onBackPressed() {

    }


}
