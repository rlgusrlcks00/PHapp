package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkoutListActivity extends AppCompatActivity {

    private LinearLayout workoutListLayout;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        workoutListLayout = findViewById(R.id.workout_list_layout);
        backButton = findViewById(R.id.back_button_workoutlist);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인화면으로 되돌아가는 기능
                finish();
            }
        });

        // 운동 리스트 설정
        String[][] workoutList = {
                {"랫풀다운 (Lat Pulldown)",
                        "자세: 풀다운 머신에 앉아 가슴을 펴고 어깨 너비로 손잡이를 잡습니다. 등을 약간 뒤로 젖힌 상태에서 팔을 뻗어 손잡이를 가슴 쪽으로 당겨서 아랫등에 닿을 때까지 내려갑니다.",
                        "주요 근육: 넓적다리근, 대흉근, 이두근"},
                {"케이블로우 (Cable Row)",
                        "자세: 케이블 머신에 앉아 발을 딱지 모양으로 놓고 손잡이를 잡습니다. 등을 펴고 어깨를 내린 상태에서 팔을 뻗어 손잡이를 가슴 쪽으로 당겨서 어깨 뒷부분을 수축시킵니다.",
                        "주요 근육: 넓적다리근, 중후면근, 이두근"},
                {"덤벨숄더프레스 (Dumbbell Shoulder Press)",
                        "자세: 등받이 의자에 앉아 손에 덤벨을 들고 어깨 높이로 올린 다음 팔을 뻗어 덤벨을 위로 밀어 올립니다. 팔을 최대한 펴고 상단에서 잠시 유지한 후 천천히 내려줍니다.",
                        "주요 근육: 이두근, 삼두근, 견갑근, 삼각근"},
                {"덤벨컬 (Dumbbell Curl)",
                        "자세: 손에 덤벨을 들고 어깨 너비로 서서 손목을 펴고 팔을 내리면서 덤벨을 몸 옆으로 가져갑니다. 팔꿈치를 고정하고 상체만을 사용하여 덤벨을 끌어올립니다. 팔을 최대한 펴지 않고 상단에서 잠시 유지한 후 천천히 내려줍니다.",
                        "주요 근육: 이두근"},
                {"케이블푸쉬다운 (Cable Tricep Pushdown)",
                        "자세: 케이블 머신에 서서 손잡이를 잡고 팔을 펴고 손잡이를 아래로 당겨 팔을 굽힙니다. 상단에서 잠시 유지한 후 천천히 원위치로 돌아옵니다.",
                        "주요 근육: 삼두근"},
                {"레그프레스 (Leg Press)",
                        "자세: 레그프레스 머신에 앉아 발을 어깨 너비로 벌리고 발판에 발을 올려놓습니다. 무게를 밀어 발을 펴고 다시 굽히는 동작을 반복합니다.",
                        "주요 근육: 대퇴사두근, 대둔근, 대퇴이두근, 종아리근"},
                {"레그익스텐션/컬 (Leg Extension/Leg Curl)",
                        "자세: 레그익스텐션 머신에 앉아 다리를 굽힌 상태에서 다리를 펴주는 동작을 반복합니다. 레그컬 머신에 앉아 다리를 펴고 다시 굽히는 동작을 반복합니다.",
                        "주요 근육: 대퇴사두근, 대퇴이두근, 종아리근"},
                {"폼롤러 근막이완 (Foam Roller Myofascial Release)",
                        "자세: 폼롤러를 사용하여 몸의 근육을 압박하고 이완시키는 동작을 합니다. 특히 근육의 힘줄 부분을 중심으로 압박합니다. 통증이 있는 부위에 집중해서 사용합니다.",
                        ""},
                {"정적인 스트레칭 (Static Stretching)",
                        "자세: 근육을 늘리기 위해 특정 자세를 유지하는 동작을 합니다. 각 근육의 피복을 늘리는 것에 초점을 두고 스트레칭합니다. 15~30초 동안 유지합니다.",
                        ""},
                {"풀업 (Pull-up)",
                        "자세: 철봉에 매달려 손바닥이 자신을 향하도록 손을 벌리고 철봉을 잡습니다. 팔의 힘으로 철봉을 당겨 목 뒤로 올라갑니다. 팔을 최대한 굽히고 다시 원위치로 돌아옵니다.",
                        "주요 근육: 넓적다리근, 대흉근, 이두근"},
                {"벤트오버 바벨로우 (Bent-Over Barbell Row)",
                        "자세: 발을 어깨 너비로 벌리고 상체를 앞으로 숙인 상태에서 양손으로 바벨을 잡습니다. 상체를 고정한 채로팔을 굽혀 바벨을 가슴 쪽으로 당겨 등근육을 수축시킵니다. 팔을 최대한 펴지 않고 상단에서 잠시 유지한 후 천천히 내려줍니다.",
                        "주요 근육: 넓적다리근, 중후면근, 이두근"},
                {"루마니안 데드리프트 (Romanian Deadlift)",
                        "자세: 어깨 너비로 발을 벌리고 양손으로 바벨을 잡습니다. 상체를 앞으로 숙인 채로 골반을 뒤로 밀어 엉덩이를 뒤로 빼면서 상체를 세워줍니다. 다리는 약간 굽혀두고 골반과 하체 근육을 이용하여 몸을 일으킵니다.",
                        "주요 근육: 대퇴사두근, 대둔근, 종아리근"},
                {"밀리터리프레스 (Military Press)",
                        "자세: 어깨 너비로 발을 벌리고 양손에 바벨을 잡습니다. 팔을 굽혀 어깨 높이로 바벨을 들어올린 후 팔을 최대한 펴고 상단에서 잠시 유지한 후 천천히 내려줍니다.",
                        "주요 근육: 이두근, 삼두근, 견갑근, 삼각근"},
                {"사이드 레터럴 레이즈 (Side Lateral Raise)",
                        "자세: 양손에 덤벨을 들고 어깨 너비로 서서 팔을 옆으로 들어 옆구리 쪽으로 드는 동작을 합니다. 팔을 최대한 펴고 상단에서 잠시 유지한 후 천천히 내려줍니다.",
                        "주요 근육: 삼각근, 견갑근"},
                {"시티드 로우 (Seated Row)",
                        "자세: 시티드 로우 머신에 앉아 발을 딱지 모양으로 놓고 손잡이를 잡습니다. 등을 펴고 어깨를 내린 상태에서 팔을 뻗어 손잡이를 가슴 쪽으로 당겨서 어깨 뒷부분을 수축시킵니다. 팔을 최대한 펴지 않고 상단에서 잠시 유지한 후 천천히 원위치로 돌아옵니다.",
                        "주요 근육: 중후면근, 이두근"},
                {"플랭크 (Plank)",
                        "자세: 엎드려 손바닥과 발끝으로 몸을 지탱합니다. 상체와 하체가 일직선이 되도록 몸을 곧게 유지합니다. 복부와 전신 근력을 강화하는 운동입니다.",
                        "주요 근육: 복근, 등근육, 대퇴사두근"},
                {"런지 (Lunge)",
                        "자세: 양발을 어깨 너비로 벌리고 한 발은 앞으로 한 발은 뒤로 내딛습니다. 무릎이 90도로 굽혀지도록 하며 몸을 내립니다. 다리를 번갈아 가며 반복합니다.",
                        "주요 근육: 대퇴사두근, 대둔근, 종아리근"},
                {"레그컬 (Leg Curl)",
                        "자세: 레그컬 머신에 앉아 다리를 펴고 다시 굽히는 동작을 반복합니다. 종아리 근육을 강화하는 운동입니다.",
                        "주요 근육: 종아리근"},
                {"크런치 (Crunch)",
                        "자세: 등받이 의자에 앉아 발을 땅에 붙이고 상체를 앞으로 숙입니다. 복부에 힘을 주어 상체를 들어올린 후 천천히 내려줍니다.",
                        "주요 근육: 복근"},
                {"플라이 (Fly)",
                        "자세: 플라이 머신에 앉아 등받이를 기대고 팔을 펴서 가슴 쪽으로 모으는 동작을 합니다. 가슴 근육을 강화하는 운동입니다.",
                        "주요 근육: 대흉근, 이두근"},
                {"벤치프레스 (Bench Press)",
                        "자세: 벤치에 누워 어깨 너비로 양손에 바벨을 잡습니다. 팔꿈치를 고정하고 가슴 쪽으로 바벨을 내리고 다시 밀어올립니다.",
                        "주요 근육: 대흉근, 삼두근, 이두근"},
                {"딥스 (Dips)",
                        "자세: 딥스 머신에 손을 놓고 팔을 펴서 몸을 들어올립니다. 상체를 내리고 다시 들어올리는 동작을 합니다.",
                        "주요 근육: 삼두근, 가슴근육"},
                {"바이셉 컬 (Bicep Curl)",
                        "자세: 손에 바벨이나 덤벨을 들고 팔을 펴서 팔꿈치를 고정한 채로 상체를 일으키는 동작을 합니다.",
                        "주요 근육: 이두근"},
                {"풀업 (Pull-up)",
                        "자세: 철봉에 매달려 손바닥이 자신을 향하도록 손을 벌리고 철봉을 잡습니다. 팔의 힘으로 철봉을 당겨 목 뒤로 올라갑니다. 팔을 최대한 굽히고 다시 원위치로 돌아옵니다.",
                        "주요 근육: 넓적다리근, 대흉근, 이두근"},
                {"오버헤드 프레스 (Overhead Press)",
                        "자세: 어깨 너비로 발을 벌리고 양손에 바벨을 잡습니다. 팔을 굽혀 어깨 높이로 바벨을 들어올린 후 팔을 최대한 펴고 상단에서 잠시 유지한 후 천천히 내려줍니다.",
                        "주요 근육: 삼각근, 견갑근"},
                {"사이드 레터럴 레이즈 (Side Lateral Raise)",
                        "자세: 양손에 덤벨을 들고 어깨 너비로 서서 팔을 옆으로 들어 옆구리 쪽으로 드는 동작을 합니다. 팔을 최대한 펴고 상단에서 잠시 유지한 후 천천히 내려줍니다.",
                        "주요 근육: 삼각근, 견갑근"},
                {"디클라인 벤치프레스 (Decline Bench Press)",
                        "자세: 디클라인 벤치에 누워 어깨 너비로 양손에 바벨을 잡습니다. 팔꿈치를 고정하고 가슴 쪽으로 바벨을 내리고 다시 밀어올립니다.",
                        "주요 근육: 대흉근, 삼두근, 이두근"},
                {"스쿼트 (Squat)",
                        "자세: 어깨 너비로 발을 벌리고 양손에 바벨을 잡습니다. 엉덩이를 뒤로 내밀며 무릎을 굽혀 내려가다가 허벅지 수평이 되도록 합니다. 상체를 일으켜 원위치로 돌아옵니다.",
                        "주요 근육: 대퇴사두근, 대둔근, 종아리근"},
                {"레그프레스 (Leg Press)",
                        "자세: 레그프레스 머신에 앉아 어깨 너비로 발을 벌리고 발판에 발을 올립니다. 발을 힘껏 밀어내며 다리를 펴고 다시 구부립니다.",
                        "주요 근육: 대퇴사두근, 대둔근, 종아리근"},
                {"레그익스텐션 (Leg Extension)",
                        "자세: 레그 익스텐션 머신에 앉아 다리를 펴고 다시 구부리는 동작을 반복합니다. 대퇴사두근을 강화하는 운동입니다.",
                        "주요 근육: 대퇴사두근"},
                {"행잉 레그레이즈 (Hanging Leg Raise)",
                        "자세: 철봉에 매달려 손바닥이 자신을 향하도록 손을 벌리고 철봉을 잡습니다. 다리를 굽히고 복부에 힘을 주어 다리를 들어올린 후 천천히 내려줍니다.",
                        "주요 근육: 복근, 허벅지 근육"},
                {"덤벨 프론트 레이즈 (Dumbbell Front Raise)",
                        "자세: 양손에 덤벨을 들고 어깨 너비로 서서 팔을 앞으로 들어 옆구리 쪽으로 드는 동작을 합니다. 팔을 최대한 펴고 상단에서 잠시 유지한 후 천천히 내려줍니다.",
                        "주요 근육: 삼각근, 이두근"}
        };

        // 식단 리스트 동적으로 생성
        for (String[] workout : workoutList) {
            createWorkoutView(workout[0], workout[1], workout[2]);
        }
    }

    private void createWorkoutView(String namew, String descriptionw, String workout_muscle) {
        View workoutView = LayoutInflater.from(this).inflate(R.layout.item_workout, null);
        TextView namewTextView = workoutView.findViewById(R.id.namew_text_view);
        TextView descriptionwTextView = workoutView.findViewById(R.id.descriptionw_text_view);
        TextView workout_muscleTextView = workoutView.findViewById(R.id.workout_muscle_text_view);

        namewTextView.setText(namew);
        descriptionwTextView.setText(descriptionw);
        workout_muscleTextView.setText(workout_muscle);

        workoutListLayout.addView(workoutView);
    }
}
