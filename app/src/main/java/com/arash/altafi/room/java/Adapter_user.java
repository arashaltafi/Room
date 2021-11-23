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

public class Adapter_user extends RecyclerView.Adapter<Adapter_user.ViewHolder> {

    List<user_model> modelList;
    onClickItem onClickItem;

    public Adapter_user(List<user_model> modelList, Adapter_user.onClickItem onClickItem) {
        this.modelList = modelList;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user , parent , false);
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

        private TextView txt_name,txt_family,txt_age;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txt_age = itemView.findViewById(R.id.txt_age);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_family = itemView.findViewById(R.id.txt_family);
        }

        private void bind(user_model model)
        {
            txt_age.setText(String.valueOf(model.age));
            txt_name.setText(model.name);
            txt_family.setText(model.family);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onClickItem.onDelete(model);
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItem.onUpdate(model);
                }
            });
        }

    }

    interface onClickItem
    {
        void onDelete(user_model model);
        void onUpdate(user_model model);
    }

}