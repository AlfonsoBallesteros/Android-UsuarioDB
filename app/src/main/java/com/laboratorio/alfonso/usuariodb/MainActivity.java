package com.laboratorio.alfonso.usuariodb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre, apellido;
    Button btnmostrar, btnguardad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.et_nombre);
        apellido = (EditText) findViewById(R.id.et_apellido);

        btnguardad = (Button) findViewById(R.id.btn_guardar);
        btnmostrar = (Button) findViewById(R.id.btn_mostrar);

        btnguardad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(nombre.getText().toString(),apellido.getText().toString());
                nombre.setText("");
                apellido.setText("");
                nombre.requestFocus();
            }
        });
        btnmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Listado.class));
            }
        });

    }
    private void guardar (String nombre, String apellido){
        BaseHelper helper = new BaseHelper(this, "demo", null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues content = new ContentValues();
            content.put("nombre",nombre);
            content.put("apellido",apellido);
            db.insert("personas",null, content);
            db.close();
            Toast.makeText(this,"Registro insertado",Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
