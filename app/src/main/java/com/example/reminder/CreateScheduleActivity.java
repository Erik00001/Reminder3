package com.example.reminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateScheduleActivity extends AppCompatActivity {

   String [] muscles=new String[]{"Chest","Back","Arms","Legs","Shoulders"};
    public static int REQUEST_CODE;
    public  int NOTIFICATION_ID;
    CalendarView calendarView;
    TimePicker timePicker;
    Calendar calendar;
    AutoCompleteTextView autoCompleteTextView;
    EditText etExercise;
    EditText etNumber;
    ScheduleDatabaseHelper scheduleDatabaseHelper;
    static SQLiteDatabase database;
  //  Intent intent;
    //PendingIntent pendingIntent;
    static ArrayList<Schedule> schedules=new ArrayList<>();
    private Cursor cursor;



   //  static ArrayList<AlarmManager> alarmManagers=new ArrayList<>();
   //  static ArrayList<Intent> intents=new ArrayList<>();
     //static ArrayList<PendingIntent> pendingIntents=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        calendarView=(CalendarView)findViewById(R.id.calView);
        timePicker=(TimePicker)findViewById(R.id.timePicker);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        etExercise=(EditText)findViewById(R.id.etExercise);
        etNumber=(EditText) findViewById(R.id.etNumber);
        scheduleDatabaseHelper=new ScheduleDatabaseHelper(this);
        database=scheduleDatabaseHelper.getWritableDatabase();

       ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,muscles);
        AutoCompleteTextView autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
      /*  calendar1=Calendar.getInstance();
        calendar1.setTimeInMillis(System.currentTimeMillis());*/

        calendar=Calendar.getInstance();

     calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
       //  @Override
         public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
         }
     });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                calendar.set(Calendar.HOUR_OF_DAY,i);
                calendar.set(Calendar.MINUTE,i1);
            }
        });
    }

    public void insert(SQLiteDatabase db){
        Date date=calendar.getTime();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String strDate=dateFormat.format(date);
        ContentValues contentValues=new ContentValues();
        contentValues.put("MUSCLE",autoCompleteTextView.getText().toString());
        contentValues.put("EXERCISE",etExercise.getText().toString());
        contentValues.put("COUNT",etNumber.getText().toString());
        contentValues.put("CALENDAR",strDate);
       // contentValues.put("NOTID",NOTIFICATION_ID);
        //contentValues.put("REQID",REQUEST_CODE);
       // contentValues.put("REQUEST_CODE",REQUEST_CODE);
        db.insert(" SCHEDULE",null,contentValues);
        Toast.makeText(this,"Training is scheduled!   "+strDate,Toast.LENGTH_SHORT).show();
    }

    public int delete( SQLiteDatabase db,int position){
      //  alarmManagers.get(position).cancel(pendingIntents.get(position));
        //alarmManagers.remove(position);
        //pendingIntents.remove(position);
       TextView tvMuscele2=HomeFragment.recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvSpecificMuscle);
       TextView tvExercise2=HomeFragment.recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvArmsExercise);
       TextView tvCount2=HomeFragment.recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvCount);
       TextView tvDate2=HomeFragment.recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvDate);
       Log.e("ERRRROOR",tvMuscele2.getText().toString());

       Cursor cursor=db.query("SCHEDULE",new String[]{"_id"},"MUSCLE=? AND EXERCISE=? AND COUNT=? AND CALENDAR=?",
               new String[]{tvMuscele2.getText().toString(),tvExercise2.getText().toString(),
                       tvCount2.getText().toString(),tvDate2.getText().toString()},null,null,null);

        int REQ_ID=0;
        if(cursor.moveToFirst()) {
           REQ_ID = cursor.getInt(cursor.getColumnIndex("_id"));
       }


       db.delete("SCHEDULE","MUSCLE=? AND EXERCISE=? AND COUNT=? AND CALENDAR=?",
                new String[]{tvMuscele2.getText().toString(),tvExercise2.getText().toString(),
                tvCount2.getText().toString(),tvDate2.getText().toString()});
        //schedules.remove(position);*/
       /* HomeFragment homeFragment=new HomeFragment();
      homeFragment.refresh();*/


      // HomeFragment.recyclerViewAdapterGeneral.notifyItemRangeChanged(position, CreateScheduleActivity.alarmManagers.size());
        return REQ_ID;
    }


    public void save(View view){
        insert(database);
        cursor=database.query(true,"SCHEDULE",new String[]{"_id"},"MUSCLE=?",new String[]{autoCompleteTextView.getText().toString()}
        ,null,null,null,null);
        if(cursor.moveToFirst())
            NOTIFICATION_ID=cursor.getInt(cursor.getColumnIndex("_id"));
            REQUEST_CODE=cursor.getInt(cursor.getColumnIndex("_id"));
        Intent intent=new Intent(CreateScheduleActivity.this,Broadcast.class);
        intent.putExtra("muscle",autoCompleteTextView.getText().toString());
        intent.putExtra("exercise",etExercise.getText().toString());
        intent.putExtra("count",etNumber.getText().toString());
        intent.putExtra("not_id",NOTIFICATION_ID);
       PendingIntent pendingIntent=PendingIntent.getBroadcast(CreateScheduleActivity.this,REQUEST_CODE,intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setAlarmClock(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }
        // alarmManagers.add(alarmManager);
        //intents.add(intent);
        //pendingIntents.add(pendingIntent);


        //scheduleDatabaseHelper.insertSchedule(database,autoCompleteTextView.getText().toString(),etExercise.getText().toString());

       Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
       // cursor=database.query("SCHEDULE",new String[]{"_id","MUSCLE"},null,null,null,null,null);
       /* if(cursor.moveToFirst()){
       do {
                Log.e("DONNNNEE",cursor.getString(cursor.getColumnIndex("MUSCLE"))+"/n");
            }  while (cursor.moveToNext());
        }*/


      //  REQUEST_CODE++;
       // NOTIFICATION_ID++;

        autoCompleteTextView.setText(null);
        etExercise.setText(null);
        etNumber.setText(null);
      // Toast.makeText(CreateScheduleActivity.this,calendar.getTimeInMillis()-calendar1.getTimeInMillis()+"",Toast.LENGTH_LONG).show();
       // Toast.makeText(this,calendar.getTimeInMillis()+"",Toast.LENGTH_LONG).show();
      //  Toast.makeText(this,miliSeconds+"",Toast.LENGTH_LONG).show();
       // miliSeconds=0;
        //calendarView.getDateTextAppearance()+
       // Toast.makeText(this,calendar.get(Calendar.MINUTE),Toast.LENGTH_LONG).show();
    }


}