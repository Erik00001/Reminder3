package com.example.reminder;

import java.sql.Date;

public class Schedule {
    private String muscle;
    private String exercise;
    private String count;
    private String calendar;

    public Schedule(String muscle, String exercise, String count,String calendar){
        this.muscle=muscle;
        this.exercise=exercise;
        this.calendar=calendar;
        this.count=count;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }


}
