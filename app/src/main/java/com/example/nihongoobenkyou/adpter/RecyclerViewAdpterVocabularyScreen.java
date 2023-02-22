package com.example.nihongoobenkyou.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.classes.Vocabulary_of_Vocabulary_Screen;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdpterVocabularyScreen extends RecyclerView.Adapter<RecyclerViewAdpterVocabularyScreen.MyViewHolder> {


    private List<Vocabulary_of_Vocabulary_Screen> list = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView textpreview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

                title = itemView.findViewById(R.id.title_of_item);
                textpreview = itemView.findViewById(R.id.TextPreview);

        }
    }

    public RecyclerViewAdpterVocabularyScreen(List<Vocabulary_of_Vocabulary_Screen> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_adpter_screen_version_1, parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Vocabulary_of_Vocabulary_Screen item = list.get(position);

        holder.title.setText(item.getTitle());
        holder.textpreview.setText(item.getTextpresview());



    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }



}
