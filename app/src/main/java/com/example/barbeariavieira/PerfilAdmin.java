package com.example.barbeariavieira;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbeariavieira.Marcar.Admin;
import com.example.barbeariavieira.Marcar.Calendario;
import com.example.barbeariavieira.Marcar.Dia1;
import com.example.barbeariavieira.Marcar.RetornaDia;
import com.example.barbeariavieira.Marcar.TabelaSeServico;
import com.example.barbeariavieira.Usuario.MainActivity;
import com.example.barbeariavieira.Usuario.PerfilUsuario;
import com.example.barbeariavieira.helper.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilAdmin extends AppCompatActivity {


    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    // Button--------------------------
    private Button bvisualiza,bapaga,bsair,bapagaHora;

    //calendarioView
    private CalendarView ccalendarView;

    private TextView tdiaselecionado;

    private FirebaseAuth autenticacao;

    //int---e string----------------------------
    public int ActiviDia;
    public String textocelendario , diaCompleto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_admin);

        iniciaObjeto();
        autenticacao = ConfiguracaoFirebase.getFirebaseAuthFirebaseAuth();


        //Button CalendarioView
        ccalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                ActiviDia = (int) Integer.parseInt(String.valueOf(dayOfMonth));
                textocelendario  = String.valueOf(ActiviDia);
                tdiaselecionado.setText(textocelendario);
                diaCompleto = (dayOfMonth + " / " + (month + 1)+ " / "+year);


            }
        });//-------------------------------------------------------------

        // botão Visualizar todos os dias
        bvisualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ActiviDia >= 1 ) {

                    Intent i = new Intent(PerfilAdmin.this, Admin.class);
                    i.putExtra("visualizaDia", textocelendario);
                    startActivity(i);

                }else {

                Toast.makeText(getApplicationContext(),"Favor escolher um dia",Toast.LENGTH_LONG).show();
                }

            }
        });//----------------------------------------------------------------------------

        //Button retorna o banco ao estado inicial2
        bapaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if  (ActiviDia >= 1 ) {
                    // retorna o banco como zero


                    String dia = ("00" + textocelendario);
                    for (int j = 10; j < 20; j++) {

                        DatabaseReference Datedia = reference.child("datames");
                        String hora = "hora" + j;
                        String cliente = "clihora" + j;
                        String pedido = "pedhora" + j;

                        Datedia.child(dia).child(hora).setValue("Vago");
                        Datedia.child(dia).child(cliente).setValue("Vago");
                        Datedia.child(dia).child(pedido).setValue("Vago");

                    }
                    Toast.makeText(PerfilAdmin.this, " Apagado Agenda Completa do dia: " + diaCompleto , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Favor escolher um dia",Toast.LENGTH_LONG).show();
                }
            }
        });
        bapagaHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if  (ActiviDia >= 1 ) {
                    // retorna o banco como zero


                    Intent i = new Intent(PerfilAdmin.this, RetornaDia.class);
                    i.putExtra(" diaCompleto ", diaCompleto); // nome da hora class
                    i.putExtra("dia", textocelendario);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Favor escolher um dia",Toast.LENGTH_LONG).show();
            }
            }
        });


        bsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autenticacao.signOut();//desloga o usuario
                Toast.makeText(getApplicationContext(),"Usuario: Admin Deslogado com sucesso",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PerfilAdmin.this, MainActivity.class);
                startActivity(i);

            }
        });

    }//--------------------------------------------------------------------------------------------------


    public void iniciaObjeto(){

        bvisualiza =findViewById(R.id.bVisualizar);
        bapaga = findViewById(R.id.bApaga);
        ccalendarView = findViewById(R.id.cCalendarView);
        tdiaselecionado= findViewById(R.id.tDiaSelecionado);
        bsair = findViewById(R.id.bSair);
        bapagaHora = findViewById(R.id.bApagaHora);

    }@Override
    public void onBackPressed() {

    }





}
