package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

@SuppressLint("Registered")
public class Game extends Activity {
  ArrayList<Piece> numbers;
  final int ROWS;
  final int COLUMNS;
  final int SIZE;
  Context context;
  private int emptyIndex;
  private int numberOfMoves;

  public Game(Context context, int rows, int columns) {
    ROWS = rows;
    COLUMNS = columns;
    SIZE = rows * columns;
    this.numbers = new ArrayList<>();
    this.context = context;
    this.emptyIndex = SIZE - 1;
    this.numberOfMoves = 0;
  }

  private boolean isMovable(int index) {
    if (index - 1 == emptyIndex && index % COLUMNS != 0) return true;
    if (index + 1 == emptyIndex && emptyIndex % COLUMNS != 0) return true;
    if (index - COLUMNS == emptyIndex || index + COLUMNS == emptyIndex) return true;
    return false;
  }

  private boolean isSolved() {
    for (int i = 1; i <= SIZE; i++) {
      if (!numbers.get(i - 1).getText().equals(Integer.toString(i))) return false;
    }
    return true;
  }

  private void move(Piece p) {
    String tmp = (String) p.getText();
    p.setText(numbers.get(emptyIndex).getText());
    numbers.get(emptyIndex).setText(tmp);
    numbers.get(emptyIndex).setVisibility(View.VISIBLE);
    emptyIndex = (int) p.getTag() - 1;
    numbers.get(emptyIndex).setVisibility(View.INVISIBLE);

    //TODO: swap pieces instead of values
  }

  private void scramble() {
    Random random = new Random();
    do { // if board comes to initial postion after scramble, scramble again
      for (int i = 0; i < SIZE * SIZE; i++) {
        switch (random.nextInt(4)) {
          case 0:
            if (emptyIndex + 1 < SIZE && (emptyIndex + 1) % COLUMNS != 0)
              move(numbers.get(emptyIndex + 1));
            break;
          case 1:
            if (emptyIndex - 1 >= 0 && emptyIndex % COLUMNS != 0)
              move(numbers.get(emptyIndex - 1));
            break;
          case 2:
            if (emptyIndex - COLUMNS >= 0)
              move(numbers.get(emptyIndex - COLUMNS));
            break;
          case 3:
            if (emptyIndex + COLUMNS < SIZE)
              move(numbers.get(emptyIndex + COLUMNS));
            break;
        }
      }
    } while (isSolved());
  }

  @SuppressLint("ClickableViewAccessibility")
  public void initBoard(GridView gridView, final TextView moves) {
    for (int i = 0; i < SIZE; i++) {
      Piece p = new Piece(context);
      p.setText(String.valueOf(i + 1));
      p.setTag(i + 1);

      p.setOnTouchListener((v, event) -> {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
          if (isMovable(((int) v.getTag()) - 1)) {
//            Toast.makeText(context, String.valueOf(v.getTag()), Toast.LENGTH_SHORT).show();
            move((Piece) v);
            Game.this.numberOfMoves++;
            moves.setText(String.valueOf(numberOfMoves));
            if (isSolved()) {
              Toast.makeText(context, "Solved!", Toast.LENGTH_SHORT).show();

              new AlertDialog.Builder(context)
                      .setTitle("Game Over")
                      .setMessage("Number of moves: " + numberOfMoves)
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          Toast.makeText(context, "New Game", Toast.LENGTH_LONG).show();
                          numberOfMoves = 0;
                          moves.setText(String.valueOf(numberOfMoves));
                          scramble();
                        }
                      }).show();
            }
          } else {
            Toast.makeText(context, "Not movable", Toast.LENGTH_SHORT).show();
          }
        }
        return false;
      });
      numbers.add(p);
    }

//    numbers.get(emptyIndex).setEmpty();
    numbers.get(emptyIndex).setVisibility(View.INVISIBLE);

    PuzzleAdapter puzzleAdapter = new PuzzleAdapter(context, R.layout.puzzle_layout, numbers, COLUMNS);
    gridView.setAdapter(puzzleAdapter);

    //TODO: make a different class for numbers arraylist
    scramble();

  }
}
