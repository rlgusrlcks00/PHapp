package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.example.myapplication.myCalendar;
import com.example.myapplication.CalendarDAO;
import com.example.myapplication.UserDAO;
import java.util.Calendar;
import java.util.List;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private TextView textViewDate;
    private EditText editTextExerciseType;
    private EditText editTextExerciseTime;
    private Button buttonSave, buttonView, buttonModify, buttonDelete;
    private CalendarDAO calendarDao;
    private String selectedDate;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private int userId;
    private Button backButton;
    private boolean hasExerciseRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        // SharedPreferences에서 저장된 이메일 정보 가져오기
        SharedPreferences sharedPreferences = getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
        String loggedInUserEmail = sharedPreferences.getString("UserEmail", "");

        backButton = findViewById(R.id.back_button_calendar);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메인화면으로 되돌아가는 기능
                finish();
            }
        });

        // UI 컴포넌트 초기화
        calendarView = findViewById(R.id.calendar_View);
        textViewDate = findViewById(R.id.textViewDate);
        editTextExerciseType = findViewById(R.id.editTextExerciseType);
        editTextExerciseTime = findViewById(R.id.editTextExerciseTime);
        buttonSave = findViewById(R.id.buttonSave);
        buttonView = findViewById(R.id.detail_calendar);
        buttonModify = findViewById(R.id.update_calendar);
        buttonDelete = findViewById(R.id.delete_calendar);

        // Room DB 초기화
        PHDatabase database = Room.databaseBuilder(getApplicationContext(), PHDatabase.class, "PH_db")
                .fallbackToDestructiveMigration() // 스키마 버전 변경 가능
                .allowMainThreadQueries() // 메인 스레드에서 DB에 IO를 가능하게 함
                .build();
        calendarDao = database.CalenderDao();
        UserDAO userDao = database.UserDao();

        userId = userDao.findUserIdByEmail(loggedInUserEmail);

        // 현재 날짜로 초기 선택
        Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
        selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
        textViewDate.setText(selectedDate);

        // 캘린더 날짜 선택 리스너 등록
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                textViewDate.setText(selectedDate);

                // 선택된 날짜의 운동 기록 확인
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        List<myCalendar> exerciseList = calendarDao.getExerciseByDateAndUserId(selectedDate, userId);
                        hasExerciseRecord = !exerciseList.isEmpty();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int dayBgColor = hasExerciseRecord ? R.drawable.calendar_day_background : R.drawable.calendar_day_background_recorded;
                                calendarView.setBackgroundResource(dayBgColor);
                                updateButtonStatus();
                            }
                        });
                    }
                });
            }
        });

        // 버튼 클릭 리스너 등록
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExercise();
            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewExercise();
            }
        });

        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyExercise();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteExercise();
            }
        });

        updateButtonStatus();
    }

    private void saveExercise() {
        String exerciseType = editTextExerciseType.getText().toString();
        int exerciseTime = Integer.parseInt(editTextExerciseTime.getText().toString());

        // 새로운 Calendar 객체 생성
        myCalendar calendar = new myCalendar();
        calendar.setDate(selectedDate);
        calendar.setExercise_type(exerciseType);
        calendar.setExercise_time(exerciseTime);
        calendar.setUser_ID(userId);  // 로그인된 사용자의 아이디를 설정함

        // Room DB에 데이터 저장
        new Thread(() -> {
            calendarDao.setInsertCalendar(calendar);
        }).start();

        // EditText 초기화
        editTextExerciseType.setText("");
        editTextExerciseTime.setText("");

        // 운동 기록이 추가되면 수정 및 삭제 버튼 활성화
        hasExerciseRecord = true;
        updateButtonStatus();
    }

    private void viewExercise() {
        // 데이터베이스에서 해당 날짜의 운동 기록을 가져와서 보여줌
        new Thread(() -> {
            List<myCalendar> exerciseList = calendarDao.getExerciseByDateAndUserId(selectedDate, userId);
            runOnUiThread(() -> {
                if (exerciseList.isEmpty()) {
                    Toast.makeText(CalendarActivity.this, "운동 기록이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 첫 번째 운동 기록만 상세히 보여줌
                    myCalendar exercise = exerciseList.get(0);

                    // 상세 화면으로 전환하기 위한 Intent 생성
                    Intent intent = new Intent(CalendarActivity.this, ExerciseDetailActivity.class);
                    intent.putExtra("exerciseType", exercise.getExercise_type());
                    intent.putExtra("exerciseTime", exercise.getExercise_time());
                    intent.putExtra("exerciseDate", exercise.getDate());
                    startActivity(intent);
                }
            });

        }).start();
    }

    private void modifyExercise() {
        // TODO: 수정 기능 구현
        if (hasExerciseRecord) {
            // 수정할 운동 기록이 있을 경우에만 실행
            new Thread(() -> {
                List<myCalendar> exerciseList = calendarDao.getExerciseByDateAndUserId(selectedDate, userId);
                if (!exerciseList.isEmpty()) {
                    // 첫 번째 운동 기록만 가져옴
                    myCalendar exercise = exerciseList.get(0);

                    // 수정 화면으로 전환하기 위한 Intent 생성
                    Intent intent = new Intent(CalendarActivity.this, ExerciseModifyActivity.class);
                    intent.putExtra("exerciseId", exercise.getData_ID());
                    startActivity(intent);
                }
            }).start();
        }
    }

    private void deleteExercise() {
        // TODO: 삭제 기능 구현
        if (hasExerciseRecord) {
            // 삭제할 운동 기록이 있을 경우에만 실행
            new Thread(() -> {
                List<myCalendar> exerciseList = calendarDao.getExerciseByDateAndUserId(selectedDate, userId);
                if (!exerciseList.isEmpty()) {
                    // 첫 번째 운동 기록만 가져옴
                    myCalendar exercise = exerciseList.get(0);

                    // 운동 기록 삭제
                    calendarDao.setDeleteCalendar(exercise);

                    runOnUiThread(() -> {
                        Toast.makeText(CalendarActivity.this, "운동 기록이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        hasExerciseRecord = false;
                        updateButtonStatus();
                    });
                }
            }).start();
        }
    }

    private void updateButtonStatus() {
        // 수정 및 삭제 버튼 활성화 여부 설정
        buttonModify.setEnabled(hasExerciseRecord);
        buttonDelete.setEnabled(hasExerciseRecord);
    }
}
