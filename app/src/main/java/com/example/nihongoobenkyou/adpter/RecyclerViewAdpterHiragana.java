package com.example.nihongoobenkyou.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.R;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdpterHiragana extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<List<String>> list = new ArrayList<>();

    public class MyviewHolder extends RecyclerView.ViewHolder{

        Button btn_1 , btn_2 , btn_3 , btn_4 , btn_5;

        public MyviewHolder(View itemView) {
            super(itemView);

            btn_1 = itemView.findViewById(R.id.button);
            btn_2 = itemView.findViewById(R.id.button2);
            btn_3 = itemView.findViewById(R.id.button3);
            btn_4 = itemView.findViewById(R.id.button4);
            btn_5 = itemView.findViewById(R.id.button5);

        }
    }
    public class MyviewHolder2 extends RecyclerView.ViewHolder{
        Button btn_1 , btn_2 , btn_3;

        public MyviewHolder2(View itemView) {
            super(itemView);

            btn_1 = itemView.findViewById(R.id.button6);
            btn_2 = itemView.findViewById(R.id.button7);
            btn_3 = itemView.findViewById(R.id.button8);
        }
    }
    public class MyviewHolder3 extends RecyclerView.ViewHolder{
        Button btn_1 , btn_2;

        public MyviewHolder3(View itemView) {
            super(itemView);

            btn_1 = itemView.findViewById(R.id.button9);
            btn_2 = itemView.findViewById(R.id.button10);
        }
    }
    public class MyviewHolder4 extends RecyclerView.ViewHolder{
        Button btn_1;

        public MyviewHolder4(View itemView) {
            super(itemView);

            btn_1 = itemView.findViewById(R.id.button11);

        }
    }



    public RecyclerViewAdpterHiragana(List<List<String>> list){
        this.list = list;

    }


    @Override
    public int getItemViewType(int position) {

        return this.list.get(position).size();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == 5){

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_hiragana_version_1, parent, false);

            return new MyviewHolder(itemView);
        }else if(viewType == 3){

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_hiragana_version_2, parent, false);

            return new MyviewHolder2(itemView);
        } else if(viewType == 2){

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_hiragana_version_3, parent, false);

            return new MyviewHolder3(itemView);
        } else if(viewType == 1){

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_hiragana_version_4, parent, false);

            return new MyviewHolder4(itemView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder.getItemViewType() == 5){
            String text = this.list.get(position).get(0).toString();
            String text_2 = this.list.get(position).get(1).toString();
            String text_3 = this.list.get(position).get(2).toString();
            String text_4 = this.list.get(position).get(3).toString();
            String text_5 = this.list.get(position).get(4).toString();

            MyviewHolder Holder = (MyviewHolder) holder;

            Holder.btn_1.setText(text);
            Holder.btn_2.setText(text_2);
            Holder.btn_3.setText(text_3);
            Holder.btn_4.setText(text_4);
            Holder.btn_5.setText(text_5);


        }else if(holder.getItemViewType() == 3){
            String text = this.list.get(position).get(0).toString();
            String text_2 = this.list.get(position).get(1).toString();
            String text_3 = this.list.get(position).get(2).toString();

            MyviewHolder2 Holder = (MyviewHolder2) holder;

            Holder.btn_1.setText(text);
            Holder.btn_2.setText(text_2);
            Holder.btn_3.setText(text_3);

        }else if(holder.getItemViewType() == 2){
            String text = this.list.get(position).get(0).toString();
            String text_2 = this.list.get(position).get(1).toString();

            MyviewHolder3 Holder = (MyviewHolder3) holder;

            Holder.btn_1.setText(text);
            Holder.btn_2.setText(text_2);

        } else if(holder.getItemViewType() == 1){
        String text = this.list.get(position).get(0).toString();

        MyviewHolder4 Holder = (MyviewHolder4) holder;

        Holder.btn_1.setText(text);


        }





    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
