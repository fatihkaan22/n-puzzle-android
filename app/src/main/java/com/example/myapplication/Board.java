package com.example.myapplication;

import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class Board {
  private ArrayList<Piece> numbers;
  private final int ROWS;
  private final int COLUMNS;
  private final int SIZE;
  private int emptyIndex;

  public Board(int ROWS, int COLUMNS) {
    this.numbers = new ArrayList<>();
    this.ROWS = ROWS;
    this.COLUMNS = COLUMNS;
    this.SIZE = ROWS * COLUMNS;
    this.emptyIndex = SIZE - 1;
  }

  public boolean isMovable(int index) {
    if (index - 1 == emptyIndex && index % COLUMNS != 0) return true;
    if (index + 1 == emptyIndex && emptyIndex % COLUMNS != 0) return true;
    if (index - COLUMNS == emptyIndex || index + COLUMNS == emptyIndex) return true;
    return false;
  }

  public void move(Piece p) {
    String tmp = (String) p.getText();
    p.setText(numbers.get(emptyIndex).getText());
    numbers.get(emptyIndex).setText(tmp);
    numbers.get(emptyIndex).setVisibility(View.VISIBLE);
    emptyIndex = (int) p.getTag() - 1;
    numbers.get(emptyIndex).setVisibility(View.INVISIBLE);
  }

  public void scramble() {
    Random random = new Random();
    do { // if board comes to initial portion after scramble, scramble again
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

  public boolean isSolved() {
    for (int i = 1; i <= SIZE; i++) {
      if (!numbers.get(i - 1).getText().equals(Integer.toString(i))) return false;
    }
    return true;
  }

  public ArrayList<Piece> getNumbers() {
    return numbers;
  }

  public int getCOLUMNS() {
    return COLUMNS;
  }

  public int getSIZE() {
    return SIZE;
  }

  public int getEmptyIndex() {
    return emptyIndex;
  }
}
