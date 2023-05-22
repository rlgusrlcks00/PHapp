package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    private Button backButton;
    private UserDAO mUserDao;
    private Button logoutButton;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private TextView heightTextView;
    private TextView weightTextView;
    private TextView ageTextView;
    private TextView sexTextView;
    private TextView muscleTextView;
    private TextView fatTextView;
    private EditText muscleInput;
    private EditText fatInput;
    private String loggedInUserEmail;
    private Button inbodyUpdateButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);



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
                heightTextView = findViewById(R.id.height);
                weightTextView = findViewById(R.id.weight);
                ageTextView = findViewById(R.id.age);
                sexTextView = findViewById(R.id.sex);
                muscleTextView = findViewById(R.id.muscle_view);
                fatTextView = findViewById(R.id.fat_view);
                muscleInput = findViewById(R.id.muscle);
                fatInput = findViewById(R.id.fat_weight);

                if (user != null) {
                    heightTextView.setText("키: " + user.getHeight());
                    weightTextView.setText("몸무게: " + user.getWeight());
                    ageTextView.setText("나이: " + user.getAge());
                    sexTextView.setText("성별: " + user.getGender());
                    muscleTextView.setText("근육량: "+ user.getMuscle_mass());
                    fatTextView.setText("체지방률: "+ user.getBody_fat_percentage());
                } else {
                    Toast.makeText(MyActivity.this, "사용자 정보를 로드하는 데 문제가 발생했습니다.", Toast.LENGTH_SHORT).show();


                }
            });
        });

        muscleInput = findViewById(R.id.muscle);
        fatInput = findViewById(R.id.fat_weight);
        inbodyUpdateButton = findViewById(R.id.inbody_update_button);

        inbodyUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMuscleMass = muscleInput.getText().toString();
                String newBodyFat = fatInput.getText().toString();

                AppExecutors.getInstance().diskIO().execute(() -> {
                    int updateCount = mUserDao.updateInbodyInfo(loggedInUserEmail, newMuscleMass, newBodyFat);
                    runOnUiThread(() -> {
                        if (updateCount > 0) {
                            Toast.makeText(MyActivity.this, "인바디 정보가 성공적으로 업데이트 되었습니다.", Toast.LENGTH_SHORT).show();
                            // 업데이트된 정보로 텍스트 뷰 갱신
                            muscleTextView.setText("근육량: " + newMuscleMass);
                            fatTextView.setText("체지방률: " + newBodyFat);
                            recreate();
                        } else {
                            Log.e("InbodyUpdate", "Inbody 정보 업데이트에 실패하였습니다.");
                            Toast.makeText(MyActivity.this, "인바디 정보 업데이트에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            }
        });



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

