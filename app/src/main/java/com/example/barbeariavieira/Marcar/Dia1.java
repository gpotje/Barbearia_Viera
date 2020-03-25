package com.example.barbeariavieira.Marcar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbeariavieira.R;
import com.example.barbeariavieira.model.DataMes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dia1 extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    //Button---------------
    private Button bdez, bonze, bdoze, bquatro, bcinco, bseis;
    private Button bsete, boito, bnove,bcalendario;


    //textos---------------
    private TextView tdez, tonze, tdoze, tquatro, tcinco, tseis;
    private TextView tsete, toito, tnove,tdia;

    public int con10 = 0,con11 = 0,con12 = 0,con14 = 0,con15 = 0,con16 = 0,con17 = 0,con18 = 0,con19 = 0;

    public  String diaSelecionado  , nomeUsuario ,DiaNobanco , diaMesAno ,dataFormatada;
    public int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia1);

        iniciObejetos();

       diaDinamico();

       bcalendario.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(Dia1.this, Calendario.class);
               i.putExtra("nome", nomeUsuario);
               startActivity(i);

           }
       });




    }


    public void iniciObejetos() {

        Bundle extras = getIntent().getExtras();

        DiaNobanco = extras.getString("textoCalendario"); // 00 + apenas o dia

         diaMesAno = extras.getString("diamesano");

         nomeUsuario = extras.getString("nome"); // nome do ususario


        //Button-----
        bdez = findViewById(R.id.bDezR);
        bonze = findViewById(R.id.bOnzeR);
        bdoze = findViewById(R.id.bDozeR);
        bquatro = findViewById(R.id.bQuatroR);
        bcinco = findViewById(R.id.bCincoR);
        bseis = findViewById(R.id.bSeisR);
        bsete = findViewById(R.id.bSeteR);
        boito = findViewById(R.id.bOitoR);
        bnove =findViewById(R.id.bNoveR);
        bcalendario = findViewById(R.id.bCalendario);

        // funcoues buttons
        bdez.setOnClickListener(this);
        bonze.setOnClickListener(this);
        bdoze.setOnClickListener(this);
        bquatro.setOnClickListener(this);
        bcinco.setOnClickListener(this);
        bseis.setOnClickListener(this);
        bsete.setOnClickListener(this);
        boito.setOnClickListener(this);
        bnove.setOnClickListener(this);



        //textos-----
        tdia =findViewById(R.id.tDiaR);

        tdez = findViewById(R.id.t1Dez);
        tonze = findViewById(R.id.tOnze);
        tdoze = findViewById(R.id.tdose);
        tquatro = findViewById(R.id.tQuatro);
        tcinco = findViewById(R.id.tCinco);
        tseis = findViewById(R.id.tSeis);
        tsete = findViewById(R.id.tSete);
        toito = findViewById(R.id.tOito);
        tnove = findViewById(R.id.tNove);

    }


    public void diaDinamico() {
        // traz do banco

        tdia.setText(diaMesAno);// seta a data no canto de cima

        diaSelecionado = (DiaNobanco);

        DatabaseReference datateste = reference.child("datames");
        DatabaseReference usuarioPesquisas = datateste.child(diaSelecionado);


        usuarioPesquisas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataMes dataMes = dataSnapshot.getValue(DataMes.class);

                //popula os horarios do dia
                tdez.setText(dataMes.getHora10());
               tonze.setText(dataMes.getHora11());
               tdoze.setText(dataMes.getHora12());
               tquatro.setText(dataMes.getHora14());
               tcinco.setText(dataMes.getHora15());
               tseis.setText(dataMes.getHora16());
               tsete.setText(dataMes.getHora17());
               toito.setText(dataMes.getHora18());
               tnove.setText(dataMes.getHora19());

                // strings para comparação
                String tdezS = dataMes.getHora10();
                String tonzeS = dataMes.getHora11();
                String tdozeS = dataMes.getHora12();
                String tquatroS = dataMes.getHora14();
                String tcincoS = dataMes.getHora15();
                String tseisS = dataMes.getHora16();
                String tseteS = dataMes.getHora17();
                String toitoS = dataMes.getHora18();
                String tnoveS = dataMes.getHora19();


               if(tdezS.equals("Vago")){
                  con10++;
               }
                if(tonzeS.equals("Vago")){
                    con11++;
                }
                if(tdozeS.equals("Vago")){
                    con12++;
                }
                if(tquatroS.equals("Vago")){
                    con14++;
                }
                if(tcincoS.equals("Vago")){
                    con15++;
                }
                if(tseisS.equals("Vago")){
                    con16++;
                }
                if(tseteS.equals("Vago")){
                    con17++;
                }
                if(toitoS.equals("Vago")){
                    con18++;
                }
                if(tnoveS.equals("Vago")){
                    con19++;
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    @Override
    public void onClick(View v) {


        String horaClass ="";
        String clienteClass = "";
        String horacliClass = "" ;
        String horaFormatadaParaMenssagem = "";


        if(v == bdez ) {
            if (con10 >= 1){
            horacliClass = "pedhora10";
            clienteClass = "clihora10";
            horaClass = "hora10";
            horaFormatadaParaMenssagem ="10:00";

            Intent i = new Intent(Dia1.this, TabelaSeServico.class);
            i.putExtra("horario", horaClass); // nome da hora class
            i.putExtra("horaFormatadaParaMenssagem",horaFormatadaParaMenssagem);
            i.putExtra("DiaSelecionado", diaSelecionado); //data do calendario
            i.putExtra("Cliente", clienteClass); // nome do cliente na class
            i.putExtra("dataFormatada",diaMesAno); // dataformatada
            i.putExtra("horacli",horacliClass);
            i.putExtra("nome", nomeUsuario);
            startActivity(i);

            DatabaseReference Datedia = reference.child("datames");
            Datedia.child(diaSelecionado).child("hora10").setValue("ocupado");
        }else {

            Toast.makeText(getApplicationContext(),"Este dia já esta ocupado",Toast.LENGTH_LONG).show();

        }
        }
        if(v == bonze){
            if(con11 >= 1) {
                horacliClass = "pedhora11";
                clienteClass = "clihora11";
                horaClass = "hora11";
                horaFormatadaParaMenssagem ="11:00";

                Intent i = new Intent(Dia1.this, TabelaSeServico.class);
                i.putExtra("horario", horaClass);
                i.putExtra("horaFormatadaParaMenssagem",horaFormatadaParaMenssagem);
                i.putExtra("DiaSelecionado", diaSelecionado);
                i.putExtra("Cliente", clienteClass);
                i.putExtra("horacli",horacliClass);
                i.putExtra("dataFormatada",diaMesAno); // dataformatada
                i.putExtra("nome", nomeUsuario);
                startActivity(i);

                DatabaseReference Datedia = reference.child("datames");
                Datedia.child(diaSelecionado).child("hora11").setValue("ocupado");

            }else{
                Toast.makeText(getApplicationContext(),"Este dia já esta ocupado",Toast.LENGTH_LONG).show();
            }

        }
        if(v == bdoze){
            if(con12 >= 1){
                horacliClass = "pedhora12";
                clienteClass = "clihora12";
                horaClass = "hora12";
                horaFormatadaParaMenssagem ="12:00";

                Intent i = new Intent(Dia1.this, TabelaSeServico.class);
                i.putExtra("horario", horaClass);
                i.putExtra("horaFormatadaParaMenssagem",horaFormatadaParaMenssagem);
                i.putExtra("DiaSelecionado", diaSelecionado);
                i.putExtra("Cliente", clienteClass);
                i.putExtra("horacli",horacliClass);
                i.putExtra("dataFormatada",diaMesAno); // dataformatada
                i.putExtra("nome", nomeUsuario);
                startActivity(i);

                DatabaseReference Datedia = reference.child("datames");
                Datedia.child(diaSelecionado).child("hora12").setValue("ocupado");
            }else{
                Toast.makeText(getApplicationContext(),"Este dia já esta ocupado",Toast.LENGTH_LONG).show();
            }
        }
        if(v == bquatro ){
            if(con14 >= 1 ) {
                horacliClass = "pedhora14";
                clienteClass = "clihora14";
                horaClass = "hora14";
                horaFormatadaParaMenssagem ="14:00";
                Intent i = new Intent(Dia1.this, TabelaSeServico.class);
                i.putExtra("horario", horaClass);
                i.putExtra("horaFormatadaParaMenssagem",horaFormatadaParaMenssagem);
                i.putExtra("DiaSelecionado", diaSelecionado);
                i.putExtra("Cliente", clienteClass);
                i.putExtra("horacli",horacliClass);
                i.putExtra("dataFormatada",diaMesAno); // dataformatada
                i.putExtra("nome", nomeUsuario);
                startActivity(i);

                DatabaseReference Datedia = reference.child("datames");
                Datedia.child(diaSelecionado).child("hora14").setValue("ocupado");
            }else{
                Toast.makeText(getApplicationContext(),"Este dia já esta ocupado",Toast.LENGTH_LONG).show();
            }
        }
        if(v == bcinco ){
            if(con15 >= 1) {

                horacliClass = "pedhora15";
                clienteClass = "clihora15";
                horaClass = "hora15";
                horaFormatadaParaMenssagem ="15:00";

                Intent i = new Intent(Dia1.this, TabelaSeServico.class);
                i.putExtra("horario", horaClass);
                i.putExtra("horaFormatadaParaMenssagem",horaFormatadaParaMenssagem);
                i.putExtra("DiaSelecionado", diaSelecionado);
                i.putExtra("Cliente", clienteClass);
                i.putExtra("horacli",horacliClass);
                i.putExtra("dataFormatada",diaMesAno); // dataformatada
                i.putExtra("nome", nomeUsuario);
                startActivity(i);

                DatabaseReference Datedia = reference.child("datames");
                Datedia.child(diaSelecionado).child("hora15").setValue("ocupado");
            }else{
                Toast.makeText(getApplicationContext(),"Este dia já esta ocupado",Toast.LENGTH_LONG).show();
            }
        }
        if(v == bseis ){
            if(con16 >= 1) {
                horacliClass = "pedhora16";
                clienteClass = "clihora16";
                horaClass = "hora16";
                horaFormatadaParaMenssagem ="16:00";

                Intent i = new Intent(Dia1.this, TabelaSeServico.class);
                i.putExtra("horario", horaClass);
                i.putExtra("horaFormatadaParaMenssagem",horaFormatadaParaMenssagem);
                i.putExtra("DiaSelecionado", diaSelecionado);
                i.putExtra("Cliente", clienteClass);
                i.putExtra("horacli",horacliClass);
                i.putExtra("dataFormatada",diaMesAno); // dataformatada
                i.putExtra("nome", nomeUsuario);
                startActivity(i);

                DatabaseReference Datedia = reference.child("datames");
                Datedia.child(diaSelecionado).child("hora16").setValue("ocupado");
            }else{
                Toast.makeText(getApplicationContext(),"Este dia já esta ocupado",Toast.LENGTH_LONG).show();
            }
        }
        if(v == bsete ){
           if(con17 >= 1 ) {
               horacliClass = "pedhora17";
               clienteClass = "clihora17";
               horaClass = "hora17";
               horaFormatadaParaMenssagem ="17:00";

                Intent i = new Intent(Dia1.this, TabelaSeServico.class);
                i.putExtra("horario", horaClass);
               i.putExtra("horaFormatadaParaMenssagem",horaFormatadaParaMenssagem);
                i.putExtra("DiaSelecionado", diaSelecionado);
                i.putExtra("Cliente", clienteClass);
                i.putExtra("nome", nomeUsuario);
               i.putExtra("dataFormatada",diaMesAno); // dataformatada
                i.putExtra("horacli",horacliClass);
                startActivity(i);

                DatabaseReference Datedia = reference.child("datames");
                Datedia.child(diaSelecionado).child("hora17").setValue("ocupado");
            }else{
                Toast.makeText(getApplicationContext(),"Este dia já esta ocupado",Toast.LENGTH_LONG).show();
            }
        }
        if(v == boito ){
            if(con18 >= 1){

                horacliClass = "pedhora18";
                clienteClass = "clihora18";
                horaClass = "hora18";
                horaFormatadaParaMenssagem ="18:00";

                Intent i = new Intent(Dia1.this, TabelaSeServico.class);
                i.putExtra("horario", horaClass);
                i.putExtra("horaFormatadaParaMenssagem",horaFormatadaParaMenssagem);
                i.putExtra("DiaSelecionado", diaSelecionado);
                i.putExtra("Cliente", clienteClass);
                i.putExtra("horacli",horacliClass);
                i.putExtra("dataFormatada",diaMesAno); // dataformatada
                i.putExtra("nome", nomeUsuario);
                startActivity(i);

                DatabaseReference Datedia = reference.child("datames");
                Datedia.child(diaSelecionado).child("hora18").setValue("ocupado");
            }else{
                Toast.makeText(getApplicationContext(),"Este dia já esta ocupado",Toast.LENGTH_LONG).show();
            }
        }
        if(v == bnove ){
            if(con19 >= 1) {
                horacliClass = "pedhora19";
                clienteClass = "clihora19";
                horaClass = "hora19";
                horaFormatadaParaMenssagem ="19:00";

                Intent i = new Intent(Dia1.this, TabelaSeServico.class);
                i.putExtra("horario", horaClass);
                i.putExtra("horaFormatadaParaMenssagem",horaFormatadaParaMenssagem);
                i.putExtra("DiaSelecionado", diaSelecionado);
                i.putExtra("Cliente", clienteClass);
                i.putExtra("horacli",horacliClass);
                i.putExtra("dataFormatada",diaMesAno); // dataformatada
                i.putExtra("nome", nomeUsuario);
                startActivity(i);

                DatabaseReference Datedia = reference.child("datames");
                Datedia.child(diaSelecionado).child("hora19").setValue("ocupado");
            }else{
                Toast.makeText(getApplicationContext(),"Este dia já esta ocupado",Toast.LENGTH_LONG).show();
            }
        }



    }
    @Override
    public void onBackPressed() {

    }
}