package com.example.nihongoobenkyou.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.R;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdpterKanji extends RecyclerView.Adapter<RecyclerViewAdpterKanji.MyViewHolder> {

    private List<String> list = new ArrayList<>();
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);

        }
    }
    public RecyclerViewAdpterKanji(List<String> list){
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_adpter_kanji, parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String item = list.get(position);

        holder.textView.setText(item);

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
