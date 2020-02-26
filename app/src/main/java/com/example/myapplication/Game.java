package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("Registered")
public class Game extends Activity {
  Board board;
  Context context;
  private int numberOfMoves;

  public Game(Context context, int rows, int columns) {
    this.context = context;
    this.numberOfMoves = 0;
    this.board = new Board(rows, columns);
  }

  @SuppressLint("ClickableViewAccessibility")
  public void initBoard(GridView gridView, final TextView moves) {

    for (int i = 0; i < board.getSIZE(); i++) {
      Piece p = new Piece(context);
      p.setText(String.valueOf(i + 1));
      p.setTag(i + 1);

      p.setOnTouchListener((v, event) -> {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
          if (board.isMovable(((int) v.getTag()) - 1)) {
            board.move((Piece) v);
            Game.this.numberOfMoves++;
            moves.setText(String.valueOf(numberOfMoves));
            if (board.isSolved()) {
              Toast.makeText(context, "Solved!", Toast.LENGTH_SHORT).show();

              new AlertDialog.Builder(context)
                      .setTitle("Congratulations! 🎉")
                      .setMessage("Number of moves: " + numberOfMoves)
                      .setPositiveButton("OK", (dialog, which) -> {
                        Toast.makeText(context, "New Game", Toast.LENGTH_LONG).show();
                        numberOfMoves = 0;
                        moves.setText(String.valueOf(numberOfMoves));
                        board.scramble();
                      }).show();
            }
          } else {
            Toast.makeText(context, "Not movable", Toast.LENGTH_SHORT).show();
          }
        }
        return false;
      });
      board.getNumbers().add(p);
    }

    board.getNumbers().get(board.getEmptyIndex()).setVisibility(View.INVISIBLE);

    PuzzleAdapter puzzleAdapter = new PuzzleAdapter(context, R.layout.puzzle_layout, board.getNumbers(), board.getCOLUMNS());
    gridView.setAdapter(puzzleAdapter);

    board.scramble();

  }
}
