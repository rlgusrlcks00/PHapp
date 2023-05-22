package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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
    private Button myButton,analyButton, communityButton;
    private UserDAO mUserDao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        UserDatabase database= Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "PH_db")
                .fallbackToDestructiveMigration() //스키마 버전 변경 가능
                .allowMainThreadQueries() //메인 스레드에서 DB에 IO를 가능하게 함
                .build();
        mUserDao=database.UserDao(); //인터페이스 객체 할당
        //데이터 삽입
        User user= new User();
        user.setName("kichan");
        user.setAge("25");
        user.setPhoneNumber("01048559646");

        mUserDao.setInsertUser(user);
*/

        //버튼 종속성 설정
        menuButton = findViewById(R.id.menu_button);
        settingsButton = findViewById(R.id.settings_button);
        myButton=findViewById(R.id.button_my);
        communityButton=findViewById(R.id.communityBtn);
        analyButton=findViewById(R.id.button_analysis);


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메뉴 버튼 기능 구현
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





    }
}