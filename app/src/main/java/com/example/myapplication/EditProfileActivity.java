package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.room.Room;
import android.content.Intent;

public class EditProfileActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText heightEditText;
    private EditText weightEditText;
    private EditText addressEditText;
    private EditText phoneNumberEditText;
    private Button saveButton;
    private UserDAO mUserDao;
    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        backButton = findViewById(R.id.back_button_editprofile);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인화면으로 되돌아가는 기능
                finish();
            }
        });

        nameEditText = findViewById(R.id.name_edit_text);
        heightEditText = findViewById(R.id.height_edit_text);
        weightEditText = findViewById(R.id.weight_edit_text);
        addressEditText = findViewById(R.id.address_edit_text);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        saveButton = findViewById(R.id.save_button);

        PHDatabase db = Room.databaseBuilder(getApplicationContext(), PHDatabase.class, "PH_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        mUserDao = db.UserDao();

        // 이전 화면에서 전달받은 사용자 이메일 정보를 가져옴
        String userEmail = getIntent().getStringExtra("userEmail");

        User user = mUserDao.getUserByEmail(userEmail);

        if (user != null) {
            // 사용자 정보를 EditText에 표시
            nameEditText.setText(user.getName());
            heightEditText.setText(user.getHeight());
            weightEditText.setText(user.getWeight());
            addressEditText.setText(user.getAddress());
            phoneNumberEditText.setText(user.getPhoneNumber());
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에서 새로운 정보를 가져옴
                String username = nameEditText.getText().toString();
                String height = heightEditText.getText().toString();
                String weight = weightEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();

                if (validateInput(username, height, weight, address, phoneNumber)) {
                    // 사용자 정보 업데이트
                    user.setName(username);
                    user.setHeight(height);
                    user.setWeight(weight);
                    user.setAddress(address);
                    user.setPhoneNumber(phoneNumber);

                    // 사용자 정보 업데이트 후 DB에 저장
                    mUserDao.setUpdateUser(user);

                    Toast.makeText(EditProfileActivity.this, "프로필이 업데이트되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent settingsIntent = new Intent(EditProfileActivity.this, SettingsActivity.class);
                    // EditProfileActivity를 종료하고 SettingsActivity로 이동
                    startActivity(settingsIntent);
                    finish();
                }
            }
        });
    }

    private boolean validateInput(String username, String height, String weight, String address, String phoneNumber) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(height) || TextUtils.isEmpty(weight) ||
                TextUtils.isEmpty(address) || TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "모든 입력란을 채워주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
