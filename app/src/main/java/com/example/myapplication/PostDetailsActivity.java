package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class PostDetailsActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView contentTextView;
    private TextView authorTextView;
    private TextView timestampTextView;

    private Button backButton;
    private PHDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        titleTextView = findViewById(R.id.post_title);
        contentTextView = findViewById(R.id.post_content);
        authorTextView = findViewById(R.id.post_author);
        timestampTextView = findViewById(R.id.post_timestamp);

        backButton = findViewById(R.id.back_button_post_detail);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메인화면으로 되돌아가는 기능
                finish();
            }
        });

        // 데이터베이스 초기화
        db = Room.databaseBuilder(getApplicationContext(), PHDatabase.class, "PH_db")
                .fallbackToDestructiveMigration()
                .build();

        // 전달받은 게시글 ID 가져오기
        int postId = getIntent().getIntExtra("postId", 0);

        // 게시글 조회 및 표시
        loadPostDetails(postId);
    }

    private void loadPostDetails(int postId) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            Post post = db.PostDao().getPostById(postId);

            if (post != null) {
                User user = db.UserDao().getUserById(post.getUserID());

                runOnUiThread(() -> {
                    // 게시글 내용 표시
                    titleTextView.setText("제목: "+post.getTitle());
                    contentTextView.setText("내용: "+post.getContent());

                    if (user != null) {
                        authorTextView.setText("작성자: "+user.getEmail());
                    } else {
                        authorTextView.setText("Unknown User");
                    }

                    // timestamp to human-readable date
                    long timestamp = Long.parseLong(post.getDate());
                    Date date = new Date(timestamp);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String formattedDate = formatter.format(date);

                    timestampTextView.setText("작성날짜: "+formattedDate);
                });
            }
        });
    }
}
