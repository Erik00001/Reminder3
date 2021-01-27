package com.example.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterSpecific extends RecyclerView.Adapter<RecyclerViewAdapterSpecific.ViewHolder> {
    private List<Schedule> schedules;
    private LayoutInflater layoutInflater;
    CreateScheduleActivity createScheduleActivity=new CreateScheduleActivity();
    //  private SQLiteDatabase database;
    // private ScheduleDatabaseHelper scheduleDatabaseHelper=new ScheduleDatabaseHelper();
    // private HomeFragment homeFragment=new HomeFragment();



    RecyclerViewAdapterSpecific(Context context, List<Schedule> schedules){
        this.layoutInflater=LayoutInflater.from(context);
        this.schedules=schedules;
    }
    @NonNull
    @Override
    public RecyclerViewAdapterSpecific.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.row_layout2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull RecyclerViewAdapterSpecific.ViewHolder holder, int position){
        holder.tvSpecificMuscle.setText(schedules.get(position).getMuscle());
        holder.tvExercise.setText(schedules.get(position).getExercise());
        holder.tvCount.setText(schedules.get(position).getCount());
        holder.tvDate.setText(schedules.get(position).getCalendar());

    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSpecificMuscle,tvExercise,tvCount,tvDate;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkBox);
            tvExercise=(TextView)itemView.findViewById(R.id.tvArmsExercise);
            tvSpecificMuscle=(TextView)itemView.findViewById(R.id.tvSpecificMuscle);
            tvCount=(TextView)itemView.findViewById(R.id.tvCount);
            tvDate=(TextView)itemView.findViewById(R.id.tvDate);


        }
    }
}

