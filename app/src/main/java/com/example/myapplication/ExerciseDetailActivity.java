package com.example.myapplication;
// ExerciseDetailActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseDetailActivity extends AppCompatActivity {
    private TextView textViewExerciseType;
    private TextView textViewExerciseTime;
    private TextView textViewExerciseDate;
    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        backButton = findViewById(R.id.back_button_exdetail);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인화면으로 되돌아가는 기능
                finish();
            }
        });
        // UI 컴포넌트 초기화
        textViewExerciseType = findViewById(R.id.textViewExerciseType);
        textViewExerciseTime = findViewById(R.id.textViewExerciseTime);
        textViewExerciseDate = findViewById(R.id.textViewExerciseDate);

        // Intent에서 운동 기록 정보 가져오기
        Intent intent = getIntent();
        String exerciseType = intent.getStringExtra("exerciseType");
        int exerciseTime = intent.getIntExtra("exerciseTime", 0);
        String exerciseDate = intent.getStringExtra("exerciseDate");

        // 운동 기록 정보 표시
        textViewExerciseType.setText(exerciseType);
        textViewExerciseTime.setText("운동 시간: " + exerciseTime + " 분");
        textViewExerciseDate.setText(exerciseDate);
    }
}
