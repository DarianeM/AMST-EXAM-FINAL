package com.example.amstdetectorfuego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class datosSensados extends AppCompatActivity {

    DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_sensados);

        db_reference = FirebaseDatabase.getInstance().getReference().child("GrupoN");
        leerRegistros();
    }

    public void leerRegistros(){
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mostrarRegistrosPorPantalla(snapshot);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }

    public void mostrarRegistrosPorPantalla(DataSnapshot snapshot){
        LinearLayout contFecha = (LinearLayout) findViewById(R.id.ContenedorFecha);
        LinearLayout contIntensidad = (LinearLayout) findViewById(R.id.ContenedorIntensidad);
        LinearLayout contAlarma = (LinearLayout) findViewById(R.id.ContenedorAlarma);

        String intensidadVal = String.valueOf(snapshot.child("uplink_message").child("decoded_payload").child("intensidad").getValue());
        String alarmaVal = String.valueOf(snapshot.child("uplink_message").child("decoded_payload").child("alarma").getValue());
        String dateTime = ZonedDateTime.now(ZoneId.of("-05:00")).format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"));

        if(alarmaVal=="1"){
            alarmaVal = "Fuego";
        } else {
            alarmaVal = "";
        }

        TextView intensidad = new TextView(getApplicationContext());
        intensidad.setText(intensidadVal);
        contIntensidad.addView(intensidad);

        TextView fecha = new TextView(getApplicationContext());
        fecha.setText(dateTime);
        contFecha.addView(fecha);

        TextView alarma = new TextView(getApplicationContext());
        alarma.setText(alarmaVal);
        contAlarma.addView(alarma);
    }
}