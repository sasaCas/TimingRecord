package com.example.mac.timimngrecord.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mac on 21/3/18.
 */

public class DataBaseManager {
    private DbHelper helper;
    private SQLiteDatabase sqLiteDatabase;

    public DataBaseManager(Context context){

        this.helper = DbHelper.getInstance(context);

    }


}
