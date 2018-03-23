package com.example.mac.timimngrecord;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

import java.text.SimpleDateFormat; // Atentos a este import

public class MainActivity extends AppCompatActivity {

    private EditText txtTime;
    private EditText txtDate;
    private EditText txtdescrption;
    private EditText txtDistance;

    private Button btnStart;
    private Button btnPause;
    private Button btnFinalize;

    private Chronometer chronometer;

    private boolean pauseClick;
    private boolean startClick;

    // Cuidado al importar esto, porque por defecto importa otro tipo
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
