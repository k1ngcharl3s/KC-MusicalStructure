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

        // Find the View that shows the song category
        ImageView tupac = findViewById(R.id.tupac);
        // Set a click listener on that View
        tupac.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent ajrIntent = new Intent(MainActivity.this, TupacActivity.class);
                // Start the new activity
                startActivity(ajrIntent);
            }
        });
        // Find the View that shows the song category
        ImageView christinaaguilera = findViewById(R.id.christinaaguilera);
        // Set a click listener on that View
        christinaaguilera.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent christinaaguileraIntent = new Intent(MainActivity.this, ChristinaAguileraActivity.class);
                // Start the new activity
                startActivity(christinaaguileraIntent);
            }
        });
        // Find the View that shows the song category
        ImageView evanescene = findViewById(R.id.evanescence);
        // Set a click listener on that View
        evanescene.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent evansceneIntent = new Intent(MainActivity.this, EvanescenceActivity.class);
                // Start the new activity
                startActivity(evansceneIntent);
            }
        });
        // Find the View that shows the song category
        ImageView isleybrothers = findViewById(R.id.isleybrothers);
        // Set a click listener on that View
        isleybrothers.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent isleybrothersIntent = new Intent(MainActivity.this, IsleyBrothersActivity.class);
                // Start the new activity
                startActivity(isleybrothersIntent);
            }
        });

    }

}
