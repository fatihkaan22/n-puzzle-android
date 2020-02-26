package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;
import android.os.Bundle;

public class BoardActivity extends AppCompatActivity {
  public int rows;
  public int columns;
  GridView gridView;
  TextView numberOfMoves;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_board);

    this.rows = getIntent().getIntExtra("row", 3);
    this.columns = getIntent().getIntExtra("column", 3);

    findViewById(R.id.back_button).setOnClickListener(v -> onBackPressed());

    gridView = findViewById(R.id.puzzle_grid);
    gridView.setNumColumns(columns);

    // to get screen width and height
    LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    @SuppressLint("InflateParams")
    View contentview = inflater != null ? inflater.inflate(R.layout.activity_board, null, false) : null;

    if (contentview != null)
      contentview.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

    int x = contentview != null ? contentview.getMeasuredWidth() : 0;
    int y = contentview != null ? contentview.getMeasuredHeight() : 0;

    //prove of formula
    //https://drive.google.com/file/d/1eYe3b3YVTdqksoJdx2jMrkPvU4nAlGBW/view?usp=sharing
    int paddingToFitScreen = (x - (columns * y / rows)) / 2;
    if (paddingToFitScreen < 0) paddingToFitScreen = 0;
    gridView.setPadding(paddingToFitScreen, gridView.getPaddingTop(), paddingToFitScreen, gridView.getPaddingBottom());
    if (columns > rows && rows < 7) {     //activate orientation for usability of landscape mode
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
      if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        Toast.makeText(this, "You can rotate screen for better experience.", Toast.LENGTH_LONG).show();
    }

    numberOfMoves = findViewById(R.id.moves);

    Game game = new Game(this, rows, columns);
    game.initBoard(gridView, numberOfMoves);

  }
}
