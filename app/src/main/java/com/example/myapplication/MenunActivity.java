package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenunActivity extends AppCompatActivity {

    private Button btnFreePost, btnPhExercise, btnCalendar, btnInbody, btnExercise, btnDiet, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menun);

        btnFreePost = findViewById(R.id.btn_freepost);
        btnPhExercise = findViewById(R.id.btn_phexercise);
        btnCalendar = findViewById(R.id.btn_calendar);
        btnInbody = findViewById(R.id.btn_inbody);
        btnExercise = findViewById(R.id.btn_exercise);
        btnDiet = findViewById(R.id.btn_diet);
        backButton = findViewById(R.id.back_button_menu);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenunActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnFreePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open FreePostActivity
                Intent intent = new Intent(MenunActivity.this, CommunityActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnPhExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open PhExerciseActivity
                Intent intent = new Intent(MenunActivity.this, GPTActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open CalendarActivity
                Intent intent = new Intent(MenunActivity.this, CalendarActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnInbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open InbodyActivity
                Intent intent = new Intent(MenunActivity.this, MyActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open ExerciseActivity
                Intent intent = new Intent(MenunActivity.this, WorkoutListActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open DietActivity
                Intent intent = new Intent(MenunActivity.this, DietListActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
