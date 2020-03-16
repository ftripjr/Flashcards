package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class EmptyCardActivity extends AppCompatActivity {

    private final int ADD_CARD_REQUEST_CODE = 20;
    public static final String TAG_IVNEXT = "EmptyActivity";

    ImageView ivAddCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptycard);

        Log.i(TAG_IVNEXT, "DAIJOBU");

        ivAddCard = findViewById(R.id.ivAddCard);

        ivAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EmptyCardActivity.this, AddCardActivity.class);
                EmptyCardActivity.this.startActivityForResult(i, ADD_CARD_REQUEST_CODE);
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

            Intent i = new Intent(EmptyCardActivity.this, MainActivity.class);
            i.putExtra("question", question);
            i.putExtra("answer", answer);
            i.putExtra("wrong1", wrong1);
            i.putExtra("wrong2", wrong2);
            setResult(RESULT_OK, i);
            finish();
        }
    }
}
