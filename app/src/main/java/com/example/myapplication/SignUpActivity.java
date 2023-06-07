package com.example.myapplication;
import androidx.room.Room;

import com.example.myapplication.User;
import com.example.myapplication.UserDAO;
import com.example.myapplication.PHDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;
import android.os.Bundle;
import com.example.myapplication.R;
import android.view.View;
import android.text.TextUtils;
import android.widget.Toast;
import android.util.Patterns;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.RadioButton;



public class SignUpActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText passwordConfirmInput;
    private RadioGroup genderGroup;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageInput;
    private EditText phoneNumberInput;
    private EditText weightInput;
    private EditText heightInput;
    private EditText addressInput;
    private Button signUpButton;
    private UserDAO mUserDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

/*        PHDatabase database = PHDatabase.getInstance(getApplicationContext());*/
        PHDatabase database= Room.databaseBuilder(getApplicationContext(), PHDatabase.class, "PH_db")
                .fallbackToDestructiveMigration() //스키마 버전 변경 가능
                .allowMainThreadQueries() //메인 스레드에서 DB에 IO를 가능하게 함
                .build();
        mUserDao=database.UserDao(); //인터페이스 객체 할당


        usernameInput = findViewById(R.id.username_input);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        passwordConfirmInput = findViewById(R.id.password_confirm_input);
        genderGroup = findViewById(R.id.gender_radio_group);

        maleButton = findViewById(R.id.male_radio_button);
        femaleButton = findViewById(R.id.female_radio_button);
        ageInput = findViewById(R.id.age_input);
        phoneNumberInput = findViewById(R.id.phone_number_input);
        weightInput = findViewById(R.id.weignt_input);
        heightInput = findViewById(R.id.height_input);
        addressInput = findViewById(R.id.address_input);
        signUpButton = findViewById(R.id.sign_up_button);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String passwordConfirm = passwordConfirmInput.getText().toString();
                String age = ageInput.getText().toString();
                String phoneNumber = phoneNumberInput.getText().toString();
                String weight = weightInput.getText().toString();
                String height = heightInput.getText().toString();
                String address = addressInput.getText().toString();

                User user = new User();

                if (!TextUtils.isEmpty(weight) && !TextUtils.isEmpty(height)) {
                    double weightValue = Double.parseDouble(weight);
                    double heightValue = Double.parseDouble(height) / 100.0; // cm to m
                    double bmi = weightValue / (heightValue * heightValue);
                    user.setBMI(String.valueOf(bmi));
                }

                if (validateInput(username, email, password, passwordConfirm)) {

                    String gender = "";
                    int selectedId = genderGroup.getCheckedRadioButtonId();
                    if (selectedId == maleButton.getId()) {
                        gender = "남성";
                    } else if (selectedId == femaleButton.getId()) {
                        gender = "여성";
                    }



                    user.setName(username);
                    user.setEmail(email);
                    user.setPW(password);
                    user.setGender(gender);
                    user.setAge(age);
                    user.setPhoneNumber(phoneNumber);
                    user.setWeight(weight);
                    user.setHeight(height);
                    user.setAddress(address);

                    mUserDao.setInsertUser(user);

                    Toast.makeText(SignUpActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                    // 로그인 화면으로 돌아가기
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);

                    // SignUpActivity 종료 (선택 사항)
                    finish();

                }
            }
        });
    }

    private boolean validateInput(String username, String email, String password, String passwordConfirm) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordConfirm)) {
            Toast.makeText(this, "모든 입력란을 채워주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "올바른 이메일 주소를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mUserDao.findByEmail(email) != null) {
            Toast.makeText(this, "이미 사용 중인 이메일입니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(passwordConfirm)) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
