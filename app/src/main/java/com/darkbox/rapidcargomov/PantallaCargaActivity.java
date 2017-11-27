package com.darkbox.rapidcargomov;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PantallaCargaActivity extends AppCompatActivity {

    private static int splashInterval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i= new Intent(PantallaCargaActivity.this, TabPrincipalActivity.class);
                startActivity(i);

                this.finish();
            }

            private void finish(){

            }
        }, splashInterval);
    }
}
