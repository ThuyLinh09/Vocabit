package com.example.vocabit.ui.practice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabit.data.model.api.response.practice.PracticeResponse;
import com.example.vocabit.databinding.ItemBtnLeftBinding;
import com.example.vocabit.databinding.ItemBtnRightBinding;
import com.example.vocabit.databinding.ItemNameUnitBinding;
import com.example.vocabit.ui.extraLetter.ExtraLetterQuestionActivity;
import com.example.vocabit.ui.fillQuestion.FillQuestionActivity;
import com.example.vocabit.ui.imageQuestion.ImageQuestionActivity;
import com.example.vocabit.ui.matchQuestion.MatchQuestionActivity;

import java.util.ArrayList;
import java.util.List;

public class PracticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_UNIT = 0;
    private static final int TYPE_BUTTON_LEFT = 1;
    private static final int TYPE_BUTTON_RIGHT = 2;
    private static Context context;

    // items có thể là Integer (unit header) hoặc PracticeResponse (button)
    private List<Object> items = new ArrayList<>();

    public PracticeAdapter(Context ctx) {
        context = ctx;
    }

    public void updateList(List<PracticeResponse> list) {
        items.clear();
        int lastUnit = -1;
        if (list != null) {
            for (PracticeResponse p : list) {
                if (p.getUnit() != lastUnit) {
                    lastUnit = p.getUnit();
                    items.add(lastUnit);  // header Unit
                }
                items.add(p);            // item question
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        if (item instanceof Integer) {
            return TYPE_UNIT;
        } else {
            // đếm số lượng buttons trước vị trí này để xác định chẵn/lẻ
            int count = 0;
            for (int i = 0; i <= position; i++) {
                if (items.get(i) instanceof PracticeResponse) {
                    count++;
                }
            }
            return (count % 2 == 1) ? TYPE_BUTTON_LEFT : TYPE_BUTTON_RIGHT;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_UNIT) {
            ItemNameUnitBinding b = ItemNameUnitBinding.inflate(inflater, parent, false);
            return new UnitViewHolder(b);
        } else if (viewType == TYPE_BUTTON_LEFT) {
            ItemBtnLeftBinding b = ItemBtnLeftBinding.inflate(inflater, parent, false);
            return new ButtonViewHolderLeft(b);
        } else {
            ItemBtnRightBinding b = ItemBtnRightBinding.inflate(inflater, parent, false);
            return new ButtonViewHolderRight(b);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_UNIT) {
            int unit = (Integer) items.get(position);
            ((UnitViewHolder) holder).binding.txtNameUnit.setText("Unit " + unit);
        } else {
            PracticeResponse p = (PracticeResponse) items.get(position);
            if (holder instanceof ButtonViewHolderLeft) {
                ((ButtonViewHolderLeft) holder).binding.button.setText(String.valueOf(position  - 5 * (p.getUnit() - 1)));
                ((ButtonViewHolderLeft) holder).binding.button.setOnClickListener(v -> openActivity(p));
            } else if (holder instanceof ButtonViewHolderRight) {
                ((ButtonViewHolderRight) holder).binding.button.setText(String.valueOf(position - 5 * (p.getUnit() - 1)));
                ((ButtonViewHolderRight) holder).binding.button.setOnClickListener(v -> openActivity(p));
            }
        }
    }
    private void openActivity(PracticeResponse p) {
        Intent intent;
        switch (p.getQuestionType()) {
            case "IMAGE_TO_TEXT":
                intent = new Intent(context, ImageQuestionActivity.class);
                intent.putExtra(ImageQuestionActivity.EXTRA_UNIT, p.getUnit());
                break;
            case "FILL_IN_BLANK":
                intent = new Intent(context, FillQuestionActivity.class);
                intent.putExtra(FillQuestionActivity.EXTRA_UNIT, p.getUnit());
                break;
            case "EXTRA_LETTER":
                intent = new Intent(context, ExtraLetterQuestionActivity.class);
                intent.putExtra(ExtraLetterQuestionActivity.EXTRA_UNIT, p.getUnit());
                break;
            case "MATCHING":
                intent = new Intent(context, MatchQuestionActivity.class);
                intent.putExtra(MatchQuestionActivity.EXTRA_UNIT, p.getUnit());
                break;
            default:
                return;
        }
        context.startActivity(intent);
    }

    static class UnitViewHolder extends RecyclerView.ViewHolder {
        final ItemNameUnitBinding binding;
        UnitViewHolder(ItemNameUnitBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    static class ButtonViewHolderLeft extends RecyclerView.ViewHolder {
        final ItemBtnLeftBinding binding;
        ButtonViewHolderLeft(ItemBtnLeftBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    static class ButtonViewHolderRight extends RecyclerView.ViewHolder {
        final ItemBtnRightBinding binding;
        ButtonViewHolderRight(ItemBtnRightBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

}
