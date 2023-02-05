package com.example.nihongoobenkyou.adpter;

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

public class RecylerViewAdpterScreenMiddle extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  List<List<Nivels_of_Screen_Middle>> list;
    public RecylerViewAdpterScreenMiddle(List<List<Nivels_of_Screen_Middle>> list){
        this.list = list;
        list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView textView;
        ImageView imageView;

        public MyviewHolder( View itemView) {
            super(itemView);

            progressBar = itemView.findViewById(R.id.progressBar_1);
            textView = itemView.findViewById(R.id.textView_1);
            imageView = itemView.findViewById(R.id.imageView_1);
        }
    }
    public class MyviewHolder_2 extends RecyclerView.ViewHolder{
        ProgressBar progressBar2;
        TextView textView2;
        ImageView imageView2;

        ProgressBar progressBar3;
        TextView textView3;
        ImageView imageView3;


        public MyviewHolder_2(View itemView) {
            super(itemView);


            progressBar2 = itemView.findViewById(R.id.progressBar_2);
            textView2 = itemView.findViewById(R.id.textView_2);
            imageView2 = itemView.findViewById(R.id.imageView_2);

            progressBar3 = itemView.findViewById(R.id.progressBar_3);
            textView3 = itemView.findViewById(R.id.textView_3);
            imageView3 = itemView.findViewById(R.id.imageView_3);

        }
    }
    @Override
    public int getItemViewType(int position) {

        int i = list.get(position).size();
        return i;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 1) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_adpter_version_1, parent, false);

            return new MyviewHolder(itemView);
        } else{
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_adpter_version_2, parent, false);

            return new MyviewHolder_2(itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if(holder.getItemViewType() == 1){
            Nivels_of_Screen_Middle nivels = list.get(position).get(0);
            MyviewHolder Holder = (MyviewHolder)holder;

            Holder.progressBar.setProgress(nivels.getProgressBar());
            Holder.textView.setText(nivels.getTextView());
            Holder.imageView.setImageResource(nivels.getImageView());

        } else{

            Nivels_of_Screen_Middle nivels = list.get(position).get(0);
            Nivels_of_Screen_Middle nivels2 = list.get(position).get(1);

            MyviewHolder_2 Holder = (MyviewHolder_2)holder;

            Holder.progressBar2.setProgress(nivels.getProgressBar());
            Holder.textView2.setText(nivels.getTextView());
            Holder.imageView2.setImageResource(nivels.getImageView());

            Holder.progressBar3.setProgress(nivels2.getProgressBar());
            Holder.textView3.setText(nivels2.getTextView());
            Holder.imageView3.setImageResource(nivels2.getImageView());
        }


    }


    @Override
    public int getItemCount() {

        return this.list.size();
    }


}
