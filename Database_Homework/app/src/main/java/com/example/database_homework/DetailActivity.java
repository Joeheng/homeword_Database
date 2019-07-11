package com.example.database_homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView tvword,tvpartofspeech,tvdefinition;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvword = findViewById(R.id.tvWord);
        tvpartofspeech = findViewById(R.id.tvpartofSpeech);
        tvdefinition = findViewById(R.id.tvDefinition);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        tvword.setText(intent.getStringExtra("WORD"));
        tvdefinition.setText(intent.getStringExtra("DEFINITION"));
        tvpartofspeech.setText(intent.getStringExtra("PARTOFSPEECH"));

        btnBack.setOnClickListener(v->{
            finish();
        });
    }
}
