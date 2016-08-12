package com.barto.week2pickerfecha;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;

    public int y = 0;
    public int year;
    public int month;
    public int day;
    public String dayS;
    private String nombreString;
    public String apellidoString;
    public String telefonoString;
    private String emailString;
    private String descripcionString;
    private String fechaString;

    public TextInputLayout nombreinput;
    public TextInputLayout apellidoinput;
    public TextInputLayout telefonoinput;
    public TextInputLayout emailinput;
    public TextInputLayout descripcioninput;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombreinput = (TextInputLayout) findViewById(R.id.tiNombreLayout);
        apellidoinput = (TextInputLayout) findViewById(R.id.tiApellidoLayout);
        telefonoinput = (TextInputLayout) findViewById(R.id.tiTelefonoLayout);
        emailinput = (TextInputLayout) findViewById(R.id.tiEmailLayout);
        descripcioninput = (TextInputLayout) findViewById(R.id.tiDescripcionLayout);

        retornovar();
        setCurrentDateOnView();
        addListenerOnButton();
        btSiguienteButton();


    }

    public void retornovar(){
        Bundle extras = getIntent().getExtras();
        EditText nombreinput1 = (EditText) findViewById(R.id.tiNombre);
        EditText apellidoinput1 = (EditText) findViewById(R.id.tiApellido);
        EditText telefonoinput1 = (EditText) findViewById(R.id.tiTelefono);
        EditText emailinput1 = (EditText) findViewById(R.id.tiEmail);
        EditText descripcioninput1 = (EditText) findViewById(R.id.tiDescripcion);

        if (extras != null) {
            nombreString =  extras.getString("nombre"); // nombreString =  (String) extras.get("nombre"); // es equivalente
            apellidoString =  extras.getString("apellido");
            fechaString = (String) extras.get("fecha");
            telefonoString =  extras.getString("telefono");
            emailString =  extras.getString("email");
            descripcionString =  extras.getString("descripcion");

            String[] pur = fechaString.split("-");
            String part1 = pur[0];
            String part2 = pur[1];
            String part3 = pur[2];
            day = Integer.valueOf(pur[0]);
            month = Integer.valueOf(pur[1]);month=month-1;
            year = Integer.valueOf(pur[2]);

            nombreinput1.setText(nombreString);
            apellidoinput1.setText(apellidoString);
            telefonoinput1.setText(telefonoString);
            emailinput1.setText(emailString);
            descripcioninput1.setText(descripcionString);
            y = 1;
        }
    }

    // display current date
    public void setCurrentDateOnView() {
        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        final Calendar c = Calendar.getInstance();

        if (y == 0) {
            year = c.get(Calendar.YEAR); //OBTIENE LAS FECHAS ACTUALES
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

        }
            // set current date into textview
            tvDisplayDate.setText(new StringBuilder()
                    // Month is 0 based, just add 1
                    .append(day).append("-").append(month+1).append("-")
                    .append(year).append(" "));

    }

    public void addListenerOnButton() {
        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        btnChangeDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            StringBuilder sb1 =new StringBuilder().append(day)
                    .append("-").append(month+1).append("-").append(year)
                    .append(" ");
            tvDisplayDate.setText(sb1);
        }
    };

    public void btSiguienteButton(){
        Button btSiguiente = (Button) findViewById(R.id.btSiguiente);
        btSiguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ActualizacionDatos.class);
                String nombre = (((TextInputEditText) findViewById(R.id.tiNombre)).getText().toString());
                String apellido = (((TextInputEditText) findViewById(R.id.tiApellido)).getText().toString());
                String telefono = (((TextInputEditText) findViewById(R.id.tiTelefono)).getText().toString());
                String email = (((TextInputEditText) findViewById(R.id.tiEmail)).getText().toString());
                String descripcion = (((TextInputEditText) findViewById(R.id.tiDescripcion)).getText().toString());

                i.putExtra("year", year);
                i.putExtra("month", month);
                i.putExtra("day", day);
                i.putExtra("nombre", nombre);
                i.putExtra("apellido", apellido);
                i.putExtra("telefono", telefono);
                i.putExtra("email", email);
                i.putExtra("descripcion", descripcion);

                if ((TextUtils.isEmpty(nombre)) || (TextUtils.isEmpty(apellido))
                        || (TextUtils.isEmpty(telefono)||(TextUtils.isEmpty(email)))){
                    nombreinput.setError("");apellidoinput.setError("");
                    telefonoinput.setError("");emailinput.setError("");
                    if ((TextUtils.isEmpty(nombre))){nombreinput.setError("This field is required");}
                    if ((TextUtils.isEmpty(apellido))){apellidoinput.setError("This field is required");}
                    if ((TextUtils.isEmpty(telefono))){telefonoinput.setError("This field is required");}
                    if ((TextUtils.isEmpty(email))){emailinput.setError("This field is required");}
                }
                else {
                    startActivity(i);
                    finish();
                }
            }

        });
    }
}