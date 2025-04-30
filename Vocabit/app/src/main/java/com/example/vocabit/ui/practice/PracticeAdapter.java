package com.example.vocabit.ui.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabit.databinding.ItemBtnLeftBinding;
import com.example.vocabit.databinding.ItemBtnRightBinding;
import com.example.vocabit.databinding.ItemNameUnitBinding;

public class PracticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NAME_UNIT = 0;
    private static final int BTN_LEFT = 1;
    private static final int BTN_RIGHT = 2;
    private final Context context;
    private final int itemCount;

    public PracticeAdapter(Context context, int itemCount) {
        this.context = context;
        this.itemCount = itemCount;
    }

    @Override
    public int getItemCount() {
        return itemCount; // number of items to display
    }

    @Override
    public int getItemViewType(int position) {
        int mod = position % 3;
        if (mod == 0) return NAME_UNIT;
        else if (mod == 1) return BTN_LEFT;
        else return BTN_RIGHT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case NAME_UNIT: {
                ItemNameUnitBinding binding = ItemNameUnitBinding.inflate(inflater, parent, false);
                return new NameUnitViewHolder(binding);
            }
            case BTN_LEFT: {
                ItemBtnLeftBinding binding = ItemBtnLeftBinding.inflate(inflater, parent, false);
                return new BtnLeftViewHolder(binding);
            }
            case BTN_RIGHT:
            default: {
                ItemBtnRightBinding binding = ItemBtnRightBinding.inflate(inflater, parent, false);
                return new BtnRightViewHolder(binding);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case NAME_UNIT:
                // static UI, no binding needed
                break;
            case BTN_LEFT:
                ((BtnLeftViewHolder) holder).binding.getRoot().setOnClickListener(v ->
                        Toast.makeText(context, "Left button clicked at position " + position, Toast.LENGTH_SHORT).show());
                break;
            case BTN_RIGHT:
                ((BtnRightViewHolder) holder).binding.getRoot().setOnClickListener(v ->
                        Toast.makeText(context, "Right button clicked at position " + position, Toast.LENGTH_SHORT).show());
                break;
        }
    }

    static class NameUnitViewHolder extends RecyclerView.ViewHolder {
        public NameUnitViewHolder(ItemNameUnitBinding binding) {
            super(binding.getRoot());
        }
    }

    static class BtnLeftViewHolder extends RecyclerView.ViewHolder {
        final ItemBtnLeftBinding binding;
        public BtnLeftViewHolder(ItemBtnLeftBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    static class BtnRightViewHolder extends RecyclerView.ViewHolder {
        final ItemBtnRightBinding binding;
        public BtnRightViewHolder(ItemBtnRightBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
