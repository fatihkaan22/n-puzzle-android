package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SizeChooser extends AppCompatActivity {
  private NumberPicker rowPicker;
  private NumberPicker columnPicker;
  private Button startGame;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_size_chooser);

    rowPicker = findViewById(R.id.row_picker);
    rowPicker.setMinValue(3);
    rowPicker.setMaxValue(9);

    columnPicker = findViewById(R.id.column_picker);
    columnPicker.setMinValue(3);
    columnPicker.setMaxValue(9);

    startGame = findViewById(R.id.start_game);
    startGame.setOnClickListener((v) -> openBoardActivity());

  }

  private void openBoardActivity() {
    Intent intent = new Intent(this, BoardActivity.class);
    intent.putExtra("row", rowPicker.getValue());
    intent.putExtra("column", columnPicker.getValue());
    startActivity(intent);
  }

}
