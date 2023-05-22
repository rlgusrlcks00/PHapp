package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;
import androidx.room.Room;
import android.widget.TextView;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.annotation.NonNull;
import com.example.myapplication.AppExecutors;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;





public class SettingsActivity extends AppCompatActivity {



    private Button backButton;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private UserDAO mUserDao;
    private User loggedInUser;
    private Button logoutButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesHelper = new SharedPreferencesHelper(getApplicationContext());

        setContentView(R.layout.activity_settings);

        backButton = findViewById(R.id.back_button_settings);
        Button logoutButton = findViewById(R.id.logout_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인화면으로 되돌아가는 기능
                finish();
            }
        });

        sharedPreferencesHelper = new SharedPreferencesHelper(this);


        // SharedPreferences에서 저장된 이메일 정보 가져오기
        SharedPreferences sharedPreferences = getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
        String loggedInUserEmail = sharedPreferences.getString("UserEmail", "");


        AppExecutors.getInstance().diskIO().execute(() -> {
            // 백그라운드 스레드에서 데이터베이스 쿼리 수행
/*            PHDatabase db = PHDatabase.getInstance(getApplicationContext());*/
            PHDatabase db= Room.databaseBuilder(getApplicationContext(), PHDatabase.class, "PH_db")
                    .fallbackToDestructiveMigration() //스키마 버전 변경 가능
                    .allowMainThreadQueries() //메인 스레드에서 DB에 IO를 가능하게 함
                    .build();
            mUserDao = db.UserDao();
            User user = mUserDao.getUserByEmail(loggedInUserEmail);
            runOnUiThread(() -> {
                // 가져온 사용자 정보를 UI에 바인딩합니다.
                TextView nameTextView = findViewById(R.id.name_text_view);
                TextView emailTextView = findViewById(R.id.email_text_view);
                TextView genderTextView = findViewById(R.id.gender_text_view);
                TextView ageTextView = findViewById(R.id.age_text_view);
                TextView phoneNumberTextView = findViewById(R.id.phone_number_text_view);
                TextView addressTextView = findViewById(R.id.address_text_view);

                if (user != null) {
                    nameTextView.setText("이름: " + user.getName());
                    emailTextView.setText("이메일: " + user.getEmail());
                    genderTextView.setText("성별: " + user.getGender());
                    ageTextView.setText("나이: " + user.getAge());
                    phoneNumberTextView.setText("핸드폰 번호: " + user.getPhoneNumber());
                    addressTextView.setText("주소: "+user.getAddress());
                } else {
                    Toast.makeText(SettingsActivity.this, "사용자 정보를 로드하는 데 문제가 발생했습니다.", Toast.LENGTH_SHORT).show();


                }
            });
        });



        logoutButton.setOnClickListener(new View.OnClickListener() {//로그아웃
            @Override
            public void onClick(View v) {
                sharedPreferencesHelper.setLoggedIn(false); // 로그인 상태 변경
                Intent loginActivityIntent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(loginActivityIntent);
                finish();
            }
        });
    }
}