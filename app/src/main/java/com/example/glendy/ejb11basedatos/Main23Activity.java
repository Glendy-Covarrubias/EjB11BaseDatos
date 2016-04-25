package com.example.glendy.ejb11basedatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Main23Activity extends AppCompatActivity {

    //Campos de formulario
    private TextView txtDatos;
    private SQLiteDatabase baseDatos;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);

        //Accesar los campos del formulario
        txtDatos=(TextView)findViewById(R.id.txtDatos);

        //Abrir o crear la base de datos
        baseDatos= this.openOrCreateDatabase("escolartecn.db", MODE_PRIVATE, null);

        //Abrir o crear una tabla
        String consultaSQL="Create Table if not exists calificaciones (matricula text PRIMARY KEY,calificacion integer);";

        baseDatos.execSQL(consultaSQL);//ahora si consultamos

        //explorar la base de datos
        explorarDB();


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public  void explorarDB(){
        String sql="SELECT estudiantes.matricula, estudiantes.nombre, calificaciones.calificacion From estudiantes, calificaciones Where estudiantes.matricula=calificaciones.matricula;";
        cursor = baseDatos.rawQuery(sql,null);
        String respuesta=" ";
        cursor.moveToFirst();
        for (int i=0; i<cursor.getCount(); i++){
            respuesta+=cursor.getString(0) + "\t";
            respuesta+=cursor.getString(1) + "\t";
            respuesta+=cursor.getInt(2) + "\n";

            cursor.moveToNext();
        }

        txtDatos.setText(respuesta);
    }

}
