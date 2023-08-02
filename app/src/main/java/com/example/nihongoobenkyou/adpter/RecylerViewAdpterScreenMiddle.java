package com.example.nihongoobenkyou.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
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

import com.example.nihongoobenkyou.Interfaces.InterfaceHTML;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.classes.Nivels_of_Screen_Middle;


import java.util.List;
import java.util.Random;

public class RecylerViewAdpterScreenMiddle extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private  List<List<Nivels_of_Screen_Middle>> list;
    public RecylerViewAdpterScreenMiddle(List<List<Nivels_of_Screen_Middle>> list){
        this.list = list;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView textView;
        ImageView imageView;
        Context context;
        Drawable drawable;
        public MyviewHolder(View itemView) {
            super(itemView);

            drawable = itemView.getContext().getDrawable(R.drawable.level_up_gray);

            context = itemView.getContext();
            progressBar = itemView.findViewById(R.id.progressBar_1);
            textView = itemView.findViewById(R.id.textView_1);
            imageView = itemView.findViewById(R.id.imageView_1);
        }

    }
    public class MyviewHolder_2 extends RecyclerView.ViewHolder{

        ProgressBar[] progressBars = new ProgressBar[2];
        TextView[] textViews = new TextView[2];
        ImageView[] imageViews = new  ImageView[2];
        Context context;

        public MyviewHolder_2(View itemView) {
            super(itemView);

            context = itemView.getContext();

            progressBars[0] = itemView.findViewById(R.id.progressBar_2);
            textViews[0] = itemView.findViewById(R.id.textView_2);
            imageViews[0] = itemView.findViewById(R.id.imageView_2);

            progressBars[1] = itemView.findViewById(R.id.progressBar_3);
            textViews[1] = itemView.findViewById(R.id.textView_3);
            imageViews[1] = itemView.findViewById(R.id.imageView_3);

        }

    }
    public class MyviewHolder_3 extends RecyclerView.ViewHolder{

        ProgressBar[] progressBars = new ProgressBar[3];
        TextView[] textViews = new TextView[3];
        ImageView[] imageViews = new  ImageView[3];

        Context context;

        public MyviewHolder_3(View itemView) {
            super(itemView);
            context = itemView.getContext();

            progressBars[0] = itemView.findViewById(R.id.progressBar_4);
            textViews[0] = itemView.findViewById(R.id.textView_4);
            imageViews[0] = itemView.findViewById(R.id.imageView_4);

            progressBars[1] = itemView.findViewById(R.id.progressBar_5);
            textViews[1] = itemView.findViewById(R.id.textView_5);
            imageViews[1] = itemView.findViewById(R.id.imageView_5);

            progressBars[2] = itemView.findViewById(R.id.progressBar_6);
            textViews[2]= itemView.findViewById(R.id.textView_6);
            imageViews[2] = itemView.findViewById(R.id.imageView_6);

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
        int positionOfList = 0;

        if(holder.getItemViewType() == 1){
            Nivels_of_Screen_Middle nivels = list.get(position).get(0);
            MyviewHolder Holder = (MyviewHolder)holder;

            Holder.progressBar.getBackground().setColorFilter(nivels.getColor(),PorterDuff.Mode.SRC);
            Holder.progressBar.setProgress(nivels.getProgressBar());
            Holder.imageView.setImageDrawable(null);;
            Holder.textView.setText(nivels.toString());


        } else if(holder.getItemViewType() == 2){
            Nivels_of_Screen_Middle[] nivels = new Nivels_of_Screen_Middle[2];

            nivels[0]  = list.get(position).get(0);
            nivels[1]  = list.get(position).get(1);

            MyviewHolder_2 Holder = (MyviewHolder_2)holder;

            for ( Nivels_of_Screen_Middle item: nivels) {
                Holder.progressBars[positionOfList].getBackground().setColorFilter(item.getColor(),PorterDuff.Mode.SRC_ATOP);

                Holder.progressBars[positionOfList].setProgress(item.getProgressBar());
                Holder.textViews[positionOfList].setText(item.toString());
                Holder.imageViews[positionOfList].setImageDrawable(null);
                positionOfList++;
            }

        } else if(holder.getItemViewType() == 3){

            Nivels_of_Screen_Middle[] nivels = new Nivels_of_Screen_Middle[3];

            nivels[0]  = list.get(position).get(0);
            nivels[1]  = list.get(position).get(1);
            nivels[2]  = list.get(position).get(2);

            MyviewHolder_3 Holder = (MyviewHolder_3)holder;

            for ( Nivels_of_Screen_Middle item: nivels) {
                Holder.progressBars[positionOfList].getBackground().setColorFilter(item.getColor(),PorterDuff.Mode.SRC_ATOP);

                Holder.progressBars[positionOfList].setProgress(item.getProgressBar());
                Holder.textViews[positionOfList].setText(item.toString());
                Holder.imageViews[positionOfList].setImageDrawable(null);
                positionOfList++;
            }

        }

    }

    @Override
    public int getItemCount() {

        return this.list.size();
    }

}
