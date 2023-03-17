package com.example.nihongoobenkyou.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.Interfaces.InterfaceHTML;
import com.example.nihongoobenkyou.MainActivity;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.classes.Articles_of_Article_Screen;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdpterArticles extends RecyclerView.Adapter<RecyclerViewAdpterArticles.MyViewHolder> {

    private List<Articles_of_Article_Screen> list = new ArrayList<>();
    private InterfaceHTML interfaceHTML;
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo, textOfArticle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.title_of_item_of_article);
            textOfArticle = itemView.findViewById(R.id.textOfArticle);

        }
    }

    public RecyclerViewAdpterArticles(List<Articles_of_Article_Screen> list, InterfaceHTML interfaceHTML){
        this.list = list;
        this.interfaceHTML = interfaceHTML;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_article,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Articles_of_Article_Screen item = list.get(position);

        holder.titulo.setText(item.getTitulo());
        holder.textOfArticle.setText(item.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceHTML.openActivityHTML(item.getTitulo());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

}
