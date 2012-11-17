package com.example;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DebitedMoney extends MoneyMonitorDatabaseHelper{

    public static String TABLE_NAME = "DEBITED_MONEY";
    public DebitedMoney(Context context) {
        super(context);
    }
}
