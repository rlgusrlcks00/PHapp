package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    //버튼변수선언
    private ImageButton menuButton, alarmButton, settingsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 종속성 설정
        menuButton = findViewById(R.id.menu_button);
        alarmButton = findViewById(R.id.alarm_button);
        settingsButton = findViewById(R.id.settings_button);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메뉴 버튼 기능 구현
            }
        });

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 알람 버튼 기능 구현
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


    }
}