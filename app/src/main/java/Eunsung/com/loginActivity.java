package Eunsung.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class loginActivity extends AppCompatActivity {
    TextView TextView_get ;

    //main(String [] args)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView_get = findViewById(R.id.TextView_get);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String email = bundle.getString("email");
        String password = bundle.getString("password");

        String[] array = new String[10];

        array[0] = "밥을 먹어라";
        array[1] = "지금은 한숨쉴때";
        array[2] = "니가 가라 하와이";
        array[3] = "밥은 먹고 다니냐";
        array[4] = "자랑할 것이 없구나";
        array[5] = "하늘을 바라보라";
        array[6] = "포기해도 될 시간이다";
        array[7] = "너는 잘하고 있니?";
        array[8] = "혼자서는 아무것도 못한다";
        array[9] = "무엇이 너의 진짜 속 마음 이냐";




        Random r=new Random();
        int randomNumber=r.nextInt(array.length);
        System.out.println(array[randomNumber]);

        TextView_get.setText(array[randomNumber]);
    }
}
