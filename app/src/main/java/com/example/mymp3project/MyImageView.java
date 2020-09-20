package com.example.mymp3project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyImageView extends View {
    //멤버변수
    private String imagePath;

    //View를 상속을 받는 객체는 무엇을 의미하냐. 이것은 UI객체다.
    //매개변수 1개있는 UI는 클래스내에서 객테로 만들어서 사용하겠다.
    public MyImageView(Context context) {
        super(context);
    }

    //매개변수 2개있는 UI는 xml에서 드래그 앤 드롭으로 위치를 배치시키겠다.
    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.imagePath = null;
    }

    //MyImageView 객체가 만들어지면 화면에 그려질때 이 함수를 자동으로 불러주는 콜백함수.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //그려져야 할 이미지파일을 불러서 화면에다가 그려넣어야한다.
        if(imagePath != null){
            //1. 비트맵(버튼 : 2진법으로 화면을 그려주는 객체가 있다.)
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            //2. 캔버스에 그린다.
            canvas.scale(2, 2, 0,0);
            //3. 캔버스에 비트맵을 그려넣는다.
            canvas.drawBitmap(bitmap, 0, 0, null);
            bitmap.recycle();
        }
    }

    //getter, setter
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
