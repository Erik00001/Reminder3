package com.example.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "schedule"; // Имя базы данных
    private static final int DB_VERSION = 2;
    static Cursor cursor,cursor2;

    ScheduleDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE SCHEDULE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "MUSCLE TEXT, "
                + "EXERCISE TEXT,"
                + "COUNT TEXT,"
                + "CALENDAR TEXT,"
                + "NOTID INTEGER,"
                + "REQID INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


    /*public void insertSchedule(SQLiteDatabase db,String muscle,
                               String exercise) {
        scheduleValues = new ContentValues();
        scheduleValues.put("MUSCLE", muscle);
        scheduleValues.put("EXERCISE", exercise);
        db.insert("SCHEDULE",null,scheduleValues);

    }*/

    public static ArrayList<Schedule> getAll(SQLiteDatabase db){
        ArrayList<Schedule> scheduleList=new ArrayList<Schedule>();
        Schedule schedule;

        cursor=db.query("SCHEDULE",new String[]{"_id","MUSCLE","EXERCISE","COUNT","CALENDAR","NOTID","REQID"},
                null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                schedule=new Schedule(cursor.getString
                        (cursor.getColumnIndex("MUSCLE")),cursor.getString(cursor.getColumnIndex("EXERCISE")),
                        cursor.getString(cursor.getColumnIndex("COUNT")),cursor.getString(cursor.getColumnIndex("CALENDAR")));
                        //cursor.getInt(cursor.getColumnIndex("REQUEST_CODE")));
                scheduleList.add(schedule);
            }while (cursor.moveToNext());
        }
        return scheduleList;
    }


    public  static ArrayList<Schedule> getSpecific(SQLiteDatabase db,String muscle){
        ArrayList<Schedule> scheduleListSpecific=new ArrayList<>();
        Schedule scheduleSpecific;

        cursor2=db.query("SCHEDULE",new String[]{"_id","MUSCLE","EXERCISE"},
                "MUSCLE=?",new String[]{muscle},null,null,null);
        if(cursor2.moveToFirst()){

                do{
                    scheduleSpecific=new Schedule(cursor2.getString
                            (cursor2.getColumnIndex("MUSCLE")),cursor2.getString(cursor2.getColumnIndex("EXERCISE")),
                            cursor2.getString(cursor2.getColumnIndex("COUNT")),cursor2.getString(cursor2.getColumnIndex("CALENDAR")));
                    scheduleListSpecific.add(scheduleSpecific);
                }while (cursor2.moveToNext());
            }
        return scheduleListSpecific;
    }

   /* public static void delete(SQLiteDatabase db){
        db.execSQL("DELETE  FROM SCHEDULE");
    }*/
}
