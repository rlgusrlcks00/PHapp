package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.util.Log;

import java.util.List;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;

public class MyActivity extends AppCompatActivity {

    private Button backButton;
    private UserDAO mUserDao;
    private Button logoutButton;
    private SharedPreferencesHelper sharedPreferencesHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        logoutButton = findViewById(R.id.logout_button);//로그아웃
/*        UserDatabase database= Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "PH_db")
                .fallbackToDestructiveMigration() //스키마 버전 변경 가능
                .allowMainThreadQueries() //메인 스레드에서 DB에 IO를 가능하게 함
                .build();

        mUserDao=database.UserDao(); //인터페이스 객체 할당

        List<User> userList= mUserDao.getUserAll();
        //조회
        for (int i = 0; i < userList.size(); i++) {
            Log.d("Test", userList.get(i).getName() + "\n"
            + userList.get(i).getAge()+"\n"
            + userList.get(i).getPhoneNumber()+"\n");

        }*/


/*
        //데이터 삽입
        User user= new User();
        user.setName("kichan");
        user.setAge("25");
        user.setPhoneNumber("01048559646");

        //데이터 수정
        User user2= new User();
        user2.setId(1);
        user2.setName("Parkkichan");
        user2.setAge("20");
        user2.setPhoneNumber("112");
        mUserDao.setUpdateUser(user2);


        mUserDao.setInsertUser(user);
*/
/*
        User user3=new User();
        user3.setId(2);
        mUserDao.setDeleteUser(user3);
        User user4=new User();
        user3.setId(3);
        mUserDao.setDeleteUser(user4);
        User user5=new User();
        user3.setId(4);
        mUserDao.setDeleteUser(user5);
*/


        backButton = findViewById(R.id.back_button_my);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인화면으로 되돌아가는 기능
                finish();
            }
        });



    }


}

