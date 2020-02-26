package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class Piece extends Button {

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, widthMeasureSpec); //to force pieces to be square
  }

  public Piece(Context context) {
    super(context);
  }

  public Piece(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Piece(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
}
