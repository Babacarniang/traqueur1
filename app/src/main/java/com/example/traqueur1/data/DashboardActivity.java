package com.example.traqueur1.data;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traqueur1.GestionDeFlotte;
import com.example.traqueur1.R;
import com.example.traqueur1.data.model.User;
import com.example.traqueur1.ui.HostActivity;

//import com.example.traqueur1.GestionDeFlotte;

public class DashboardActivity extends AppCompatActivity {
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        TextView bienvenueText = findViewById(R.id.bienvenueText);

        bienvenueText.setText("BIENVENUE " + user.getFullName() + ", DANS PCS TRACKING ," +
                " Votre session expirera le" + user.getSessionExpiryDate());


        ImageView imageView = findViewById(R.id.image);
        Button SuivantBtn = findViewById(R.id.btnUser);

        SuivantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                Intent i = new Intent(DashboardActivity.this, HostActivity.class);
                startActivity(i);
                finish();

            }
        });
        Button GestionBtn = findViewById(R.id.btnAdmin);

        GestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, GestionDeFlotte.class);
                startActivity(i);
                finish();

            }
        });
    }
}
