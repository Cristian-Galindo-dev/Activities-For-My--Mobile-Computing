package com.example.room_persistance_project.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_persistance_project.R;
import com.example.room_persistance_project.model.entity.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<History> historyList = new ArrayList<>();

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History currentHistory = historyList.get(position);
        holder.action.setText(currentHistory.action);
        holder.details.setText(currentHistory.details);
        holder.date.setText(currentHistory.createdAt);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public void setHistory(List<History> history) {
        this.historyList = history;
        notifyDataSetChanged();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView action;
        private final TextView details;
        private final TextView date;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            action = itemView.findViewById(R.id.text_view_history_action);
            details = itemView.findViewById(R.id.text_view_history_details);
            date = itemView.findViewById(R.id.text_view_history_date);
        }
    }
}
