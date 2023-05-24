package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {
    private Button backButton;
    private Button WriteButton;
    private int totalPost;
    // 게시글 페이지 번호
    private int currentPage = 1;
    private int totalPages = 1;

    // 게시글 페이지 사이즈
    private static final int PAGE_SIZE = 5;

    // 게시글 제목 TextView 배열
    private TextView[] memoTextViews;

    private PHDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        backButton = findViewById(R.id.back_button_community);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메인화면으로 되돌아가는 기능
                Intent intent = new Intent(CommunityActivity.this, MainActivity.class);
                startActivity(intent);
                // 현재 액티비티 종료
                finish();
            }
        });

        WriteButton = findViewById(R.id.write_button);
        WriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        // 페이지 번호 및 게시글 TextView 배열 초기화
        currentPage = 1;
        memoTextViews = new TextView[]{
                findViewById(R.id.memo1),
                findViewById(R.id.memo2),
                findViewById(R.id.memo3),
                findViewById(R.id.memo4),
                findViewById(R.id.memo5)
        };

        // 데이터베이스 초기화
        db = Room.databaseBuilder(getApplicationContext(), PHDatabase.class, "PH_db")
                .fallbackToDestructiveMigration()
                .build();

        // 페이지 로드
        loadPage(currentPage);

        // 다음 페이지로 이동
        Button nextPageButton = findViewById(R.id.next_page_button);
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage < totalPages) {
                    currentPage++;
                    loadPage(currentPage);
                }
            }
        });

        // 이전 페이지로 이동
        Button prevPageButton = findViewById(R.id.prev_page_button);
        prevPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage > 1) {
                    currentPage--;
                    loadPage(currentPage);
                }
            }
        });
    }

    // 페이지 로드
    private void loadPage(int page) {
        // 게시글 페이지를 DB에서 가져와서 표시하는 로직 구현
        // 예시로 각 페이지에 표시될 게시글 제목을 설정해주세요
        // 페이지 제목 가져오기
        int pageSize = PAGE_SIZE;
        int offset = (page - 1) * PAGE_SIZE;

        AppExecutors.getInstance().diskIO().execute(() -> {
            List<String> pageTitles = db.PostDao().getPageTitles(pageSize, offset);

            // 페이지 수 계산
            totalPost = db.PostDao().getTotalPosts(); // 전체 게시글 수를 가져와야 함
            totalPages = (int) Math.ceil((double) totalPost / PAGE_SIZE);

            runOnUiThread(() -> {
                // 페이지 제목 표시
                for (int i = 0; i < memoTextViews.length; i++) {
                    if (i < pageTitles.size()) {
                        String title = pageTitles.get(i);
                        memoTextViews[i].setText(title);
                        memoTextViews[i].setVisibility(View.VISIBLE);

                        // 게시글 제목을 클릭할 때 상세 정보 화면으로 이동
                        int position = i + (currentPage - 1) * PAGE_SIZE;
                        memoTextViews[i].setOnClickListener(v -> showPostDetails(position));
                    } else {
                        memoTextViews[i].setVisibility(View.GONE);
                    }
                }

                // 페이지 이동 버튼 활성화/비활성화
                Button nextPageButton = findViewById(R.id.next_page_button);
                Button prevPageButton = findViewById(R.id.prev_page_button);

                if (currentPage < totalPages) {
                    nextPageButton.setEnabled(true);
                } else {
                    nextPageButton.setEnabled(false);
                }

                if (currentPage > 1) {
                    prevPageButton.setEnabled(true);
                } else {
                    prevPageButton.setEnabled(false);
                }
            });
        });
    }

    // 게시글 상세 정보 화면으로 이동
    private void showPostDetails(int position) {
        Intent intent = new Intent(CommunityActivity.this, PostDetailsActivity.class);
        intent.putExtra("postId", position + 1); // 게시글 ID 전달 (예: 1부터 시작하는 게시글 ID)
        startActivity(intent);
    }
}
