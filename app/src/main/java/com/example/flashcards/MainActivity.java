package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 20;

    TextView tvQuestion;
    TextView tvFlashBack;
    TextView tvAnswer;
    TextView tvWrong1;
    TextView tvWrong2;
    ImageView ivAddCard;
    ImageView ivEditCard;
    ImageView ivEyeOn;
    ImageView ivEyeOff;
    RelativeLayout relBackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvFlashQuestion);
        tvFlashBack = findViewById(R.id.tvFlashBackAnswer);
        tvAnswer= findViewById(R.id.tvFlashAnswer);
        tvWrong1 = findViewById(R.id.tvFlashWrong1);
        tvWrong2 = findViewById(R.id.tvFlashWrong2);
        ivAddCard = findViewById(R.id.ivAddCard);
        ivEditCard = findViewById(R.id.ivEditCard);
        ivEyeOn = findViewById(R.id.ivEyeOn);
        ivEyeOff= findViewById(R.id.ivEyeOff);
        relBackdrop = findViewById(R.id.rlBackdrop);


        relBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setBackground(getDrawable(R.drawable.answer_background));
                tvWrong1.setBackground(getDrawable(R.drawable.answer_background));
                tvWrong2.setBackground(getDrawable(R.drawable.answer_background));
            }
        });

        tvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if (tvQuestion.getVisibility() == View.VISIBLE)
                {
                    tvQuestion.setVisibility(View.INVISIBLE);
                    tvFlashBack.setVisibility(View.VISIBLE);
                }
            }
        });

        tvFlashBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if (tvFlashBack.getVisibility() == View.VISIBLE)
                {
                    tvQuestion.setVisibility(View.VISIBLE);
                    tvFlashBack.setVisibility(View.INVISIBLE);
                }
            }
        });

        tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAnswer.setBackground(getDrawable(R.drawable.correct_answer));
            }
        });

        tvWrong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvWrong1.setBackground(getDrawable(R.drawable.wrong_answer));
            }
        });

        tvWrong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvWrong2.setBackground(getDrawable(R.drawable.wrong_answer));
            }
        });

        ivAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivity(i);
            }
        });

        ivEditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddCardActivity.class);
                i.putExtra("question", tvQuestion.toString());
                i.putExtra("answer", tvAnswer.toString());
                i.putExtra("wrong1", tvWrong1.toString());
                i.putExtra("wrong2", tvWrong2.toString());
                MainActivity.this.startActivityForResult(i, REQUEST_CODE);
            }
        });

        ivEyeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if (ivEyeOn.getVisibility() == View.VISIBLE)
                {
                    ivEyeOn.setVisibility(View.INVISIBLE);
                    ivEyeOff.setVisibility(View.VISIBLE);
                    tvAnswer.setVisibility(View.VISIBLE);
                    tvWrong1.setVisibility(View.VISIBLE);
                    tvWrong2.setVisibility(View.VISIBLE);
                }
            }
        });

        ivEyeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if (ivEyeOff.getVisibility() == View.VISIBLE)
                {
                    ivEyeOn.setVisibility(View.VISIBLE);
                    ivEyeOff.setVisibility(View.INVISIBLE);
                    tvAnswer.setVisibility(View.INVISIBLE);
                    tvWrong1.setVisibility(View.INVISIBLE);
                    tvWrong2.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            String wrong1 = data.getExtras().getString("wrong1");
            String wrong2 = data.getExtras().getString("wrong2");
        }
    }
}
