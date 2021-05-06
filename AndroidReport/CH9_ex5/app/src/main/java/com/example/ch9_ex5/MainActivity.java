package com.example.ch9_ex5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);
        }
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.jeju14);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, null);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.rotate(45, cenX, cenY);
            canvas.drawBitmap(picture, picX, picY, null);

            picture.recycle();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }
}