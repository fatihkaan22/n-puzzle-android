package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.annotation.Nullable;

public class Piece extends Button {

//  private final int EMPTY_PIECE = -1;

//  public void setEmpty() {
//    this.setText(String.valueOf(EMPTY_PIECE));
//    this.setTag(EMPTY_PIECE);
//  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, widthMeasureSpec); //to force the pieces to be square
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
