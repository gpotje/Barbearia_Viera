package com.example.barbeariavieira.Marcar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.widget.TextView;
import android.widget.Toast;

import com.example.barbeariavieira.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Calendario extends AppCompatActivity {

    // banco de dados
    public DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    //------------------------------------------

    public String textocelendario,diamesano;
    public int ActiviDia;

    // CalendarView----------------------------
    private CalendarView mcalendarView;

    //------------------------------------------

    private TextView teste;
    public String nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        // -------------------------------------    inicio CalendarView----------------------------
        mcalendarView = findViewById(R.id.mCalendarView);
        mcalendarView.setFirstDayOfWeek(1);
        //------------------------------------------
        mcalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //pega o dia mes e ano desejado pelo usuario



                diamesano = (dayOfMonth + " / " + (month + 1) + " / " + year);
                ActiviDia = (int) Integer.parseInt(String.valueOf(dayOfMonth));
                textocelendario  = String.valueOf(ActiviDia);
                textocelendario =("00"+textocelendario);


                retornarDiaSemana(year, month + 1, dayOfMonth);

            }
        });

    }

    @Override
    public void onBackPressed() {


    }

    //retorna o dia da semana dada uma data
    public void retornarDiaSemana(int ano, int mes, int dia) {
        int Ano = ano;
        int Mes = mes;
        int Dia = dia;

        Bundle extras = getIntent().getExtras();

        nome = extras.getString("nome");


        Calendar calendario = new GregorianCalendar(Ano, Mes -1  , Dia);

        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

        switch (diaSemana){
            case 1 :
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Olá "  + nome);
                builder.setMessage("Domingo é por ordem de Chegada");
                builder.setCancelable(false);
                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            case 2 :

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Lamentamos " + nome);
                builder1.setMessage("Segunda-feira não estão funcionando \n Agradeçemos a preferência");
                builder1.setCancelable(false);
                builder1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog1 = builder1.create();
                dialog1.show();

                break;
             default:

                 Intent i = new Intent(Calendario.this, Dia1.class);
                 i.putExtra("textoCalendario", textocelendario);
                 i.putExtra("diamesano",diamesano);
                 i.putExtra("nome",nome);
                 startActivity(i);

                 DatabaseReference datateste = reference.child("datames");
                 DatabaseReference usuarioPesquisas = datateste.child(textocelendario);

                 break;
        }

    }


    }











