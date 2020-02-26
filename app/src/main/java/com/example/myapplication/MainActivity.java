package com.example.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    openActivitySizeChooser();

    finish();
  }

  public void openActivitySizeChooser() {
    Intent intent = new Intent(this, SizeChooser.class);
    startActivity(intent);
  }

}
