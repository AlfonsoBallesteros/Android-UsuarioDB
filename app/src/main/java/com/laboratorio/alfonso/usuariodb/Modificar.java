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

public class Modificar extends AppCompatActivity {
    EditText nombre, apellido;
    Button btnmodificar, btneliminar;
    int id;
    String Nombre;
    String Apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b = getIntent().getExtras();
        if(b != null){
            id = b.getInt("Id");
            Nombre = b.getString("nombre");
            Apellido = b.getString("apellido");
        }

        nombre = (EditText) findViewById(R.id.et_nombre);
        apellido = (EditText) findViewById(R.id.et_apellido);

        nombre.setText(Nombre);
        apellido.setText(Apellido);

        btnmodificar = (Button) findViewById(R.id.btn_modificar);
        btneliminar = (Button) findViewById(R.id.btn_eliminar);

        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //guardar(nombre.getText().toString(),apellido.getText().toString());
                modificar(id,nombre.getText().toString(),apellido.getText().toString());
                onBackPressed();
            }
        });
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(id);
                onBackPressed();
            }
        });
    }
    private void modificar (int id, String nombre, String apellido){
        BaseHelper helper = new BaseHelper(this, "demo", null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "update personas set nombre='"+ nombre +"', apellido='"+ apellido +"' where id="+id;

        db.execSQL(sql);
        db.close();
        Toast.makeText(this,"Actualizacion exitosa",Toast.LENGTH_SHORT).show();
    }
    private void eliminar (int id){
        BaseHelper helper = new BaseHelper(this, "demo", null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "delete from personas where id="+id;

        db.execSQL(sql);
        db.close();
        Toast.makeText(this,"eliminacion exitosa",Toast.LENGTH_SHORT).show();
    }
}
