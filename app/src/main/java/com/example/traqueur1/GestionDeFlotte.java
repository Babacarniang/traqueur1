package com.example.traqueur1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traqueur1.ui.HostActivity;
import com.example.traqueur1.ui.appareil.MapsActivity;

public class GestionDeFlotte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_flotte);

        Button SuivremaflotteBtn = findViewById(R.id.idsuivre);

        SuivremaflotteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GestionDeFlotte.this, MapsActivity.class);
                startActivity(i);
                finish();

            }
        });

        Button SuppModiBtn = findViewById(R.id.idmodsup);

        SuppModiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GestionDeFlotte.this, HostActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}
