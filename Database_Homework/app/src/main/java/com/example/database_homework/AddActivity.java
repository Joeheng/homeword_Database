package com.example.database_homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.database_homework.entity.Word;

public class AddActivity extends AppCompatActivity {
    EditText edtword,edtpartofspeech,edtdefinition;
    Button btnSave;
    Word word;
    ImageView btnBack;
    DictionaryDatabase dictionaryDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtword = findViewById(R.id.edt_Word);
        edtpartofspeech = findViewById(R.id.edt_partofSpeech);
        edtdefinition = findViewById(R.id.edt_Definition);
        btnSave = findViewById(R.id.btnSave);
        dictionaryDatabase = DictionaryDatabase.getInstance(this);

        btnSave.setOnClickListener(v->{
            Word word = new Word();

            word.word = edtword.getText().toString();
            word.partofSpeech = edtpartofspeech.getText().toString();
            word.definition = edtdefinition.getText().toString();
            saveWord(word);
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddActivity.this,MainActivity.class));
        });
    }
    private Long saveWord(Word word){
        if(word !=null){
            return dictionaryDatabase.wordDao().add(word);
        }
        return 0l;
    }
}
