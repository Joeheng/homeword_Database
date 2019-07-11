package com.example.database_homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database_homework.entity.Word;

public class EditActivity extends AppCompatActivity {
    Word word;
    EditText edtword, edtpartofspeech, edtdefinition;
    Button btnDone;
    DictionaryDatabase dictionaryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtword = findViewById(R.id.edt_Word);
        edtpartofspeech = findViewById(R.id.edt_partofSpeech);
        edtdefinition = findViewById(R.id.edt_Definition);
        btnDone = findViewById(R.id.btnDone);
        dictionaryDatabase = DictionaryDatabase.getInstance(this);

        Intent intent = getIntent();

        if (intent != null) {
            word = getIntent().getParcelableExtra("word");
            if (word != null) {
                edtword.setText(word.word);
                edtpartofspeech.setText(word.partofSpeech);
                edtdefinition.setText(word.definition);
            }
        }
        btnDone.setOnClickListener(v -> {
            SaveChange();
        });
    }

    private void SaveChange() {
        if (word == null)
            word = new Word();
        word.word = edtword.getText().toString();
        word.partofSpeech = edtpartofspeech.getText().toString();
        word.definition = edtdefinition.getText().toString();

        dictionaryDatabase.wordDao().edit(word);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditActivity.this, MainActivity.class));
        finish();

    }
}
