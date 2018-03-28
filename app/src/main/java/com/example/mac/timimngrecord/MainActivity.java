package com.example.mac.timimngrecord;



import android.content.DialogInterface;
import android.os.SystemClock;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

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

    private long timePause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       btnStart = findViewById(R.id.btnStart);
       btnFinalize = findViewById(R.id.btnFinalize);
       btnPause = findViewById(R.id.btnPause);

       chronometer = findViewById(R.id.chronometer2);
       btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chronometer.setBase(SystemClock.elapsedRealtime() + timePause);
                chronometer.start();
                pauseClick = false;
                startClick = true;

            }
        });

       btnPause.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(!pauseClick){
                   timePause = chronometer.getBase() - SystemClock.elapsedRealtime();
                   chronometer.stop();
                   pauseClick = true;
               }

           }
       });

       btnFinalize.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (startClick){
                   if(pauseClick){

                       new AlertDialog.Builder(MainActivity.this).setTitle(getString(R.string.title))
                               .setPositiveButton(getString(R.string.save), new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {

                                   }
                               }).setNegativeButton(getString(R.string.restart), new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {

                               chronometer.setBase(SystemClock.elapsedRealtime());
                               timePause = 0;
                               startClick = false;
                               dialogInterface.dismiss();

                           }
                       }).show();


                   } else {
                       Toast.makeText(getApplicationContext(), getString(R.string.pause_clicked),
                               Toast.LENGTH_SHORT).show();

                   }


               } else {
                   Toast.makeText(getApplicationContext(), getString(R.string.start_clicked), 
                           Toast.LENGTH_LONG).show();

               }

           }
       });

    }
}
