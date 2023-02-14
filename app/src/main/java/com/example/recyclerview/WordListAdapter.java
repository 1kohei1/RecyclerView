package com.example.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;
    private Set<Integer> checkedIndexes;

    public WordListAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.checkedIndexes = new HashSet<>();
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.mCheckbox.setText(mCurrent);
        // Reset to false. This might be recycled and set to be false.
        holder.mCheckbox.setChecked(false);
        holder.mCheckbox.setOnClickListener(view -> {
            if (this.checkedIndexes.contains(position)) {
                this.checkedIndexes.remove(position);
            } else {
                this.checkedIndexes.add(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    // This only works because the list item isn't reordered by the users.
    public Set<Integer> getCheckedIndexes() {
        return this.checkedIndexes;
    }

    public void emptyCheckedIndexes() {
        this.checkedIndexes.clear();
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final CheckBox mCheckbox;
        final WordListAdapter mAdapter;

        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            mCheckbox = itemView.findViewById(R.id.checkBox);
            this.mAdapter = adapter;
        }

        @Override
        public void onClick(View view) {}
    }
}
