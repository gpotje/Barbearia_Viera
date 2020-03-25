package com.example.barbeariavieira.Marcar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbeariavieira.PerfilAdmin;
import com.example.barbeariavieira.R;
import com.example.barbeariavieira.Usuario.MainActivity;
import com.example.barbeariavieira.Usuario.PerfilUsuario;
import com.example.barbeariavieira.helper.ConfiguracaoFirebase;
import com.example.barbeariavieira.helper.UsuarioFirebase;
import com.example.barbeariavieira.model.UsuarioCliente;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.barbeariavieira.helper.UsuarioFirebase.getIdentificadorUsuario;
import static com.example.barbeariavieira.helper.UsuarioFirebase.getUsuarioAtual;

public class TabelaSeServico extends AppCompatActivity {

    private CheckBox ccortesimple, ccorete, cbarba, cluzes, crelaxamento, cprogressiva, cpenteado, csobrancelha;


    public String pedidoFinal = "";  // pedido final

    public int con = 0;

    FirebaseAuth usuario = ConfiguracaoFirebase.getFirebaseAuthFirebaseAuth();

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    // banco de dados

    //atributo da classe.
    private AlertDialog alerta;




    public   String dia ;
    public   String horacliClass ;
    public   String cliente;
    public   String nomeUsuario ;
    public     String horarioClass;
    public   String horaFormatadaParaMenssagem,dataFomatada;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_se_servico);

        iniciaComponete();




    }


    public void iniciaComponete() {

        Bundle extras = getIntent().getExtras();

          dia = extras.getString("DiaSelecionado");
          horacliClass = extras.getString("horacli");
          cliente = extras.getString("Cliente");
          nomeUsuario = extras.getString("nome");
          horarioClass = extras.getString("horario");
          dataFomatada = extras.getString("dataFormatada");



          horaFormatadaParaMenssagem = extras.getString("horaFormatadaParaMenssagem");



        //check e box
        ccortesimple = findViewById(R.id.cCorteSimples);
        ccorete = findViewById(R.id.cCorte);
        cbarba = findViewById(R.id.cBarba);
        cluzes = findViewById(R.id.cLuzes);
        crelaxamento = findViewById(R.id.cRelax);
        cprogressiva = findViewById(R.id.cProgressiva);
        cpenteado = findViewById(R.id.cPenteado);
        csobrancelha = findViewById(R.id.cSonbra);
        // fim


    }


    public void verificaCheck() {


        if (ccortesimple.isChecked()) {

            pedidoFinal = (pedidoFinal + "Corte Simple ,");
            con++;

        }
        if (ccorete.isChecked()) {

            pedidoFinal = (pedidoFinal + " Corte ,");
            con++;
        }
        if (cbarba.isChecked()) {
            pedidoFinal = (pedidoFinal + " Barba ,");
            con++;
        }
        if (cluzes.isChecked()) {
            pedidoFinal = (pedidoFinal + " Luzes ,");
            con++;
        }
        if (crelaxamento.isChecked()) {
            pedidoFinal = (pedidoFinal + " Relaxamento ,");
            con++;
        }
        if (cprogressiva.isChecked()) {
            pedidoFinal = (pedidoFinal + " Processiva  ,");
            con++;
        }
        if (csobrancelha.isChecked()) {
            pedidoFinal = (pedidoFinal + " Sobrancelha ,");
            con++;
        }
        if (cpenteado.isChecked()) {
            pedidoFinal = (pedidoFinal + " Penteado,");
            con++;
        }

    }


    public void finaliza(View view) {


        verificaCheck();


        if (con > 0) {

            messagemFinaliza();
        }
        else {
            Toast.makeText(TabelaSeServico.this, "Favor selecionar um serviço", Toast.LENGTH_SHORT).show();
        }

    }
    public void cancelar(View view) {

    menssagemCancelar();






    }

    public void gravarDados(){

        DatabaseReference Datedia = reference.child("datames");
        Datedia.child(dia).child(horacliClass).setValue(pedidoFinal);
        Datedia.child(dia).child(cliente).setValue(nomeUsuario);


        Intent i = new Intent(TabelaSeServico.this, PerfilUsuario.class);
        i.putExtra("nome", nomeUsuario);
        startActivity(i);
        Toast.makeText(TabelaSeServico.this, "Marcado", Toast.LENGTH_SHORT).show();

    }





    @Override
    public void onBackPressed() {

    }

    public void messagemFinaliza() {




        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Olá "+ nomeUsuario +" Confirma  os dados abaixo ? \n");
        //define a mensagem
        builder.setMessage("dia: "+ dataFomatada +" ? \n"+"hora: " +horaFormatadaParaMenssagem +" ?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {


                gravarDados();

            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                Toast.makeText(TabelaSeServico.this, "So continuar", Toast.LENGTH_SHORT).show();


            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();

    }

    public void menssagemCancelar(){

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Deseja Cancelar o Agendamento de horario ?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                Toast.makeText(TabelaSeServico.this, "Serviço Cancelado", Toast.LENGTH_SHORT).show();

                DatabaseReference Datedia = reference.child("datames");
                Datedia.child(dia).child(horarioClass).setValue("Vago");

                Intent i = new Intent(TabelaSeServico.this, PerfilUsuario.class);
                i.putExtra("nome", nomeUsuario);
                startActivity(i);





            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {


                Toast.makeText(TabelaSeServico.this, "So continuar", Toast.LENGTH_SHORT).show();



            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();


    }



}




