package com.example.vocabit.ui.practice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabit.data.model.api.response.practice.PracticeResponse;
import com.example.vocabit.databinding.ItemBtnLeftBinding;
import com.example.vocabit.databinding.ItemNameUnitBinding;
import com.example.vocabit.ui.extraLetter.ExtraLetterQuestionActivity;
import com.example.vocabit.ui.fillQuestion.FillQuestionActivity;
import com.example.vocabit.ui.imageQuestion.ImageQuestionActivity;
import com.example.vocabit.ui.matchQuestion.MatchQuestionActivity;

import java.util.ArrayList;
import java.util.List;

public class PracticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_UNIT = 0;
    private static final int TYPE_BUTTON = 1;
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
        return (items.get(position) instanceof Integer) ? TYPE_UNIT : TYPE_BUTTON;
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
        } else {
            ItemBtnLeftBinding b = ItemBtnLeftBinding.inflate(inflater, parent, false);
            return new ButtonViewHolder(b);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_UNIT) {
            int unit = (Integer) items.get(position);
            ((UnitViewHolder) holder).binding.txtNameUnit.setText("Unit " + unit);
        } else {
            PracticeResponse p = (PracticeResponse) items.get(position);
            ButtonViewHolder vh = (ButtonViewHolder) holder;
            vh.binding.button.setOnClickListener(v -> {
                // Chuyển Unit qua ImageToTextQuestionActivity
                Intent intent;
                switch (((PracticeResponse) items.get(position)).getQuestionType()){
                    case "IMAGE_TO_TEXT" :
                        intent = new Intent(context, ImageQuestionActivity.class);
                        intent.putExtra(ImageQuestionActivity.EXTRA_UNIT, p.getUnit());
                        context.startActivity(intent);
                        break;
                    case "FILL_IN_BLANK" :
                        intent = new Intent(context, FillQuestionActivity.class);
                        intent.putExtra(FillQuestionActivity.EXTRA_UNIT, p.getUnit());
                        context.startActivity(intent);
                        break;
                    case "EXTRA_LETTER" :
                        intent = new Intent(context, ExtraLetterQuestionActivity.class);
                        intent.putExtra(ExtraLetterQuestionActivity.EXTRA_UNIT, p.getUnit());
                        context.startActivity(intent);
                        break;
                    case "MATCHING" :
                        intent = new Intent(context, MatchQuestionActivity.class);
                        intent.putExtra(MatchQuestionActivity.EXTRA_UNIT, p.getUnit());
                        context.startActivity(intent);
                        break;
                    default:
                        break;
                }

            });
        }
    }

    static class UnitViewHolder extends RecyclerView.ViewHolder {
        final ItemNameUnitBinding binding;
        UnitViewHolder(ItemNameUnitBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    static class ButtonViewHolder extends RecyclerView.ViewHolder {
        final ItemBtnLeftBinding binding;
        ButtonViewHolder(ItemBtnLeftBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
