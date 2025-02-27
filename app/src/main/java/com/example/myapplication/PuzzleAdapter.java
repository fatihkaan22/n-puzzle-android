package com.example.myapplication;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class PuzzleAdapter extends ArrayAdapter<Piece> {

  private final int mResource;
  private int column;

  public PuzzleAdapter(@NonNull Context context, int resource, ArrayList<Piece> textViewResourceId, int column) {
    super(context, resource, textViewResourceId);
    this.mResource = resource;
    this.column = column;
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
  @NonNull
  @Override
  public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    if (convertView == null)
      convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);

    convertView = getItem(position);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      // set board size according to number of columns in puzzle
      if (convertView != null)
        ((Button) convertView).setAutoSizeTextTypeUniformWithConfiguration(14,
                80,
                2,
                TypedValue.COMPLEX_UNIT_DIP);

    }
    if (convertView != null)
      convertView.setBackground(convertView.getResources().getDrawable(R.drawable.back));

    if (convertView != null)
      ((Button) convertView).setGravity(Gravity.CENTER);

    //for readability of 9x puzzle
    if (convertView != null)
      ((Button) convertView).setMinHeight(0);
    ((Button) convertView).setMinWidth(0);
    ((Button) convertView).setIncludeFontPadding(false);

    return convertView;
  }
}
