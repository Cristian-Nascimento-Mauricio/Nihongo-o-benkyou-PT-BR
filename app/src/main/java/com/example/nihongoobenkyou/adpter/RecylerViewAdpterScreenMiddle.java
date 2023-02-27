package com.example.nihongoobenkyou.adpter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;


import java.util.List;
import java.util.Random;

public class RecylerViewAdpterScreenMiddle extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  List<List<Nivels_of_Screen_Middle>> list;
    public RecylerViewAdpterScreenMiddle(List<List<Nivels_of_Screen_Middle>> list){
        this.list = list;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView textView;
        ImageView imageView;
        Context context;
        public MyviewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            progressBar = itemView.findViewById(R.id.progressBar_1);
            textView = itemView.findViewById(R.id.textView_1);
            imageView = itemView.findViewById(R.id.imageView_1);
        }


    }
    public class MyviewHolder_2 extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView textView;
        ImageView imageView;
        Context context;

        ProgressBar progressBar2;
        TextView textView2;
        ImageView imageView2;


        public MyviewHolder_2(View itemView) {
            super(itemView);

            context = itemView.getContext();

            progressBar = itemView.findViewById(R.id.progressBar_2);
            textView = itemView.findViewById(R.id.textView_2);
            imageView = itemView.findViewById(R.id.imageView_2);

            progressBar2 = itemView.findViewById(R.id.progressBar_3);
            textView2 = itemView.findViewById(R.id.textView_3);
            imageView2 = itemView.findViewById(R.id.imageView_3);

        }


    }
    public class MyviewHolder_3 extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView textView;
        ImageView imageView;

        ProgressBar progressBar2;
        TextView textView2;
        ImageView imageView2;

        ProgressBar progressBar3;
        TextView textView3;
        ImageView imageView3;

        Context context;


        public MyviewHolder_3(View itemView) {
            super(itemView);
            context = itemView.getContext();

            progressBar = itemView.findViewById(R.id.progressBar_4);
            textView = itemView.findViewById(R.id.textView_4);
            imageView = itemView.findViewById(R.id.imageView_4);

            progressBar2 = itemView.findViewById(R.id.progressBar_5);
            textView2 = itemView.findViewById(R.id.textView_5);
            imageView2 = itemView.findViewById(R.id.imageView_5);

            progressBar3 = itemView.findViewById(R.id.progressBar_6);
            textView3 = itemView.findViewById(R.id.textView_6);
            imageView3 = itemView.findViewById(R.id.imageView_6);

        }

    }
    @Override
    public int getItemViewType(int position) {

        return list.get(position).size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 1) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_adpter_version_1, parent, false);

            return new MyviewHolder(itemView);
        } else if(viewType == 2){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_adpter_version_2, parent, false);

            return new MyviewHolder_2(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_adpter_version_3, parent, false);

            return new MyviewHolder_3(itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if(holder.getItemViewType() == 1){
            Nivels_of_Screen_Middle nivels = list.get(position).get(0);
            MyviewHolder Holder = (MyviewHolder)holder;

            Holder.progressBar.getBackground().setColorFilter(nivels.getColor(),PorterDuff.Mode.SRC);

            if(nivels.getColor() == Color.GRAY) {
                Drawable drawable = Holder.context.getResources().getDrawable(R.drawable.level_up_gray);
                Holder.progressBar.setProgressDrawable(drawable);
            }


            Holder.progressBar.setProgress(nivels.getProgressBar());
            Holder.textView.setText(nivels.toString());
            Holder.imageView.setImageResource(nivels.getImageView());


        } else if(holder.getItemViewType() == 2){

            Nivels_of_Screen_Middle nivels  = list.get(position).get(0);
            Nivels_of_Screen_Middle nivels2 = list.get(position).get(1);

            MyviewHolder_2 Holder = (MyviewHolder_2)holder;

            Holder.progressBar.getBackground().setColorFilter(nivels.getColor(),PorterDuff.Mode.SRC_ATOP);
            if(nivels.getColor() == Color.GRAY) {
                Drawable drawable = Holder.context.getResources().getDrawable(R.drawable.level_up_gray);
                Holder.progressBar.setProgressDrawable(drawable);
                Holder.progressBar2.setProgressDrawable(drawable);

            }
            Holder.progressBar.setProgress(nivels.getProgressBar());
            Holder.textView.setText(nivels.toString());
            Holder.imageView.setImageResource(nivels.getImageView());

            Holder.progressBar2.getBackground().setColorFilter(nivels2.getColor(),PorterDuff.Mode.SRC_ATOP);

            Holder.progressBar2.setProgress(nivels2.getProgressBar());
            Holder.textView2.setText(nivels2.toString());
            Holder.imageView2.setImageResource(nivels2.getImageView());

        } else if(holder.getItemViewType() == 3){

            Nivels_of_Screen_Middle nivels  = list.get(position).get(0);
            Nivels_of_Screen_Middle nivels2 = list.get(position).get(1);
            Nivels_of_Screen_Middle nivels3 = list.get(position).get(2);

            MyviewHolder_3 Holder = (MyviewHolder_3)holder;

            Holder.progressBar.getBackground().setColorFilter(nivels.getColor(),PorterDuff.Mode.SRC_ATOP);

            if(nivels.getColor() == Color.GRAY) {
                Drawable drawable = Holder.context.getResources().getDrawable(R.drawable.level_up_gray);
                Holder.progressBar.setProgressDrawable(drawable);
                Holder.progressBar2.setProgressDrawable(drawable);
                Holder.progressBar3.setProgressDrawable(drawable);
            }
            Holder.progressBar.setProgress(nivels.getProgressBar());
            Holder.textView.setText(nivels.toString());
            Holder.imageView.setImageResource(nivels.getImageView());

            Holder.progressBar2.getBackground().setColorFilter(nivels2.getColor(),PorterDuff.Mode.SRC_ATOP);

            Holder.progressBar2.setProgress(nivels2.getProgressBar());
            Holder.textView2.setText(nivels2.toString());
            Holder.imageView2.setImageResource(nivels2.getImageView());

            Holder.progressBar3.getBackground().setColorFilter(nivels3.getColor(),PorterDuff.Mode.SRC_ATOP);

            Holder.progressBar3.setProgress(nivels3.getProgressBar());
            Holder.textView3.setText(nivels3.toString());
            Holder.imageView3.setImageResource(nivels3.getImageView());


        }


    }


    @Override
    public int getItemCount() {

        return this.list.size();
    }


}
