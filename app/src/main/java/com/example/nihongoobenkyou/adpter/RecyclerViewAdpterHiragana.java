package com.example.nihongoobenkyou.adpter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.R;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdpterHiragana extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<List<String>> list = new ArrayList<>();

    public class MyviewHolder extends RecyclerView.ViewHolder{

        Controller controller;
        Context  context;
        Button[] button = new Button[5];
        MediaPlayer[] mediaPlayers = new MediaPlayer[5];
        public MyviewHolder(View itemView) {
            super(itemView);

            controller = new Controller(itemView.getContext());
            context = itemView.getContext();

            button[0] = itemView.findViewById(R.id.button);
            button[1] = itemView.findViewById(R.id.button2);
            button[2] = itemView.findViewById(R.id.button3);
            button[3] = itemView.findViewById(R.id.button4);
            button[4] = itemView.findViewById(R.id.button5);

        }
    }
    public class MyviewHolder2 extends RecyclerView.ViewHolder{
        Button[] buttons = new Button[3];

        public MyviewHolder2(View itemView) {
            super(itemView);

            buttons[0] = itemView.findViewById(R.id.button6);
            buttons[1] = itemView.findViewById(R.id.button7);
            buttons[2] = itemView.findViewById(R.id.button8);
        }
    }
    public class MyviewHolder3 extends RecyclerView.ViewHolder{
        Button[] buttons = new Button[2];

        public MyviewHolder3(View itemView) {
            super(itemView);

            buttons[0] = itemView.findViewById(R.id.button9);
            buttons[1] = itemView.findViewById(R.id.button10);
        }
    }
    public class MyviewHolder4 extends RecyclerView.ViewHolder{
        Button button;

        public MyviewHolder4(View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.button11);

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
            String[] string = new String[5];

            for(int i =0; i < 5;i++)
                string[i] = this.list.get(position).get(i).toString();

            MyviewHolder Holder = (MyviewHolder) holder;

            for (int i = 0; i < 5; i++)
                Holder.button[i].setText(string[i]);


            for (int i = 0;i < 5;i++){
                Holder.mediaPlayers[i] = MediaPlayer.create(Holder.context,getAudio(string[0]));
                Holder.button[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Holder.mediaPlayers[2].start();
                    }
                });
            }


        }else if(holder.getItemViewType() == 3){

            String[] string = new String[5];

            for(int i =0; i < 3;i++)
                string[i] = this.list.get(position).get(i).toString();

            MyviewHolder2 Holder = (MyviewHolder2) holder;

            for (int i = 0; i < 3; i++)
                Holder.buttons[i].setText(string[i]);

        }else if(holder.getItemViewType() == 2){

            String[] strings = new String[2];

            strings[0] = this.list.get(position).get(0).toString();
            strings[1] = this.list.get(position).get(1).toString();


            MyviewHolder3 Holder = (MyviewHolder3) holder;

            Holder.buttons[0].setText(strings[0]);
            Holder.buttons[1].setText(strings[1]);

        } else if(holder.getItemViewType() == 1){
        String text = this.list.get(position).get(0).toString();

        MyviewHolder4 Holder = (MyviewHolder4) holder;

        Holder.button.setText(text);


        }


    }
    private int getAudio(String text){

        if(text.matches("お"))
            return R.raw.i;
    return R.raw.a;
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
