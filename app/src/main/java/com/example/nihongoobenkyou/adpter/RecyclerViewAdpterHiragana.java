package com.example.nihongoobenkyou.adpter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.HardwarePropertiesManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
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
        MediaPlayer mediaPlayer;
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
        MediaPlayer mediaPlayer;

        public MyviewHolder2(View itemView) {
            super(itemView);

            buttons[0] = itemView.findViewById(R.id.button6);
            buttons[1] = itemView.findViewById(R.id.button7);
            buttons[2] = itemView.findViewById(R.id.button8);
        }
    }
    public class MyviewHolder3 extends RecyclerView.ViewHolder{
        Button[] buttons = new Button[2];
        MediaPlayer mediaPlayer;


        public MyviewHolder3(View itemView) {
            super(itemView);

            buttons[0] = itemView.findViewById(R.id.button9);
            buttons[1] = itemView.findViewById(R.id.button10);
        }
    }
    public class MyviewHolder4 extends RecyclerView.ViewHolder{
        Button button;
        MediaPlayer mediaPlayer;

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
            SpannableString[] SpannableString = new SpannableString[5];

            for(byte i =0; i < 5;i++) {
                SpannableString[i]  = new SpannableString(this.list.get(position).get(i).replace("\\n","\n"));
                SpannableString[i].setSpan(
                        new RelativeSizeSpan(0.5f),
                        2,
                        SpannableString[i].length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

            MyviewHolder Holder = (MyviewHolder) holder;

            for (byte i = 0; i < 5; i++)
                Holder.button[i].setText(SpannableString[i]);

            for (byte i = 0;i < 5;i++){ ;
                Holder.button[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(Holder.mediaPlayer != null)
                            Holder.mediaPlayer.release();

                        for (byte i = 0; i < 5; i++)
                            if(Holder.button[i].getId() == view.getId() )
                                Holder.mediaPlayer = MediaPlayer.create(view.getContext(), getAudio(SpannableString[i].toString().substring(0,1)));

                        Holder.mediaPlayer.start();

                    }
                });
            }


        }else if(holder.getItemViewType() == 3){

            SpannableString[] SpannableString = new SpannableString[3];

            for(byte i =0; i < 3;i++) {
                SpannableString[i] = new SpannableString(this.list.get(position).get(i).replace("\\n", "\n"));
                SpannableString[i].setSpan(
                        new RelativeSizeSpan(0.5f),
                        2,
                        SpannableString[i].length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            MyviewHolder2 Holder = (MyviewHolder2) holder;

            for (byte i = 0; i < 3; i++)
                Holder.buttons[i].setText(SpannableString[i]);

            for (byte i = 0;i < 3;i++){ ;
                Holder.buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(Holder.mediaPlayer != null)
                            Holder.mediaPlayer.release();

                        for (byte i = 0; i < 3; i++)
                            if(Holder.buttons[i].getId() == view.getId() )
                                Holder.mediaPlayer = MediaPlayer.create(view.getContext(), getAudio(SpannableString[i].toString().substring(0,1)));

                        Holder.mediaPlayer.start();

                    }
                });
            }

        }else if(holder.getItemViewType() == 2){

            SpannableString[] SpannableString = new SpannableString[2];

            SpannableString[0] = new SpannableString ( this.list.get(position).get(0).replace("\\n", "\n") );
            SpannableString[0].setSpan(
                    new RelativeSizeSpan(0.5f),
                    2,
                    SpannableString[0].length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            SpannableString[1] = new SpannableString ( this.list.get(position).get(1).replace("\\n", "\n") );
            SpannableString[1].setSpan(
                    new RelativeSizeSpan(0.5f),
                    2,
                    SpannableString[1].length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            MyviewHolder3 Holder = (MyviewHolder3) holder;

            Holder.buttons[0].setText(SpannableString[0]);
            Holder.buttons[1].setText(SpannableString[1]);

            Holder.buttons[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Holder.mediaPlayer != null)
                        Holder.mediaPlayer.release();
                    if(Holder.buttons[0].getId() == view.getId())
                        Holder.mediaPlayer = MediaPlayer.create(view.getContext(), getAudio(SpannableString[0].toString().substring(0,1)));
                    else
                        Holder.mediaPlayer = MediaPlayer.create(view.getContext(), getAudio(SpannableString[1].toString().substring(0,1)));

                    Holder.mediaPlayer.start();

                }
            });
            Holder.buttons[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Holder.mediaPlayer != null)
                        Holder.mediaPlayer.release();

                    if(Holder.mediaPlayer != null)
                        Holder.mediaPlayer.release();
                    if(Holder.buttons[1].getId() == view.getId())
                        Holder.mediaPlayer = MediaPlayer.create(view.getContext(), getAudio(SpannableString[1].toString().substring(0,1)));
                    else
                        Holder.mediaPlayer = MediaPlayer.create(view.getContext(), getAudio(SpannableString[0].toString().substring(0,1)));

                    Holder.mediaPlayer.start();

                }
            });



        } else if(holder.getItemViewType() == 1){

            SpannableString text = new SpannableString ( this.list.get(position).get(0).replace("\\n", "\n"));
            text.setSpan(
                    new RelativeSizeSpan(0.5f),
                    2,
                    text.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            MyviewHolder4 Holder = (MyviewHolder4) holder;

            Holder.button.setText(text);

            Holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Holder.mediaPlayer != null)
                        Holder.mediaPlayer.release();

                    Holder.mediaPlayer = MediaPlayer.create(view.getContext(), getAudio(text.toString().substring(0,1)));

                    Holder.mediaPlayer.start();

                }
            });

        }

    }
    private int getAudio(String text ){

        switch (text){
            case "あ":
                return R.raw.a;
            case "い":
                return R.raw.i;
            case "う":
                return R.raw.u;
            case "え":
                return R.raw.e;
            case"お":
                return R.raw.o;
            case"か":
                return R.raw.ka;
            case"き":
                return R.raw.ki;
            case"く":
                return R.raw.ku;
            case"け":
                return R.raw.ke;
            case"こ":
                return R.raw.ko;
            case"さ":
                return R.raw.sa;
            case"し":
                return R.raw.shi;
            case"す":
                return R.raw.su;
            case"せ":
                return R.raw.se;
            case"そ":
                return R.raw.so;
            case"た":
                return R.raw.ta;
            case"ち":
                return R.raw.chi;
            case"つ":
                return R.raw.tsu;
            case"て":
                return R.raw.te;
            case"と":
                return R.raw.to;
            case"な":
                return R.raw.na;
            case"に":
                return R.raw.ni;
            case"ぬ":
                return R.raw.nu;
            case"ね":
                return R.raw.ne;
            case"の":
                return R.raw.no;
            case"は":
                return R.raw.ha;
            case"ひ":
                return R.raw.hi;
            case"ふ":
                return R.raw.fu;
            case"へ":
                return R.raw.he;
            case"ほ":
                return R.raw.ho;
            case"ま":
                return R.raw.ma;
            case"み":
                return R.raw.mi;
            case"む":
                return R.raw.mu;
            case"め":
                return R.raw.me;
            case"も":
                return R.raw.mo;
            case"や":
                return R.raw.ya;
            case"ゆ":
                return R.raw.yu;
            case"よ":
                return R.raw.yo;
            case"ら":
                return R.raw.ra;
            case"り":
                return R.raw.ri;
            case"る":
                return R.raw.ru;
            case"れ":
                return R.raw.re;
            case"ろ":
                return R.raw.ro;
            case"わ":
                return R.raw.wa;
            case"を":
                return R.raw.wo;
            case"ん":
                return R.raw.n;
            default:
                return R.raw.n;

        }


    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
