package com.example.ch9_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("선그리기 실습");
    }

    private static class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;
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
                    this.invalidate();
                    break;
            }
            return true;
        }
    }
}