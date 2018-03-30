package com.example.mac.timimngrecord;



import android.app.Dialog; // Este es el recurso que estamos llamando con la declaración de Dialog
import android.content.DialogInterface;
import android.os.SystemClock;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat; // Atentos a este import
import java.util.ArrayList;

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

    // Vamos a crear unas variables para darle vida al método recién creado , ShowListTime ,.
    private ArrayList<Time> listTime;
    private TimeAdapter adapter;
    private RecyclerView recyclerView;

    // Declaramos una variable para el layout alertdialog_new
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       btnStart = findViewById(R.id.btnStart);
       btnFinalize = findViewById(R.id.btnFinalize);
       btnPause = findViewById(R.id.btnPause);

       // inicialiamos recyclerView aquí
        recyclerView = findViewById(R.id.rvTime);

       chronometer = findViewById(R.id.chronometer2);

       // Asignamos el valor al Dialog que hemos declarado arriba.
        dialog = new Dialog(MainActivity.this);
        // y ahora componemos ese Dialog
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // y por fin llamamos al layout
        dialog.setContentView(R.layout.alertdialog_new);


       // Dentro del OnCreate llamamos a nuestro método recién creado
        ShowListTime();


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

    // Y por fin creamos el método para mostrar el listado
    public void ShowListTime() {

        //LLamamos al recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        // Enviamos false  aquí abajo porque
        // queremos leer
        listTime = new Time(getApplicationContext(), false).getAllTime();

        // Miramos con este if si existe algún registro en la base de datos.
        // Si existe alguno entonces lo agrega a nuestro recyclerView
        if (listTime != null) {

            adapter = new TimeAdapter(listTime, new TimeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Time time) {

                }
            });

            recyclerView.setAdapter(adapter);

        }
    }

    // Creamos un nuevo método para llamar al layout alertdialog_new
    public void newTime(){
        // Aquí dentro mandamos a llamar a los componentes
        txtTime = dialog.findViewById(R.id.txtTime);
        txtdescrption = dialog.findViewById(R.id.txtDescription);
        txtDistance = dialog.findViewById(R.id.txtDistance);
        txtDate = dialog.findViewById(R.id.txtDates);



    }


}
