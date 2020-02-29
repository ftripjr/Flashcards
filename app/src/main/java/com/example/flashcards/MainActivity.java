package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvQuestion;
    TextView tvAnswer;
    TextView tvWrong1;
    TextView tvWrong2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvFlashQuestion);
        tvAnswer= findViewById(R.id.tvFlashAnswer);
        tvWrong1 = findViewById(R.id.tvFlashWrong1);
        tvWrong2 = findViewById(R.id.tvFlashWrong2);

        tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setBackgroundColor(getResources().getColor(R.color.colorAnswer));
            }
        });

        tvWrong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvWrong1.setBackgroundColor(getResources().getColor(R.color.colorWrong));
            }
        });

        tvWrong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvWrong2.setBackgroundColor(getResources().getColor(R.color.colorWrong));
            }
        });

    }
}
