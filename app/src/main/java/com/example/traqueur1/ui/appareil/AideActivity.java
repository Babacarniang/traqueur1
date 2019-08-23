package com.example.traqueur1.ui.appareil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.traqueur1.R;

public class AideActivity extends Activity {
    String msg = "Android : ";

    TextView idaide;
    EditText idblem;
    Button Sauve;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);
        Log.d(msg, "The onCreate() event");


        TextView aide = findViewById(R.id.idaide);

        EditText blem = findViewById(R.id.idblem);
        Button Aide = findViewById(R.id.idsauve);



      /*  Aide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AideActivity.this, GestionDeFlotte.class);
                startActivity(i);
                finish();

            }
        });*/
    }

}