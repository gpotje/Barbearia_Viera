package com.example.barbeariavieira.Marcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbeariavieira.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RetornaDia extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    //Button---------------
    private Button bdezr, bonzer, bdozer, bquatror, bcincor, bseisr;
    private Button bseter, boitor, bnover,bcalendarior;

    private TextView tdiar;

    public String diacompleto,textocalendario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retorna_dia);
        iniciObejetos();

        tdiar.setText(diacompleto);
    }



    public void iniciObejetos() {

        Bundle extras = getIntent().getExtras();

        diacompleto = extras.getString("diaCompleto"); // 00 + apenas o dia

        textocalendario = extras.getString("dia");

        textocalendario = "00"+textocalendario;

        tdiar = findViewById(R.id.tDiaR);

        //Button-----
        bdezr = findViewById(R.id.bDezR);
        bonzer = findViewById(R.id.bOnzeR);
        bdozer = findViewById(R.id.bDozeR);
        bquatror = findViewById(R.id.bQuatroR);
        bcincor = findViewById(R.id.bCincoR);
        bseisr = findViewById(R.id.bSeisR);
        bseter = findViewById(R.id.bSeteR);
        boitor = findViewById(R.id.bOitoR);
        bnover =findViewById(R.id.bNoveR);


        // funcoues buttons
        bdezr.setOnClickListener(this);
        bonzer.setOnClickListener(this);
        bdozer.setOnClickListener(this);
        bquatror.setOnClickListener(this);
        bcincor.setOnClickListener(this);
        bseisr.setOnClickListener(this);
        bseter.setOnClickListener(this);
        boitor.setOnClickListener(this);
        bnover.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        if(v == bdezr){

            DatabaseReference Datedia = reference.child("datames");
            String hora = "hora10" ;
            String cliente = "clihora10" ;
            String pedido = "pedhora10" ;

            Datedia.child(textocalendario).child(hora).setValue("Vago");
            Datedia.child(textocalendario).child(cliente).setValue("Vago");
            Datedia.child(textocalendario).child(pedido).setValue("Vago");

            Toast.makeText(this, "Dia" + diacompleto + "10:00 " +" Esta vago novamente", Toast.LENGTH_SHORT).show();


        }
        if(v == bonzer){
            DatabaseReference Datedia = reference.child("datames");
            String hora = "hora11" ;
            String cliente = "clihora11" ;
            String pedido = "pedhora11" ;

            Datedia.child(textocalendario).child(hora).setValue("Vago");
            Datedia.child(textocalendario).child(cliente).setValue("Vago");
            Datedia.child(textocalendario).child(pedido).setValue("Vago");

            Toast.makeText(this, "Dia" + diacompleto + "11:00" +" Esta vago novamente", Toast.LENGTH_SHORT).show();



        }
        if(v == bdozer){
            DatabaseReference Datedia = reference.child("datames");
            String hora = "hora12" ;
            String cliente = "clihora12" ;
            String pedido = "pedhora12" ;

            Datedia.child(textocalendario).child(hora).setValue("Vago");
            Datedia.child(textocalendario).child(cliente).setValue("Vago");
            Datedia.child(textocalendario).child(pedido).setValue("Vago");

            Toast.makeText(this, "Dia" + diacompleto + "12:00" +" Esta vago novamente", Toast.LENGTH_SHORT).show();



        }
        if(v == bquatror){
            DatabaseReference Datedia = reference.child("datames");
            String hora = "hora14" ;
            String cliente = "clihora14" ;
            String pedido = "pedhora14" ;

            Datedia.child(textocalendario).child(hora).setValue("Vago");
            Datedia.child(textocalendario).child(cliente).setValue("Vago");
            Datedia.child(textocalendario).child(pedido).setValue("Vago");

            Toast.makeText(this, "Dia" + diacompleto + "14:00" +" Esta vago novamente", Toast.LENGTH_SHORT).show();



        }
        if(v == bcincor){
            DatabaseReference Datedia = reference.child("datames");
            String hora = "hora15" ;
            String cliente = "clihora15" ;
            String pedido = "pedhora15" ;

            Datedia.child(textocalendario).child(hora).setValue("Vago");
            Datedia.child(textocalendario).child(cliente).setValue("Vago");
            Datedia.child(textocalendario).child(pedido).setValue("Vago");

            Toast.makeText(this, "Dia" + diacompleto + "15:00" +" Esta vago novamente", Toast.LENGTH_SHORT).show();



        }
        if(v == bseisr){
            DatabaseReference Datedia = reference.child("datames");
            String hora = "hora16" ;
            String cliente = "clihora16" ;
            String pedido = "pedhora16" ;

            Datedia.child(textocalendario).child(hora).setValue("Vago");
            Datedia.child(textocalendario).child(cliente).setValue("Vago");
            Datedia.child(textocalendario).child(pedido).setValue("Vago");

            Toast.makeText(this, "Dia" + diacompleto + "16:00" +" Esta vago novamente", Toast.LENGTH_SHORT).show();



        }
        if(v == bseter){
            DatabaseReference Datedia = reference.child("datames");
            String hora = "hora17" ;
            String cliente = "clihora17" ;
            String pedido = "pedhora17" ;

            Datedia.child(textocalendario).child(hora).setValue("Vago");
            Datedia.child(textocalendario).child(cliente).setValue("Vago");
            Datedia.child(textocalendario).child(pedido).setValue("Vago");

            Toast.makeText(this, "Dia" + diacompleto + "17:00" +" Esta vago novamente", Toast.LENGTH_SHORT).show();



        }
        if(v == boitor){
            DatabaseReference Datedia = reference.child("datames");
            String hora = "hora18" ;
            String cliente = "clihora18" ;
            String pedido = "pedhora18" ;

            Datedia.child(textocalendario).child(hora).setValue("Vago");
            Datedia.child(textocalendario).child(cliente).setValue("Vago");
            Datedia.child(textocalendario).child(pedido).setValue("Vago");

            Toast.makeText(this, "Dia" + diacompleto + "18:00" +" Esta vago novamente", Toast.LENGTH_SHORT).show();



        }
        if(v == bnover){
            DatabaseReference Datedia = reference.child("datames");
            String hora = "hora19" ;
            String cliente = "clihora19" ;
            String pedido = "pedhora19" ;

            Datedia.child(textocalendario).child(hora).setValue("Vago");
            Datedia.child(textocalendario).child(cliente).setValue("Vago");
            Datedia.child(textocalendario).child(pedido).setValue("Vago");

            Toast.makeText(this, "Dia" + diacompleto + "19:00" +" Esta vago novamente", Toast.LENGTH_SHORT).show();



        }





    }
}
