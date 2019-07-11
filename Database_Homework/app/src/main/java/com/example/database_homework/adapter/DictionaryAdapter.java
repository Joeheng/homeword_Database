package com.example.database_homework.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.database_homework.DetailActivity;
import com.example.database_homework.R;
import com.example.database_homework.entity.Word;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {
    List<Word> words;
    Context context;

    public DictionaryAdapter(List<Word> words, Context context) {
        this.words = words;
        this.context = context;
        this.listener = (setListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DictionaryAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Word word = words.get(position);

        viewHolder.tvWord.setText(word.word);
        viewHolder.tvpart_of_Speech.setText(word.partofSpeech);
        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context,DetailActivity.class);
            intent.putExtra("WORD",word.word);
            intent.putExtra("PARTOFSPEECH",word.partofSpeech);
            intent.putExtra("DEFINITION",word.definition);
            context.startActivity(intent);
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,v);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()){
                        case R.id.btnUpdate:
                            listener.OnUpdateItem(word,viewHolder.getAdapterPosition());
                            return true;
                        case R.id.btnRemove:
                            listener.OnRemoveItem(word,viewHolder.getAdapterPosition());
                            return true;
                    }
                    return false;

                });
                popupMenu.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvWord,tvpart_of_Speech;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tvWord);
            tvpart_of_Speech  = itemView.findViewById(R.id.tvPos);

        }
    }
    public void addMoreWord(List<Word> words){
        int previousSize = getItemCount();
        this.words.addAll(words);
        notifyItemRangeInserted(previousSize-1,this.words.size());
    }
    setListener listener;
    public interface setListener{
        void OnRemoveItem(Word word,int position);
        void OnUpdateItem(Word word,int position);
    }
    public void removeItem(Word word,int position){
        this.words.remove(word);
        notifyItemRemoved(position);
    }
}
