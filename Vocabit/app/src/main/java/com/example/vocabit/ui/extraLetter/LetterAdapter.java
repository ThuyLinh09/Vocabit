package com.example.vocabit.ui.extraLetter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.extraLetter.LetterWrapper;

import java.util.List;
import java.util.function.Consumer;

public class LetterAdapter extends RecyclerView.Adapter<LetterAdapter.LetterViewHolder> {
    private List<LetterWrapper> letters;
    private final Consumer<Integer> onClick;

    public LetterAdapter(List<LetterWrapper> letters, Consumer<Integer> onClick) {
        this.letters = letters;
        this.onClick = onClick;
    }

    public void setLetters(List<LetterWrapper> newList) {
        this.letters = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LetterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_letter, parent, false);
        return new LetterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LetterViewHolder holder, int position) {
        LetterWrapper letter = letters.get(position);
        holder.text.setText(letter.getLetter());
        holder.text.setVisibility(letter.isVisible() ? View.VISIBLE : View.INVISIBLE);
        holder.text.setOnClickListener(v -> onClick.accept(position));
    }

    @Override
    public int getItemCount() {
        return letters != null ? letters.size() : 0;
    }

    static class LetterViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        LetterViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tvLetter);
        }
    }
}


