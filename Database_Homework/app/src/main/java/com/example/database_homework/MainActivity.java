package com.example.database_homework;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.database_homework.adapter.DictionaryAdapter;
import com.example.database_homework.entity.Word;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DictionaryAdapter.setListener{
    RecyclerView rv;
    DictionaryAdapter dictionaryAdapter;
    List<Word> words = new ArrayList<>();
    DictionaryDatabase dictionaryDatabase;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        getWords();
        previous_WordList.addAll(words);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                doSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                words.clear();
                words.addAll(previous_WordList);
                dictionaryAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    private void initUI() {
        rv = findViewById(R.id.recyclerView);
        dictionaryAdapter = new DictionaryAdapter(words, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);
        rv.setAdapter(dictionaryAdapter);
        dictionaryDatabase = DictionaryDatabase.getInstance(this);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddActivity.class));
        });
    }
    private void getWords() {
        /*for (int i=0;i<100;i++){
            Word word = new Word();
            word.word = "dictionary";
            word.partofSpeech = "noun";
            word.definition = "a book or electronic resource that lists the words of a language (typically in alphabetical order) and gives their meaning, or gives the equivalent words in a different language, often also providing information about pronunciation, origin, and usage.";
            words.add(word);
        }
        dictionaryAdapter.notifyDataSetChanged();*/
        List<Word> words = dictionaryDatabase.wordDao().getWordList();
        dictionaryAdapter.addMoreWord(words);
    }
    List<Word> previous_WordList = new ArrayList<>();

    private void doSearch(String s) {
        if (s.isEmpty()) {
            return;
        }
        if (words.size() == 0)
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            words.clear();
        for (Word word : previous_WordList) {
            if (word.word.equalsIgnoreCase(s)) {
                previous_WordList.add(word);
            }
        }
        dictionaryAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnRemoveItem(Word word, int position) {
        dictionaryDatabase.wordDao().remove(word);
        dictionaryAdapter.removeItem(word,position);
    }

    @Override
    public void OnUpdateItem(Word word, int position) {
        Intent intent = new Intent(this,EditActivity.class);
        intent.putExtra("word",word);
        startActivity(intent);
    }
}
