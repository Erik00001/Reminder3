package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView tvMuscleDetail,tvExerciseDetail,tvCountDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        tvMuscleDetail=(TextView)findViewById(R.id.tvMuscleDetail);
        tvExerciseDetail=(TextView)findViewById(R.id.tvExerciseDetail);
        tvCountDetail=(TextView)findViewById(R.id.tvCountDetail);
        tvMuscleDetail.setText("It is time to train "+getIntent().getStringExtra("muscle")+".");
        tvExerciseDetail.setText("Description:"+getIntent().getStringExtra("exercise")+".");
        tvCountDetail.setText("You have to do this "+getIntent().getStringExtra("count")+" times.");
    }
}