package com.barto.week2pickerfecha;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActualizacionDatos extends AppCompatActivity  {

    private String nombreString;
    private String apellidoString;
    private String telefonoString;
    private String emailString;
    private String descripcionString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_actualizacion);

        Bundle extras = getIntent().getExtras();
        int day = extras.getInt("day");
        int month = extras.getInt("month");
        int year = extras.getInt("year");
        nombreString = (String) extras.get("nombre");
        apellidoString = (String) extras.get("apellido");
        telefonoString = (String) extras.get("telefono");
        emailString = (String) extras.get("email");
        descripcionString = (String) extras.get("descripcion");

        TextView nombre = (TextView) findViewById(R.id.tvNombre);
        TextView apellido = (TextView) findViewById(R.id.tvApellido);
        TextView fecha = (TextView) findViewById(R.id.tvFecha);
        TextView telefono = (TextView) findViewById(R.id.tvTelefono);
        TextView email = (TextView) findViewById(R.id.tvEmail);
        TextView descripcion = (TextView) findViewById(R.id.tvDescripcion);

        nombre.setText(nombreString);
        apellido.setText(apellidoString);
        fecha.setText(new StringBuilder().append(day)
                .append("-").append(month + 1).append("-").append(year));
        telefono.setText(telefonoString);
        email.setText(emailString);
        descripcion.setText(descripcionString);

        PRU();
    }

    public void PRU(){
        Button btEditarDatos = (Button) findViewById(R.id.btnEditarDatos);
        btEditarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActualizacionDatos.this, MainActivity.class);
                String nombre = (((TextView) findViewById(R.id.tvNombre)).getText().toString());
                String apellido = (((TextView) findViewById(R.id.tvApellido)).getText().toString());
                String telefono = (((TextView) findViewById(R.id.tvTelefono)).getText().toString());
                String email = (((TextView) findViewById(R.id.tvEmail)).getText().toString());
                String descripcion = (((TextView) findViewById(R.id.tvDescripcion)).getText().toString());
                String fecha = (((TextView) findViewById(R.id.tvFecha)).getText().toString());
                //fecha = fecha+"-";
               // i.putExtra("year", year);
               // i.putExtra("month", month);
               // i.putExtra("day", day);


                i.putExtra("nombre", nombre);
                i.putExtra("apellido", apellido);
                i.putExtra("fecha", fecha);
                i.putExtra("telefono", telefono);
                i.putExtra("email", email);
                i.putExtra("descripcion", descripcion);

                startActivity(i);
                finish();
            }
        });
    }

}
