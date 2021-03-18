package com.example.r1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3;
    int cntOnClick1 = 0;
    int cntOnClick2 = 0;
    int cntOnClick3 = 0;
    String textOnClick[] = {"클릭했습니다.", "눌렀습니다", "이미 눌렀습니다.", "충분히 눌렀습니다"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        // if문 이용
        button1.setOnClickListener(new View.OnClickListener(){
            int chkClicks = 0;
            String btnNum = "첫 번째 버튼을 ";

            public void onClick(View view){
                chkClicks = cntOnClick1 % 4;
                if(chkClicks == 0){
                    Toast.makeText(getApplicationContext(),  btnNum + textOnClick[0], Toast.LENGTH_SHORT).show();
                } else if(chkClicks == 1){
                    Toast.makeText(getApplicationContext(), btnNum + textOnClick[1], Toast.LENGTH_SHORT).show();
                } else if(chkClicks == 2){
                    Toast.makeText(getApplicationContext(), btnNum + textOnClick[2], Toast.LENGTH_SHORT).show();
                } else if(chkClicks == 3){
                    Toast.makeText(getApplicationContext(), btnNum + textOnClick[3], Toast.LENGTH_SHORT).show();
                }
                cntOnClick1++;
            }
        });

        // switch문 이용
        button2.setOnClickListener(new View.OnClickListener(){
            int chkClicks = 0;
            String btnNum = "두 번째 버튼을 ";

            public void onClick(View view){
                chkClicks = cntOnClick2 % 4;
                switch(chkClicks) {
                    case 0:
                        Toast.makeText(getApplicationContext(), btnNum + textOnClick[0], Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),btnNum + textOnClick[1], Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), btnNum + textOnClick[2], Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), btnNum + textOnClick[3], Toast.LENGTH_SHORT).show();
                        break;
                }
                cntOnClick2++;
            }
        });

        // 첫 번째 버튼 내용 + for문 이용
        button3.setOnClickListener(new View.OnClickListener(){
            int chkClicks = 0;
            String btnNum = "세 번째 버튼을 ";

            public void onClick(View view){
                chkClicks = cntOnClick3 % 4;
                if(chkClicks == 0){
                    Toast.makeText(getApplicationContext(),  btnNum + textOnClick[0], Toast.LENGTH_SHORT).show();
                } else if(chkClicks == 1){
                    Toast.makeText(getApplicationContext(), btnNum + textOnClick[1], Toast.LENGTH_SHORT).show();
                } else if(chkClicks == 2){
                    Toast.makeText(getApplicationContext(), btnNum + textOnClick[2], Toast.LENGTH_SHORT).show();
                } else if(chkClicks == 3){
                    Toast.makeText(getApplicationContext(), btnNum + textOnClick[3], Toast.LENGTH_SHORT).show();

                    Toast.makeText(getApplicationContext(), "오류로 인해 시스템이 재부팅됩니다!", Toast.LENGTH_SHORT).show();
                    for(int cntLoop=3 ; cntLoop>0 ; cntLoop--){
                        Toast.makeText(getApplicationContext(), "재부팅까지 " + cntLoop*3 + "초", Toast.LENGTH_SHORT).show();
                        try{
                            sleep(1000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "재부팅 완료", Toast.LENGTH_SHORT).show();
                }
                cntOnClick3++;
            }
        });
    }
}