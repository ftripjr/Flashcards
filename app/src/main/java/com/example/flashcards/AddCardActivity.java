package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    EditText etQuestion;
    EditText etAnswer;
    EditText etWrong1;
    EditText etWrong2;

    ImageView ivCancel;
    ImageView ivSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);

        etQuestion = findViewById(R.id.etQuestion);
        etAnswer= findViewById(R.id.etAnswer);
        etWrong1 = findViewById(R.id.etWrong1);
        etWrong2 = findViewById(R.id.etWrong2);

        ivSave = findViewById(R.id.ivSaveCard);
        ivCancel = findViewById(R.id.ivCancelCard);

        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");
        String wrong1 = getIntent().getStringExtra("wrong1");
        String wrong2 = getIntent().getStringExtra("wrong2");

        etQuestion.setText(question);
        etAnswer.setText(answer);
        etWrong1.setText(wrong1);
        etWrong2.setText(wrong2);

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = etQuestion.getText().toString();
                String answer = etAnswer.getText().toString();
                String wrong1 = etWrong1.getText().toString();
                String wrong2 = etWrong2.getText().toString();

                if(question.isEmpty() || answer.isEmpty())
                {
                    Toast.makeText(AddCardActivity.this, "Please, complete all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i = new Intent(AddCardActivity.this, MainActivity.class);
                i.putExtra("question", question);
                i.putExtra("answer", answer);
                i.putExtra("wrong1", wrong1);
                i.putExtra("wrong2", wrong2);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
