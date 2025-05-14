package com.example.vocabit.ui.exam;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.exam.ExamResponse;
import com.example.vocabit.databinding.ItemExamBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {
    private final Context context;
    private List<ExamResponse> examList = new ArrayList<>();

    public ExamAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<ExamResponse> list) {
        this.examList = list != null ? list : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemExamBinding binding = ItemExamBinding.inflate(inflater, parent, false);
        return new ExamViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        ExamResponse exam = examList.get(position);


        Glide.with(context)
                .load(R.drawable.siba)
                .override(200, 200)
                .centerCrop()
                .into(holder.binding.imgIcon);

        holder.binding.getRoot().setOnClickListener(v -> {
            // TODO: Handle starting activity with exam data
            Toast.makeText(context, "Loading exam for Unit " + exam.getUnit(), Toast.LENGTH_SHORT).show();

            // Ví dụ gửi dữ liệu nếu cần:
             Intent intent = new Intent(context, ExamDetailActivity.class);
             intent.putExtra("EXTRA_EXAM", new Gson().toJson(exam));
             context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    static class ExamViewHolder extends RecyclerView.ViewHolder {
        final ItemExamBinding binding;

        public ExamViewHolder(ItemExamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
