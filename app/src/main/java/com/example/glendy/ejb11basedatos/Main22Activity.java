package com.example.glendy.ejb11basedatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main22Activity extends AppCompatActivity {

    private EditText txtMatricula,txtNombre, txtCalificar;
    Button btnConsultar, btnGuardar;


    private SQLiteDatabase baseDatos;
    private  Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        txtMatricula = (EditText) findViewById(R.id.txtMatricula);
        txtNombre =(EditText)findViewById(R.id.txtNombre);
        txtCalificar= (EditText)findViewById(R.id.txtCalificar);

        baseDatos= this.openOrCreateDatabase("escolartecn.db", MODE_PRIVATE,null);


        btnConsultar=(Button) findViewById(R.id.btnConsultar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarDB(v);
            }
        });


        btnGuardar=(Button)findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDB(v);
            }
        });

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
        public void consultarDB(View v) {


            //Abrir o crear una tabla
            String consultaSQL = "Create Table if not exists estudiantes (matricula text PRIMARY KEY, " +
                    "nombre text, semestre integer);";
            baseDatos.execSQL(consultaSQL);//ahora si consultamos


            /*Cursor cursor =baseDatos.rawQuery(" SELECT nombre FROM estudiates WHERE matricula=E12080067 ",null);
            String respuesta=" ";
            Toast.makeText(this, "nombre es:"+respuesta, Toast.LENGTH_LONG).show();*/
           String matricula=txtMatricula.getText().toString();

            //puede ir asi como lo tengo ahorita o directo    +txtMatricula.getText().toString()+
            String consultasql="SELECT matricula, nombre  From estudiantes Where matricula='"+matricula+"';";
            cursor = baseDatos.rawQuery(consultasql,null);
            String respuesta=" ";
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++){
                respuesta+=cursor.getString(1) + "\n";

                cursor.moveToNext();
            }
            //Toast.makeText(this, "nombre es:"+respuesta, Toast.LENGTH_LONG).show();
            //Mostrar Datos
            txtNombre.setText(respuesta);
        }


    public void guardarDB(View v) {

        //Abrir o crear una tabla
        String consultaSQL = "Create Table if not exists calificaciones (matricula text PRIMARY KEY, " +
                "calificacion integer);";
        baseDatos.execSQL(consultaSQL);//ahora si consultamos

        String matricula=txtMatricula.getText().toString();

        Double calificacion=Double.parseDouble(txtCalificar.getText().toString());

        String sql="Insert Into calificaciones(matricula, calificacion) " +
                "VALUES ('"+matricula+"',"+calificacion+");";
        baseDatos.execSQL(sql);

        Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();

        //Abrir o crear una tabla
        /*String consultaSQL = "Create Table if not exists calificaciones (matricula text PRIMARY KEY, " +
                "calificacion integer, FOREIGN KEY matricula REFERENCES estudiantes (matricula));";
        baseDatos.execSQL(consultaSQL);//ahora si consultamos

        Double calificar=Double.parseDouble(txtCalificar.getText().toString());

            String sql="Insert Into calificaciones(matricula, calificacion) " +
                    "VALUES ('E12080071,"+calificar+");";
            baseDatos.execSQL(sql);

            Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();*/
        }

}
