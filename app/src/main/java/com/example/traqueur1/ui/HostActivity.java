package com.example.traqueur1.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.traqueur1.R;
import com.example.traqueur1.ui.appareil.AjoutAppareilFragment;
import com.example.traqueur1.ui.appareil.ListAppareilFragment;
import com.example.traqueur1.ui.appareil.MapsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HostActivity extends AppCompatActivity {

    public static final String API_KEY = "INSERT_YOUR_API_KEY_HERE";
    FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_ajout:
                    AjoutAppareilFragment fragmentAjout = new AjoutAppareilFragment();
                    FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                    fragmentTransaction1.replace(R.id.placeholder, fragmentAjout);
                    fragmentTransaction1.commit();
                    return true;
                case R.id.navigation_list:

                    ListAppareilFragment fragment = new ListAppareilFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.placeholder, fragment);
                    fragmentTransaction.commit();
                    return true;

                case R.id.navigation_visualiser:
                    Intent intent = new Intent(HostActivity.this, MapsActivity.class);
                    startActivity(intent);

                    return true;


            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        fragmentManager = getSupportFragmentManager();
        AjoutAppareilFragment fragmentAjout = new AjoutAppareilFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeholder, fragmentAjout);
        fragmentTransaction.commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
}




