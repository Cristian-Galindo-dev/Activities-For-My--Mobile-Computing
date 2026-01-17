package com.example.room_persistance_project.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_persistance_project.R;
import com.example.room_persistance_project.model.entity.Note;
import com.example.room_persistance_project.model.relations.CategoryWithNotes;

import java.util.ArrayList;
import java.util.List;

public class CategoryWithNotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CATEGORY = 0;
    private static final int TYPE_NOTE = 1;

    private List<Object> items = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof CategoryWithNotes) {
            return TYPE_CATEGORY;
        }
        return TYPE_NOTE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_CATEGORY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_header_item, parent, false);
            return new CategoryViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
            return new NoteViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_CATEGORY) {
            CategoryWithNotes categoryWithNotes = (CategoryWithNotes) items.get(position);
            ((CategoryViewHolder) holder).bind(categoryWithNotes.category.categoryName);
        } else {
            Note note = (Note) items.get(position);
            ((NoteViewHolder) holder).bind(note);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<CategoryWithNotes> categoriesWithNotes) {
        items.clear();
        for (CategoryWithNotes categoryWithNotes : categoriesWithNotes) {
            items.add(categoryWithNotes); // Add category header
            items.addAll(categoryWithNotes.notes); // Add notes for that category
        }
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryHeader;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryHeader = itemView.findViewById(R.id.text_view_category_header);
        }

        void bind(String categoryName) {
            categoryHeader.setText(categoryName);
        }
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteTitle;
        private final TextView noteContent;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.text_view_note_title);
            noteContent = itemView.findViewById(R.id.text_view_note_content);
        }

        void bind(Note note) {
            noteTitle.setText(note.noteTitle);
            noteContent.setText(note.noteContent);
        }
    }
}
