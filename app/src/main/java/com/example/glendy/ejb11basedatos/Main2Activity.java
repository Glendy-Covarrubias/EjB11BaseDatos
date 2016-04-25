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

public class Main2Activity extends AppCompatActivity {

    private EditText txtMatricula, txtNombre, txtSemestre;
    Button btnInsertar;

    //Campos de formulario
    private TextView txtDatos;
    private SQLiteDatabase baseDatos;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Accesar los campos de formulario
        txtDatos= (TextView)findViewById(R.id.txtDatos);

        //Abrir o crear la base de datos
        baseDatos= this.openOrCreateDatabase("escolartecn.db", MODE_PRIVATE, null);

        //Abrir o crear una tabla
        String consultaSQL="Create Table if not exists estudiantes (matricula text PRIMARY KEY, nombre text, semestre integer);";

        baseDatos.execSQL(consultaSQL);//ahora si consultamos

        txtMatricula=(EditText)findViewById(R.id.txtMatricula);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtSemestre=(EditText)findViewById(R.id.txtSemestre);

        btnInsertar=(Button)findViewById(R.id.btnInsertar);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               insertarBD(v);
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

    public  void insertarBD(View v){
        String matricula=txtMatricula.getText().toString();
        String nombre=txtNombre.getText().toString();
        Integer semestre=Integer.parseInt(txtSemestre.getText().toString());

        //Double semestre=Double.parseDouble(txtSemestre.getText().toString());

        String sql="Insert Into estudiantes(matricula, nombre, semestre) " +
                "VALUES ('"+matricula+"','"+nombre+"',"+semestre+");";
        baseDatos.execSQL(sql);

        Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
    }

}
