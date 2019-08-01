package com.example.traqueur1.data;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traqueur1.R;
import com.example.traqueur1.ui.appareil.AppareilPositionService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, AppareilPositionService.class));
    }
}
