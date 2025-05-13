package com.example.vocabit.ui.matchQuestion;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabit.databinding.ItemWordBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<String> words = new ArrayList<>();
    private Set<String> matchedWords = new HashSet<>();
    private final OnWordClickListener listener;
    private String feedbackWord = null;
    private boolean isCorrect = false;


    public interface OnWordClickListener {
        void onClick(String word);
    }

    public WordAdapter(OnWordClickListener listener) {
        this.listener = listener;
    }

    public void submitList(List<String> list) {
        this.words = list;
        notifyDataSetChanged();
    }

    public void setMatchedWords(Set<String> matched) {
        this.matchedWords = matched;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWordBinding binding = ItemWordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        String word = words.get(position);
        holder.binding.btnWord.setText(word);
        holder.binding.btnWord.setEnabled(!matchedWords.contains(word));
        holder.binding.btnWord.setOnClickListener(v -> listener.onClick(word));

        if (word.equals(feedbackWord)) {
            // Ưu tiên hiển thị feedback màu xanh/đỏ
            holder.binding.btnWord.setBackgroundColor(isCorrect ? Color.GREEN : Color.RED);
        } else if (matchedWords.contains(word)) {
            // Nếu đã matched và không đang feedback thì hiển thị xám
            holder.binding.btnWord.setBackgroundColor(Color.GRAY);
            holder.binding.btnWord.setEnabled(false);
        } else {
            // Màu mặc định
            holder.binding.btnWord.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            holder.binding.btnWord.setEnabled(true);
        }
    }


    @Override
    public int getItemCount() {
        return words.size();
    }
    public void showFeedback(String word, boolean correct) {
        this.feedbackWord = word;
        this.isCorrect = correct;
        notifyDataSetChanged();
    }

    public void clearFeedback() {
        this.feedbackWord = null;
        notifyDataSetChanged();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        ItemWordBinding binding;

        public WordViewHolder(@NonNull ItemWordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
