package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EmptyCardActivity extends AppCompatActivity  {

    ImageView ivAddCard;
    public static final String TAG_IVNEXT = "EmptyActivity";

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
                EmptyCardActivity.this.startActivity(i);
            }
        });
    }

}
