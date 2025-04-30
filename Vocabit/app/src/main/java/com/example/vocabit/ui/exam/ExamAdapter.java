package com.example.vocabit.ui.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vocabit.R;
import com.example.vocabit.databinding.ItemBtnLeftBinding;
import com.example.vocabit.databinding.ItemBtnRightBinding;
import com.example.vocabit.databinding.ItemExamBinding;
import com.example.vocabit.databinding.ItemNameUnitBinding;

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NAME_UNIT = 0;
    private static final int BTN_LEFT = 1;
    private static final int BTN_RIGHT = 2;
    private final Context context;

    public ExamAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return 9; // Always three items
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemExamBinding binding = ItemExamBinding.inflate(inflater, parent, false);
        return new BtnLeftViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case NAME_UNIT:
                BtnLeftViewHolder vh = (BtnLeftViewHolder) holder;
                ((BtnLeftViewHolder) holder).binding.getRoot().setOnClickListener(v ->
                        Toast.makeText(context, "Left button clicked", Toast.LENGTH_SHORT).show());
                Glide.with(context)
                        .load(R.drawable.siba)
                        .override(200, 200)
                        .centerCrop()
                        .into(vh.binding.imgIcon);
                break;
            case BTN_LEFT:
                ((BtnLeftViewHolder) holder).binding.getRoot().setOnClickListener(v ->
                        Toast.makeText(context, "Left button clicked", Toast.LENGTH_SHORT).show());
                break;
            case BTN_RIGHT:
                ((BtnLeftViewHolder) holder).binding.getRoot().setOnClickListener(v ->
                        Toast.makeText(context, "Right button clicked", Toast.LENGTH_SHORT).show());
                break;
        }
    }

    static class BtnLeftViewHolder extends RecyclerView.ViewHolder {
        final ItemExamBinding binding;
        public BtnLeftViewHolder(ItemExamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}