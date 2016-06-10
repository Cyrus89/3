package com.tomcio.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WynikActivity extends AppCompatActivity {

    TextView imieWynik;
    TextView stopWynik;
    TextView lewoWynik;
    TextView prawoWynik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);

        imieWynik = (TextView) findViewById(R.id.imie_wynik);
        stopWynik = (TextView) findViewById(R.id.stop_wynik);
        lewoWynik = (TextView) findViewById(R.id.lewo_wynik);
        prawoWynik = (TextView) findViewById(R.id.prawo_wynik);


        Bundle bundle = getIntent().getExtras();
        String imie_string = bundle.getString("imie_key");
        int stop_int = bundle.getInt("stop_key",0);
        int lewo_int = bundle.getInt("lewo_key",0);
        int prawo_int = bundle.getInt("prawo_key",0);

        imieWynik.setText("Sówka " + imie_string + " została obkręcona:");
        lewoWynik.setText("w lewo " + lewo_int + " razy");
        prawoWynik.setText("w prawo " + prawo_int + " razy");
        stopWynik.setText("i zatrzymana " + stop_int + " razy");


    }
}
