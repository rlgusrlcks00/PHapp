package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.PHDatabase;
import com.example.myapplication.myCalendar;

public class ExerciseModifyActivity extends AppCompatActivity {

    private EditText editTextExerciseType;
    private EditText editTextExerciseTime;
    private Button buttonSave, backButton;

    private PHDatabase database;
    private CalendarDAO calendarDao;

    private int exerciseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_modify);

        editTextExerciseType = findViewById(R.id.editTextExerciseType);
        editTextExerciseTime = findViewById(R.id.editTextExerciseTime);
        buttonSave = findViewById(R.id.buttonSave);
        backButton = findViewById(R.id.back_button_exercisemodify);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인화면으로 되돌아가는 기능
                finish();
            }
        });

        database = Room.databaseBuilder(getApplicationContext(), PHDatabase.class, "PH_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        calendarDao = database.CalenderDao();

        exerciseId = getIntent().getIntExtra("exerciseId", -1);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyExercise();
            }
        });

        loadExerciseDetails();
    }

    private void loadExerciseDetails() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                myCalendar exercise = calendarDao.getExerciseById(exerciseId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (exercise != null) {
                            editTextExerciseType.setText(exercise.getExercise_type());
                            editTextExerciseTime.setText(String.valueOf(exercise.getExercise_time()));
                        }
                    }
                });
            }
        });
    }

    private void modifyExercise() {
        String exerciseType = editTextExerciseType.getText().toString();
        int exerciseTime = Integer.parseInt(editTextExerciseTime.getText().toString());

        if (exerciseType.isEmpty() || exerciseTime <= 0) {
            Toast.makeText(this, "운동 유형과 시간을 올바르게 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                myCalendar exercise = calendarDao.getExerciseById(exerciseId);
                if (exercise != null) {
                    exercise.setExercise_type(exerciseType);
                    exercise.setExercise_time(exerciseTime);
                    calendarDao.setUpdateCalendar(exercise);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ExerciseModifyActivity.this, "운동 기록이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }
        });
    }
}
