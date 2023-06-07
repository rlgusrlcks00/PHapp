package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DietListActivity extends AppCompatActivity {

    private LinearLayout dietListLayout;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_list);

        dietListLayout = findViewById(R.id.diet_list_layout);
        backButton = findViewById(R.id.back_button_dietlist);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인화면으로 되돌아가는 기능
                finish();
            }
        });

        // 식단 리스트 설정
        String[][] dietList = {
                {"아침식사", "오트밀과 과일", "오트밀을 끓여서 그 위에 선명한 과일 조각과 견과류를 올려줍니다. 꿀이나 자연 유래의 감미료로 간단히 달게 해줄 수 있습니다."},
                {"아침식사", "토마토와 아보카도 샌드위치", "신선한 토마토와 아보카도 슬라이스, 신선한 채소, 그리고 올리브 오일과 식초로 만든 소스를 사용하여 샌드위치를 만듭니다."},
                {"아침식사", "그린 스무디와 닭가슴살 샐러드", "시금치, 바나나, 그리고 우유를 블렌더에 넣어서 스무디를 만들고, 영양가 있는 닭가슴살과 신선한 채소로 샐러드를 만듭니다."},
                {"아침식사", "훈제 연어와 계란 샌드위치", "훈제 연어 슬라이스와 계란 슬라이스, 신선한 채소, 마요네즈로 만든 소스를 사용하여 샌드위치를 만듭니다."},
                {"아침식사", "고구마 팬케이크와 그릴드 체다 치즈", "고구마를 갈아서 팬케이크 형태로 만들고, 그릴에서 치즈를 녹여 케이크 위에 올려줍니다."},
                {"간식", "그릭 요거트 파르페", "그릭 요거트에 신선한 과일과 견과류, 그리고 꿀을 층층이 쌓아 파르페를 만듭니다."},
                {"간식", "삶은 계란과 아몬드", "삶은 계란과 아몬드를 함께 간식용 용기에 담아 먹습니다."},
                {"간식", "허니 머스타드 치킨 워핑", "그릴 또는 오븐에서 구운 치킨 조각을 토마토, 상추, 양파와 함께 토마토 소스와 허니 머스타드 드레싱으로 워핑으로 싸먹습니다."},
                {"간식", "과일과 견과류 믹스", "다양한 종류의 과일과 견과류를 섞어서 간식용 용기에 담아 먹습니다."},
                {"간식", "터키 살라미 롤업", "터키 살라미, 신선한 채소, 크림 치즈를 롤러로 말아서 롤업으로 만듭니다."},
                {"점심식사", "퀴노아 샐러드와 그릴 치킨", "퀴노아에 그릴 치킨 조각, 신선한 채소, 드레싱을 섞어 샐러드를 만듭니다."},
                {"점심식사", "새우 스키레치니와 채소 볶음", "허브와 양념으로 마리네이드한 새우를 그릴이나 팬에서 구워주고, 신선한 채소를 볶아서 함께 섭취합니다."},
                {"점심식사", "소고기 타코와 아보카도 살사", "구운 소고기 슬라이스를 타코에 싸고, 아보카도와 토마토를 포함한 살사를 올려줍니다."},
                {"점심식사", "스파이시 허니 글레이즈드 연어와 콩 샐러드", "스파이시 허니 글레이즈로 마리네이드한 연어를 그릴이나 오븐에서 조리한 후, 콩과 신선한 채소로 샐러드를 만듭니다."},
                {"점심식사", "채소 스티어프라이와 키노아", "다양한 채소와 키노아를 볶아서 스티어프라이로 만들어 줍니다."},
                {"간식", "피스타치오와 드라이 프룻", "피스타치오와 드라이 프룻을 함께 간식용 용기에 담아 먹습니다."},
                {"간식", "허니 머스타드 새우 스케워", "허니 머스타드 소스로 마리네이드한 새우를 꼬치에 꽂아 그릴에서 구워줍니다."},
                {"간식", "그릴드 에그플랜트 롤", "그릴드 에그플랜트 슬라이스와 신선한 채소, 드레싱을 사용하여 롤을 만듭니다."},
                {"간식", "그릭 요거트와 너트 버터 딥", "그릭 요거트와 너트 버터를 섞어 간식용 용기에 담아 과일이나 채소를 딥하여 먹습니다."},
                {"간식", "닭가슴살 로메인 레타스 롤", "구운 닭가슴살 슬라이스와 신선한 채소를 로메인 레타스로 싸서 롤을 만듭니다."},
                {"저녁식사", "그릴 치킨과 새우 스키야키", "그릴에서 치킨과 새우를 구워주고, 테리야끼 소스로 맛을 낸 후 채소와 함께 섭취합니다."},
                {"저녁식사", "허니 머스타드 포크 삼각김밥", "허니 머스타드로 마리네이드한 포크를 구워서 김밥에 싸서 삼각김밥으로 만듭니다."},
                {"저녁식사", "매운 토마토 치킨 샐러드", "매운 토마토 소스로 마리네이드한 치킨을 그릴이나 오븐에서 구워주고, 채소와 함께 샐러드로 섭취합니다."},
                {"저녁식사", "훈제 연어와 미니 샐러드 피자", "훈제 연어 슬라이스와 다양한 채소, 치즈를 얹은 미니 피자를 만들어 줍니다."},
                {"저녁식사", "그릴드 포크 카우보이 스테이크", "그릴에서 구운 포크 스테이크에 카우보이 스파이스를 뿌려 맛을 낸 후 섭취합니다."},
                {"후식", "그린 애플 스무디", "녹색 애플, 시금치, 바나나, 우유를 블렌더에 넣어 스무디를 만들어 줍니다."},
                {"후식", "코코넛 우유 차", "코코넛 우유를 데워서 차로 즐깁니다."},
                {"후식", "다크 초콜릿과 아몬드", "다크 초콜릿과 아몬드를 함께 섭취합니다."},
                {"후식", "그린 그레이프 프루트 스무디", "그린 그레이프 프루트, 바나나, 우유를 블렌더에 넣어 스무디를 만들어 줍니다."},
                {"후식", "오렌지와 유자 체리 푸딩", "오렌지와 유자 체리로 만든 푸딩을 섭취합니다."}
        };

        // 식단 리스트 동적으로 생성
        for (String[] diet : dietList) {
            createDietView(diet[0], diet[1], diet[2]);
        }
    }

    private void createDietView(String category, String name, String description) {
        View dietView = LayoutInflater.from(this).inflate(R.layout.item_diet, null);
        TextView categoryTextView = dietView.findViewById(R.id.category_text_view);
        TextView nameTextView = dietView.findViewById(R.id.name_text_view);
        TextView descriptionTextView = dietView.findViewById(R.id.description_text_view);

        categoryTextView.setText(category);
        nameTextView.setText(name);
        descriptionTextView.setText(description);

        dietListLayout.addView(dietView);
    }
}
