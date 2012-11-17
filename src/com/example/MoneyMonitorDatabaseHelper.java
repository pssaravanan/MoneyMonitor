package com.example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.apache.commons.lang3.StringUtils;


public class MoneyMonitorDatabaseHelper extends SQLiteOpenHelper{
    private static String DATABASE_NAME = "MoneyMonitor";
    private static int VERSION = 1;
    public static String[] TABLES = {"CREDITED_MONEY", "DEBITED_MONEY", "USED_MONEY"};
    public static String[][] COLUMNS = {
            {"amount", "credited_date"},
            {"id", "amount", "date"},
            {"id", "amount", "date", "description", "debited_money_id"}
     };

    public static String[][] COLUMN_PROPERTY = {
            {"INTEGER", "DATE"},
            {"INTEGER PRIMARY KEY AUTOINCREMENT", "INTEGER", "DATE"},
            {"INTEGER PRIMARY KEY AUTOINCREMENT", "INTEGER", "DATE", "TEXT", "INTEGER", "FOREIGN KEY(debited_money_id) REFERENCES DEBITED_MONEY(id)"}
    };
    public MoneyMonitorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       for(int i = 0; i < TABLES.length; i++){
           String CREATE_STATEMENT = "CREATE TABLE %s";
           String columnDefinition[] = new String[COLUMN_PROPERTY[i].length];
           for(int j = 0 ; j < COLUMNS[i].length; j++){ columnDefinition[j] = COLUMNS[i][j] + " "+ COLUMN_PROPERTY[i][j];}
           columnDefinition[COLUMN_PROPERTY[i].length - 1] =  COLUMNS[i].length < COLUMN_PROPERTY[i].length? COLUMN_PROPERTY[i][COLUMN_PROPERTY[i].length -1] : null;
           CREATE_STATEMENT += "(%s)";
           CREATE_STATEMENT = String.format(CREATE_STATEMENT, new String[]{ TABLES[i], StringUtils.join(columnDefinition, ",")});
           sqLiteDatabase.execSQL(CREATE_STATEMENT);
       }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
