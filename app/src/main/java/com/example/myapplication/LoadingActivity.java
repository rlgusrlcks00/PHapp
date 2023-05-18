package com.example.myapplication;

import com.example.myapplication.LoginActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;



public class LoadingActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 2000; // 로딩 화면을 보여줄 시간 (2초)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // 일정 시간동안 로딩 화면을 보여준 후에 메인 화면으로 전환
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LoadingActivity.this, LoginActivity.class); // 메인화면으로 전환할 액티비티 설정
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
