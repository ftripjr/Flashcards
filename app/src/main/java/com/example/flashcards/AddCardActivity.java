package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    private final int REQ_CODE = 11;

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

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddCardActivity.this, MainActivity.class);
                i.putExtra("question", etQuestion.getText().toString());
                i.putExtra("answer", etAnswer.getText().toString());
                i.putExtra("wrong1", etWrong1.getText().toString());
                i.putExtra("wrong2", etWrong2.getText().toString());
                AddCardActivity.this.startActivityForResult(i, REQ_CODE);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQ_CODE) {
            // Extract name value from result extras
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            String wrong1 = data.getExtras().getString("wrong1");
            String wrong2 = data.getExtras().getString("wrong2");
        }
    }
}
