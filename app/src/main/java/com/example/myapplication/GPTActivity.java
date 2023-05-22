package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.SharedPreferences;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit; // TimeUnit import 추가


public class GPTActivity extends AppCompatActivity {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS) // 연결 타임아웃 값 설정 (예: 30초)
            .readTimeout(120, TimeUnit.SECONDS) // 읽기 타임아웃 값 설정 (예: 30초)
            .writeTimeout(60, TimeUnit.SECONDS) // 쓰기 타임아웃 값 설정 (예: 30초)
            .build();

    private SharedPreferencesHelper sharedPreferencesHelper;
    private UserDAO mUserDao;
    private User loggedInUser;
    private Button backButton;
    private String height; // height 변수 추가
    private String age; // age 변수 추가
    private String weight; // weight 변수 추가
    private String bmi; // bmi 변수 추가
    private String gender; // gender 변수 추가
    private String muscle; // muscle 변수 추가
    private String fat; // fat 변수 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gptactivity); // activity_main layout, change if needed

        sharedPreferencesHelper = new SharedPreferencesHelper(getApplicationContext());

        Button button = findViewById(R.id.button_get_recommendation);
        button.setOnClickListener(this::getRecommendation);

        backButton = findViewById(R.id.back_button_gpt);
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
        AppExecutors.getInstance().diskIO().execute(() ->

        {
            // 백그라운드 스레드에서 데이터베이스 쿼리 수행
            /*            PHDatabase db = PHDatabase.getInstance(getApplicationContext());*/
            PHDatabase db = Room.databaseBuilder(getApplicationContext(), PHDatabase.class, "PH_db")
                    .fallbackToDestructiveMigration() //스키마 버전 변경 가능
                    .allowMainThreadQueries() //메인 스레드에서 DB에 IO를 가능하게 함
                    .build();
            mUserDao = db.UserDao();
            User user = mUserDao.getUserByEmail(loggedInUserEmail);
            height = user.getHeight();
            age = user.getAge();
            weight = user.getWeight();
            bmi = user.getBMI();
            gender = user.getGender();
            muscle = user.getMuscle_mass();
            fat = user.getBody_fat_percentage();
        });
    }

    private void getRecommendation(View view) {
        String prompt = "넌 전문 운동가라고 가정하고 사용자의 키는 "
                + height + "cm, 몸무게는 "
                + weight + "kg, 나이는 "
                + age + "세 bmi는 "
                +bmi+ " 성별은 "
                +gender+ " 근육량은 "
                +muscle+ "체지방률은 "
                +fat+ "이야 지금부터 헬스 운동 방법이랑 횟수랑 중량을 추천해줘, 단 null값을 받는다면 무시하고 답해";

        JSONArray messagesArray = new JSONArray();

        JSONObject userMessage = new JSONObject();


        messagesArray.put(userMessage);

        JSONObject jsonBody = new JSONObject();
        try {
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            jsonBody.put("model", "gpt-3.5-turbo");
            jsonBody.put("messages", messagesArray);
/*            jsonBody.put("model", "text-davinci-003");
            jsonBody.put("prompt", prompt);
            jsonBody.put("max_tokens", 3000);
            jsonBody.put("temperature", 0.0);*/
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer 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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
                showToast("네트워크 요청이 실패했습니다: " + e.getMessage());

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    // Parse JSON response body, extract the recommendation and update UI.
                    try {
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        JSONArray jsonArray = jsonResponse.getJSONArray("choices");
                        if (jsonArray.length() > 0) {
                            JSONObject choice = jsonArray.getJSONObject(0);
                            JSONObject message = choice.getJSONObject("message");
                            String content = message.getString("content").trim();
                            showToast("요청은 성공했어");
                            runOnUiThread(() -> {
                                TextView recommendationTextView = findViewById(R.id.text_recommendation);
                                recommendationTextView.setText("운동 추천: " + content);
                                recommendationTextView.invalidate(); // Force immediate UI update
                            });
                        } else {
                            showToast("운동 추천을 찾을 수 없습니다");
                        }
                        Log.d("GPTActivity", "API Response: " + jsonResponse.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();

/*                        JSONObject jsonResponse = new JSONObject(responseBody);
                        JSONArray jsonArray = jsonResponse.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text").trim();
                        showToast("요청은 성공했어");
                        runOnUiThread(() -> {
                            TextView recommendationTextView = findViewById(R.id.text_recommendation);
                            recommendationTextView.setText("운동 추천: " + result);
                            recommendationTextView.invalidate(); // Force immediate UI update

                        });
                    } catch (JSONException e) {
                        e.printStackTrace();*/
                    }
                } else {
                    // Handle failure
                    showToast("요청이 실패했습니다: " + response.code());
                    Log.e("GPTActivity", "Request failed with code: " + response.code());
                    try {
                        String errorBody = response.body().string();
                        Log.e("GPTActivity", "Error response body: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(GPTActivity.this, message, Toast.LENGTH_SHORT).show());
    }
}
