package com.arash.altafi.room.java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arash.altafi.room.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterUserJava extends RecyclerView.Adapter<AdapterUserJava.ViewHolder> {

    List<userModel> modelList;
    onClickItem onClickItem;

    public AdapterUserJava(List<userModel> modelList, AdapterUserJava.onClickItem onClickItem) {
        this.modelList = modelList;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtName;
        private final TextView txtFamily;
        private final TextView txtAge;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtAge = itemView.findViewById(R.id.txt_age);
            txtName = itemView.findViewById(R.id.txt_name);
            txtFamily = itemView.findViewById(R.id.txt_family);
        }

        private void bind(userModel model) {
            txtAge.setText(String.valueOf(model.age));
            txtName.setText(model.name);
            txtFamily.setText(model.family);

            itemView.setOnLongClickListener(view -> {
                onClickItem.onDelete(model);
                return true;
            });

            itemView.setOnClickListener(view -> onClickItem.onUpdate(model));
        }

    }

    interface onClickItem {
        void onDelete(userModel model);

        void onUpdate(userModel model);
    }

}