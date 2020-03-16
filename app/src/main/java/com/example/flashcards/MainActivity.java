package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int ADD_CARD_REQUEST_CODE = 20;
    private final int EDIT_CARD_REQUEST_CODE = 30;
    public static final String TAG_IVNEXT = "MainActivity - ivNext";
    RelativeLayout relBackdrop;

    // TextViews
    TextView tvQuestion;
    TextView tvFlashBack;
    TextView tvAnswer;
    TextView tvWrong1;
    TextView tvWrong2;

    // ImageViews
    ImageView ivAddCard;
    ImageView ivEditCard;
    ImageView ivEyeOn;
    ImageView ivEyeOff;
    ImageView ivNext;
    ImageView ivDelete;

    // Flashcard
    List<Flashcard> allFlashcards;
    FlashcardDatabase fcDatabase;
    Flashcard cardToEdit;

    int currCardDisplayedIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relBackdrop = findViewById(R.id.rlBackdrop);

        tvQuestion = findViewById(R.id.tvFlashQuestion);
        tvFlashBack = findViewById(R.id.tvFlashBackAnswer);
        tvAnswer= findViewById(R.id.tvFlashAnswer);
        tvWrong1 = findViewById(R.id.tvFlashWrong1);
        tvWrong2 = findViewById(R.id.tvFlashWrong2);

        ivAddCard = findViewById(R.id.ivAddCard);
        ivEditCard = findViewById(R.id.ivEditCard);
        ivEyeOn = findViewById(R.id.ivEyeOn);
        ivEyeOff = findViewById(R.id.ivEyeOff);
        ivDelete = findViewById(R.id.ivDelete);
        ivNext = findViewById(R.id.ivNext);

        fcDatabase= new FlashcardDatabase(getApplicationContext());
        allFlashcards = fcDatabase.getAllCards();

        if(allFlashcards != null && allFlashcards.size() > 0)
        {
            Toast.makeText(this, TAG_IVNEXT + " -- DATABASE SIZE = " + allFlashcards.size() , Toast.LENGTH_LONG).show();

            tvQuestion.setText(allFlashcards.get(0).getQuestion());
            tvAnswer.setText(allFlashcards.get(0).getAnswer());
            tvWrong1.setText(allFlashcards.get(0).getWrongAnswer1());
            tvWrong2.setText(allFlashcards.get(0).getWrongAnswer2());
            tvFlashBack.setText(allFlashcards.get(0).getAnswer());
        } else {
            Toast.makeText(this, TAG_IVNEXT + " -- DATABASE SIZE = Empty" , Toast.LENGTH_LONG).show();
            Log.i(TAG_IVNEXT, "DATABASE SIZE = " + allFlashcards.size());
        }

        relBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearScreen();
            }
        });

        tvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                cardFlip();
            }
        });

        tvFlashBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                cardFlip();
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
                MainActivity.this.startActivityForResult(i, ADD_CARD_REQUEST_CODE);
            }
        });

        ivEditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String question = tvQuestion.getText().toString();
                String answer = tvAnswer.getText().toString();
                String wrong1 = tvWrong1.getText().toString();
                String wrong2 = tvWrong2.getText().toString();

                // Find the card in question
                for (Flashcard f : allFlashcards) {
                    if (f.getQuestion().equals(question)) {
                        cardToEdit = f;
                    }
                }

                Intent i = new Intent(MainActivity.this, AddCardActivity.class);
                i.putExtra("question", question);
                i.putExtra("answer", answer);
                i.putExtra("wrong1", wrong1);
                i.putExtra("wrong2", wrong2);
                MainActivity.this.startActivityForResult(i, EDIT_CARD_REQUEST_CODE);
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

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearScreen();
                getRandomNumber(0, (allFlashcards.size() - 1));

                tvQuestion.setText(allFlashcards.get(currCardDisplayedIndex).getQuestion());
                tvFlashBack.setText(allFlashcards.get(currCardDisplayedIndex).getAnswer());
                tvAnswer.setText(allFlashcards.get(currCardDisplayedIndex).getAnswer());
                tvWrong1.setText(allFlashcards.get(currCardDisplayedIndex).getWrongAnswer1());
                tvWrong2.setText(allFlashcards.get(currCardDisplayedIndex).getWrongAnswer2());
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fcDatabase.deleteCard(tvQuestion.getText().toString());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == ADD_CARD_REQUEST_CODE) {
            // Extract name value from result extras
            String question = data.getStringExtra("question");
            String answer = data.getStringExtra("answer");
            String wrong1 = data.getStringExtra("wrong1");
            String wrong2 = data.getStringExtra("wrong2");

            tvQuestion.setVisibility(View.VISIBLE);
            tvFlashBack.setVisibility(View.INVISIBLE);

            Snackbar.make(tvQuestion,"New card created!", Snackbar.LENGTH_SHORT).show();

            fcDatabase.insertCard(new Flashcard(question, answer, wrong1, wrong2));
            allFlashcards = fcDatabase.getAllCards();

            // Change text in text views
            tvQuestion.setText(question);
            tvFlashBack.setText(answer);
            tvAnswer.setText(answer);
            tvWrong1.setText(wrong1);
            tvWrong2.setText(wrong2);
        }

        else if (resultCode == RESULT_OK && requestCode == EDIT_CARD_REQUEST_CODE) {
            // Extract name value from result extras
            String question = data.getStringExtra("question");
            String answer = data.getStringExtra("answer");
            String wrong1 = data.getStringExtra("wrong1");
            String wrong2 = data.getStringExtra("wrong2");

            tvQuestion.setVisibility(View.VISIBLE);
            tvFlashBack.setVisibility(View.INVISIBLE);

            Snackbar.make(tvQuestion,"Card updated!", Snackbar.LENGTH_SHORT).show();

            cardToEdit.setQuestion(question);
            cardToEdit.setAnswer(answer);
            cardToEdit.setWrongAnswer1(wrong1);
            cardToEdit.setWrongAnswer2(wrong2);

            fcDatabase.updateCard(cardToEdit);
            allFlashcards = fcDatabase.getAllCards();

            // Change text in text views
            tvQuestion.setText(question);
            tvFlashBack.setText(answer);
            tvAnswer.setText(answer);
            tvWrong1.setText(wrong1);
            tvWrong2.setText(wrong2);
        }
    }

    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }

    public void clearScreen()
    {
        // Clear answer
        if (tvFlashBack.getVisibility() == View.VISIBLE)
        {
            tvQuestion.setVisibility(View.VISIBLE);
            tvFlashBack.setVisibility(View.INVISIBLE);
        }

        // Clear Answer Selection
        tvAnswer.setBackground(getDrawable(R.drawable.answer_background));
        tvWrong1.setBackground(getDrawable(R.drawable.answer_background));
        tvWrong2.setBackground(getDrawable(R.drawable.answer_background));
    }

    public void cardFlip()
    {
        if (tvQuestion.getVisibility() == View.VISIBLE)
        {
            tvQuestion.setVisibility(View.INVISIBLE);
            tvFlashBack.setVisibility(View.VISIBLE);
        } else if (tvFlashBack.getVisibility() == View.VISIBLE)
        {
            tvQuestion.setVisibility(View.VISIBLE);
            tvFlashBack.setVisibility(View.INVISIBLE);
        }
    }
}
