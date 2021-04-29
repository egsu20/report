package com.example.ch8_r1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker dp;
    EditText edtDiary;
    Button btnWrite, btnClear;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp = (DatePicker)findViewById(R.id.datePicker1);
        edtDiary = (EditText)findViewById(R.id.edtDiary);
        btnWrite = (Button)findViewById(R.id.btnWrite);
        btnClear = (Button)findViewById(R.id.btnClear);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth =  cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener(){
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                fileName = Integer.toString(year) + "_"+
                        Integer.toString(monthOfYear+1) +"_"
                        + Integer.toString(dayOfMonth) +".txt";
                String str = readDiary(fileName);
                edtDiary.setText(str);
                btnWrite.setEnabled(true);
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName+" 이 저장됨", Toast.LENGTH_SHORT).show();
                    btnClear.setEnabled(true); ///////////추가
                } catch(IOException e){}
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener(){ ///////////추가
            public void onClick(View v){
                try {
                    File f = new File(fileName);
                    f.delete();
                    Toast.makeText(getApplicationContext(), "삭제 완료", Toast.LENGTH_SHORT).show();
                    edtDiary.setHint("일기 없음");
                    edtDiary.setText(null);
                    btnWrite.setText("새로 저장");
                } catch(Exception e){}
            }
        });
    }
    String readDiary(String fName){
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");
            btnClear.setText("삭제하기");
        }catch(IOException e){
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
            btnClear.setEnabled(false); ///////////추가
        }
        return diaryStr;
    }
}