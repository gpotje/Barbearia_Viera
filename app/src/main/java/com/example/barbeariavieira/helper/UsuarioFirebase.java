package com.example.barbeariavieira.helper;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.barbeariavieira.Marcar.Calendario;
import com.example.barbeariavieira.Marcar.Dia1;
import com.example.barbeariavieira.PerfilAdmin;
import com.example.barbeariavieira.Usuario.PerfilUsuario;
import com.example.barbeariavieira.model.UsuarioCliente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UsuarioFirebase {



    public static FirebaseUser getUsuarioAtual(){
        //retorna o usuario logado
        FirebaseAuth usuario =ConfiguracaoFirebase.getFirebaseAuthFirebaseAuth();
        return usuario.getCurrentUser();



    }

    public static boolean atualizarNOmeUsuatio(String nome) {

        try {
            FirebaseUser user = getUsuarioAtual();
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName( nome )
                    .build();
            user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                   if(!task.isSuccessful()) {
                       Log.d("perfil","Erro ao atualizar nome de perfil");
                   }
                }
            });

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void redirecionaUsuarioLogado(final Activity activity){
        // veriofica se o usuario esta logado

        FirebaseUser user = getUsuarioAtual();


        if(user != null) {
            // pesquisa o tipo de usuario
            DatabaseReference usuariosRef = ConfiguracaoFirebase.getFirebaseDatabase().
                    child("usuarios")
                    .child(getIdentificadorUsuario());//recupera o indenticador do usuario
            usuariosRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UsuarioCliente usuarioCliente = dataSnapshot.getValue(UsuarioCliente.class);

                    String tipo = usuarioCliente.getTipo();
                    String nome = usuarioCliente.getNome();


                    //if que compara String
                    if (tipo.equals("A")) {


                        activity.startActivity(new Intent(activity, PerfilAdmin.class));





                    } else {

                        Intent i = new Intent(activity, PerfilUsuario.class);
                        i.putExtra("nome", nome);
                        activity.startActivity(i);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

    }



    public static String getIdentificadorUsuario(){
       //rescupera id do usuario
        return getUsuarioAtual().getUid();


    }
}
