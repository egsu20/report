package com.example.ch9_r1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("선그리기 실습");
    }

    private static class MyGraphicView extends View {
        int startX = -5, startY = -5, stopX = -5, stopY = -5;
        public MyGraphicView(Context context){
            super(context);
        }
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStrokeWidth(8);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);

            canvas.drawLine(startX, startY, stopX, stopY, paint);

            paint.setColor(Color.BLUE);
            paint.setTextSize(50);
            paint.setStrokeWidth(3);
            canvas.drawText("시작점", startX, startY, paint);
            canvas.drawText("끝점", stopX, stopY, paint);
        }

        public boolean onTouchEvent(MotionEvent event){
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = (int)event.getX();
                    startY = (int)event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }
    }
}