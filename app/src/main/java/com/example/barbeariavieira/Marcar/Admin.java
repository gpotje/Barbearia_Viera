package com.example.barbeariavieira.Marcar;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.barbeariavieira.R;
import com.example.barbeariavieira.model.Cliente;
import com.example.barbeariavieira.model.DataMes;
import com.example.barbeariavieira.model.PedidoCorte;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity {

    private TextView tcliente10,tpedido10,tcliente11,tpedido11,tcliente12,tpedido12,tcliente14,tpedido14,tcliente15,tpedido15;
    private TextView tcliente16,tpedido16,tcliente17,tpedido17,tcliente18,tpedido18,tcliente19,tpedido19;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);




         iniciaObjeto();
        populaPesquisa();


    }


    public void iniciaObjeto(){

        tcliente10 = findViewById(R.id.tCliente10);
        tpedido10 = findViewById(R.id.tCorte10);
        //----------------------------------------------
        tcliente11 = findViewById(R.id.tCliente11);
        tpedido11 = findViewById(R.id.tCorte11);
        //----------------------------------------------
        tcliente12 = findViewById(R.id.tCliente12);
        tpedido12 = findViewById(R.id.tCorte12);
        //----------------------------------------------
        tcliente14 = findViewById(R.id.tCliente14);
        tpedido14 = findViewById(R.id.tCorte14);
        //----------------------------------------------
        tcliente15 = findViewById(R.id.tCliente15);
        tpedido15 = findViewById(R.id.tCorte15);
        //----------------------------------------------
        tcliente16 = findViewById(R.id.tCliente16);
        tpedido16 = findViewById(R.id.tCorte16);
        //----------------------------------------------
        tcliente17 = findViewById(R.id.tCliente17);
        tpedido17 = findViewById(R.id.tCorte17);
        //----------------------------------------------
        tcliente18 = findViewById(R.id.tCliente18);
        tpedido18 = findViewById(R.id.tCorte18);
        //----------------------------------------------
        tcliente19 = findViewById(R.id.tCliente19);
        tpedido19 = findViewById(R.id.tCorte19);

        //----------------------------------------------


    }

    public void populaPesquisa(){

        Bundle extras = getIntent().getExtras();

       String  userid = extras.getString("visualizaDia");
       userid = ("00"+userid);

        DatabaseReference datateste = reference.child("datames");
        DatabaseReference usuarioPesquisas = datateste.child(userid);


        usuarioPesquisas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataMes dataMes = dataSnapshot.getValue(DataMes.class);
                Cliente cliente = dataSnapshot.getValue(Cliente.class);


                PedidoCorte pedidoCorte = dataSnapshot.getValue(PedidoCorte.class);

                //popula os horarios do dia
                //-----------------------------------------------
                tcliente10.setText(cliente.getClihora10());
                tpedido10.setText(pedidoCorte.getPedhora10());
                //-----------------------------------------------
                tcliente11.setText(cliente.getClihora11());
                tpedido11.setText(pedidoCorte.getPedhora11());
                //-----------------------------------------------
                tcliente12.setText(cliente.getClihora12());
                tpedido12.setText(pedidoCorte.getPedhora12());
                //-----------------------------------------------
                tcliente14.setText(cliente.getClihora14());
                tpedido14.setText(pedidoCorte.getPedhora14());
                //-----------------------------------------------
                tcliente15.setText(cliente.getClihora15());
                tpedido15.setText(pedidoCorte.getPedhora15());
                //-----------------------------------------------
                tcliente16.setText(cliente.getClihora16());
                tpedido16.setText(pedidoCorte.getPedhora16());
                //-----------------------------------------------
                tcliente17.setText(cliente.getClihora17());
                tpedido17.setText(pedidoCorte.getPedhora17());
                //-----------------------------------------------
                tcliente18.setText(cliente.getClihora18());
                tpedido18.setText(pedidoCorte.getPedhora18());
                //-----------------------------------------------
                tcliente19.setText(cliente.getClihora19());
                tpedido19.setText(pedidoCorte.getPedhora19());
                //-----------------------------------------------

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
