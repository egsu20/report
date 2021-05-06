package com.example.ch9_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);
        }
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);

            paint.setStrokeWidth(5);
            Path path1 = new Path();
            path1.moveTo(0, 29);
            path1.lineTo(0+50, 29+50);
            path1.lineTo(0+100, 29);
            path1.lineTo(0+150, 29+50);
            path1.lineTo(0+200, 29);
            canvas.drawPath(path1, paint);

            paint.setStrokeWidth(0);
            paint.setTextSize(30);
            canvas.drawText("안드로이드", 10, 200, paint);
        }
    }

}