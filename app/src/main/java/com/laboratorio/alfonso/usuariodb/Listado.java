package com.laboratorio.alfonso.usuariodb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Listado extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listView = (ListView) findViewById(R.id.List);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Listado.this,listado.get(position),Toast.LENGTH_SHORT).show();
                int clave = Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre = listado.get(position).split(" ")[1];
                String apellido = listado.get(position).split(" ")[2];
                Intent intent = new Intent(Listado.this, Modificar.class);
                intent.putExtra("Id",clave);
                intent.putExtra("nombre",nombre);
                intent.putExtra("apellido",apellido);
                startActivity(intent);
            }
        });

        CargarLista();

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CargarLista(){
        listado = ListaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaPersonas() {
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this, "demo", null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select id, nombre, apellido from personas";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do{
                String linea = cursor.getInt(0) +" "+ cursor.getString(1) +" "+ cursor.getString(2);
                datos.add(linea);

            }while(cursor.moveToNext());
        }
        return datos;
    }
}
