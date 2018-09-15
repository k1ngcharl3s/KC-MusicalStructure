package com.example.k1ngcharl3s.musicalstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView tupac = findViewById(R.id.tupac);
        tupac.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent ajrIntent = new Intent(MainActivity.this, TupacActivity.class);
                startActivity(ajrIntent);
            }
        });

        ImageView christinaaguilera = findViewById(R.id.christinaaguilera);
        christinaaguilera.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent christinaaguileraIntent = new Intent(MainActivity.this, ChristinaAguileraActivity.class);
                startActivity(christinaaguileraIntent);
            }
        });

        ImageView evanescene = findViewById(R.id.evanescence);
        evanescene.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent evansceneIntent = new Intent(MainActivity.this, EvanescenceActivity.class);
                startActivity(evansceneIntent);
            }
        });

        ImageView isleybrothers = findViewById(R.id.isleybrothers);
        isleybrothers.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent isleybrothersIntent = new Intent(MainActivity.this, IsleyBrothersActivity.class);
                startActivity(isleybrothersIntent);
            }
        });

    }

}
