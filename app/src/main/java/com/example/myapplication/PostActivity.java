package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.AppExecutors;
import com.example.myapplication.PHDatabase;
import com.example.myapplication.User;

public class PostActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentEditText;
    private Button submitButton;
    private PostDAO postDAO;
    private UserDAO mUserDao;
    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        backButton = findViewById(R.id.back_button_post);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인화면으로 되돌아가는 기능
                finish();
            }
        });

        // SharedPreferences에서 저장된 이메일 정보 가져오기
        SharedPreferences sharedPreferences = getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
        String loggedInUserEmail = sharedPreferences.getString("UserEmail", "");

        AppExecutors.getInstance().diskIO().execute(() -> {
            // 데이터베이스 초기화
            PHDatabase db = Room.databaseBuilder(getApplicationContext(), PHDatabase.class, "PH_db")
                    .fallbackToDestructiveMigration()
                    .build();
            postDAO = db.PostDao();
            mUserDao = db.UserDao();

            // 사용자 정보 가져오기
            User user = mUserDao.getUserByEmail(loggedInUserEmail);

            // UI 요소 초기화
            titleEditText = findViewById(R.id.title_edit_text);
            contentEditText = findViewById(R.id.content_edit_text);
            submitButton = findViewById(R.id.submit_button);

            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = titleEditText.getText().toString().trim();
                    String content = contentEditText.getText().toString().trim();

                    // 제목과 내용이 비어있는지 확인
                    if (title.isEmpty() || content.isEmpty()) {
                        Toast.makeText(PostActivity.this, "제목과 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        // 게시글 정보를 데이터베이스에 저장
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                // 사용자 정보 가져오기
                                int userId = user.getUser_id();

                                // 현재 시간 가져오기
                                long currentTime = System.currentTimeMillis();

                                // 게시글 정보 생성
                                Post post = new Post(0, title, content, String.valueOf(currentTime), userId);

                                // 데이터베이스에 게시글 저장
                                postDAO.setInsertPost(post);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(PostActivity.this, "게시글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                                        // 게시글 등록 완료 후 액티비티 종료
                                        Intent intent = new Intent(PostActivity.this, CommunityActivity.class);
                                        startActivity(intent);
                                        // 현재 액티비티 종료
                                        finish();
                                    }
                                });
                            }
                        });
                    }
                }
            });
        });
    }
}
