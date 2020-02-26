package com.example.myapplication;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
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
    View contentview = inflater.inflate(R.layout.activity_board, null, false);
    contentview.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

    int x = contentview.getMeasuredWidth();
    int y = contentview.getMeasuredHeight();

    //see also: FIXME: add link samsung notes to get formula
    int paddingToFitScreen = (x - (columns * y / rows)) / 2;
    if (paddingToFitScreen < 0) paddingToFitScreen = 0;
    gridView.setPadding(paddingToFitScreen, gridView.getPaddingTop(), paddingToFitScreen, gridView.getPaddingBottom());
    if (columns > rows && rows < 7) {   //activate orientation for usability of landscape mode
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
      if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        Toast.makeText(this, "You can rotate screen for better experience.", Toast.LENGTH_LONG).show(); //FIXME: increase length
    }

    numberOfMoves = findViewById(R.id.moves);

    Game game = new Game(this, rows, columns);
    //FIXME: try setters instead of passing as arguments
    game.initBoard(gridView, numberOfMoves);

  }
}
