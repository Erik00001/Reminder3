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

import java.util.List;

public class RecyclerViewAdapterGeneral extends RecyclerView.Adapter<RecyclerViewAdapterGeneral.ViewHolder> {

    private List<Schedule> schedules;
    private LayoutInflater layoutInflater;
    CreateScheduleActivity createScheduleActivity=new CreateScheduleActivity();
  //  private SQLiteDatabase database;
   // private ScheduleDatabaseHelper scheduleDatabaseHelper=new ScheduleDatabaseHelper();
   // private HomeFragment homeFragment=new HomeFragment();



    RecyclerViewAdapterGeneral(Context context,List<Schedule> schedules){
        this.layoutInflater=LayoutInflater.from(context);
        this.schedules=schedules;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.row_layout,parent,false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder,int position){
        holder.tvMuscle.setText(schedules.get(position).getMuscle());
        holder.tvExercise.setText(schedules.get(position).getExercise());
        holder.tvCount.setText(schedules.get(position).getCount());
        holder.tvDate.setText(schedules.get(position).getCalendar());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                  int REQ_ID=createScheduleActivity.delete(HomeFragment.db,position);
                    Context context=view.getContext();
                    Intent intent2=new Intent(context,Broadcast.class);
                    PendingIntent pendingIntent=PendingIntent.getBroadcast(context,REQ_ID,intent2,0);
                    AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    HomeFragment.list.remove(position);
                    HomeFragment.recyclerView.removeViewAt(position);
                    HomeFragment.recyclerViewAdapterGeneral.notifyDataSetChanged();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMuscle,tvExercise,tvCount,tvDate;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkBox);
            tvExercise=(TextView)itemView.findViewById(R.id.tvArmsExercise);
            tvMuscle=(TextView)itemView.findViewById(R.id.tvSpecificMuscle);
            tvCount=(TextView)itemView.findViewById(R.id.tvCount);
            tvDate=(TextView)itemView.findViewById(R.id.tvDate);


        }
    }
}
