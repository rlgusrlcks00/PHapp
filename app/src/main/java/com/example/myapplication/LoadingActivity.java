/*
import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 로딩 창 생성
        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        loadingDialog.setCancelable(false); // 로딩 창을 취소할 수 없도록 설정합니다.

        // 로딩 창 표시
        showLoadingDialog();

        // 로딩 창 숨기기
        // 로딩이 완료된 후에 호출하세요.
        hideLoadingDialog();
    }

    private void showLoadingDialog() {
        loadingDialog.show();
    }

    private void hideLoadingDialog() {
        loadingDialog.dismiss();
    }
}
*/
