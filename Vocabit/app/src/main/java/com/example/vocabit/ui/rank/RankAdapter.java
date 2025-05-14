package com.example.vocabit.ui.rank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.rank.RankResponse;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankViewHolder> {

    private List<RankResponse> rankList;

    public void setRankData(List<RankResponse> rankList) {
        this.rankList = rankList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);
        return new RankViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RankViewHolder holder, int position) {
        RankResponse rank = rankList.get(position);

        // Gắn dữ liệu vào các TextView
        holder.tvId.setText(String.valueOf(position + 1));
        holder.tvRank.setText(rank.getName());
        holder.tvScore.setText(String.valueOf(rank.getScore()));
        holder.tvTime.setText(String.valueOf(rank.getDurationInSeconds()));
    }

    @Override
    public int getItemCount() {
        return rankList == null ? 0 : rankList.size();
    }

    public static class RankViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvRank, tvScore, tvTime;

        public RankViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvRank = itemView.findViewById(R.id.tvFullName);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
