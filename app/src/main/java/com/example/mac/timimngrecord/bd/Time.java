package com.example.mac.timimngrecord.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mac on 22/3/18.
 */

public class Time {

    private int id;
    private String time;
    private String date;
    private String distance;
    private String description;

    private static DataBaseManager dataBaseManager;
    private static SQLiteDatabase database;

    public Time(){

        id = 0;
        time = "";
        date = "";
        distance = "";
        description = "";

    }

    public Time(int id, String time, String date, String distance, String description) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.distance = distance;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time(Context context, boolean write){

        // Agregamos el contexto a la base de datos para poder usarla
        dataBaseManager = new DataBaseManager(context);
        if(write) // Si es true es que quiero escribir
            database = dataBaseManager.openWriteableDB();
        else // Si es false entonces lo que quiero es sólo leer
            database = dataBaseManager.openReableDB();

    }

    // Añadimos un método para leer todos los registros que hay en la
    // base de datos.
    public ArrayList<Time> getAllTime(){
        try {


        } catch (Exception ex){
            Log.e("Time/getAllTime", ex.toString());
            return null;
        }
    }



}









