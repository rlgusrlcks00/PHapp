package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    //버튼변수선언
    private ImageButton menuButton,alarmButton, settingsButton;
    private ImageView myButton,calendarButton;
    private Button analyButton, communityButton,  dietButton, workoutButton;
    private UserDAO mUserDao;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //버튼 종속성 설정
        menuButton = findViewById(R.id.menu_button);
        settingsButton = findViewById(R.id.settings_button);
        myButton=findViewById(R.id.button_my);
        communityButton=findViewById(R.id.communityBtn);
        analyButton=findViewById(R.id.button_analysis);
        calendarButton=findViewById(R.id.calendar_Button);
        dietButton = findViewById(R.id.button_diet);
        workoutButton = findViewById(R.id.button_workout);


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메뉴 버튼 기능 구현
                Intent intent = new Intent(MainActivity.this, MenunActivity.class);
                startActivity(intent);
            }
        });


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 설정 버튼 기능 구현
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                //설정화면 연결
            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MY버튼 기능 구현
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                startActivity(intent);
                //MY화면 연결
            }
        });



        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);

            }
        });

        communityButton.setOnClickListener(new View.OnClickListener() {
            //community 버튼 기능
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

        analyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, GPTActivity.class);
                startActivity(intent);
            }
        });

        dietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, DietListActivity.class);
                startActivity(intent);
            }
        });

        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WorkoutListActivity.class);
                startActivity(intent);
            }
        });



    }
}