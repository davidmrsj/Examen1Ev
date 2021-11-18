package com.example.examen1ev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spin1;
    Spinner spin2;
    Spinner spin3;
    Spinner spin4;
    String numero;
    TextView num;
    String [] nombre = {"Doble", "Apocaliptico"};
    int[] icono = {R.drawable.x2,R.drawable.apocaliptico};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numero="";
        rellenaSpinner();

        Spinner operacion = (Spinner) findViewById(R.id.spinnerPerso);
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, nombre);
        operacion.setAdapter(a);

    }

    public void rellenaSpinner(){
        String [] numeros = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9"};

        ArrayAdapter <String> adaptador;
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, numeros);

        spin1 = (Spinner) findViewById(R.id.spinner);
        spin2 = (Spinner) findViewById(R.id.spinner2);
        spin3 = (Spinner) findViewById(R.id.spinner3);
        spin4 = (Spinner) findViewById(R.id.spinner4);
        spin1.setAdapter(adaptador);
        spin2.setAdapter(adaptador);
        spin3.setAdapter(adaptador);
        spin4.setAdapter(adaptador);
        spin1.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);
        spin3.setOnItemSelectedListener(this);
        spin4.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        num = (TextView) findViewById(R.id.txtNumero);
        numero = numero + parent.getSelectedItem().toString();

        num.setText(numero);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        num.setText("Numero");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.fecha){
            Date fecha = Calendar.getInstance().getTime();
            SimpleDateFormat dateTiFor = new SimpleDateFormat("dd/MM/yyyy");
            Toast tost = Toast.makeText(getApplicationContext(), dateTiFor.format(fecha), Toast.LENGTH_LONG);
            tost.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class AdaptadorPerso extends ArrayAdapter<String>{

        public AdaptadorPerso(@NonNull Context context, int resource, @NonNull String[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFilaPerso(position, convertView, parent);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFilaPerso(position, convertView, parent);
        }

        public View crearFilaPerso(int position, View convertView, ViewGroup padre){
            LayoutInflater inflar = getLayoutInflater();
            View mfila = inflar.inflate(R.layout.fila, padre, false);

            TextView nombres = (TextView) findViewById(R.id.textView4);
            nombres.setText(nombre[position]);
            ImageView icon = (ImageView) findViewById(R.id.imageView3);
            icon.setImageResource(icono[position]);

            return mfila;
        }
    }

    public void doblarNum(View view){
        numero=String.valueOf(Integer.parseInt(numero)*2);
        TextView resultado = findViewById(R.id.textView2);
        resultado.setText(numero);
        numero = "";
        num.setText(numero);
    }

}