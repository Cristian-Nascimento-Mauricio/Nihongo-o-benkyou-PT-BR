package com.example.nihongoobenkyou.adpter;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.Controllers.Controller;
import com.example.nihongoobenkyou.R;


import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdpterHiragana extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<List<String>> list;

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
    public class MyviewHolder5 extends RecyclerView.ViewHolder{
        TextView textView;
        public MyviewHolder5(View itemView, String texto) {
            super(itemView);
            textView = itemView.findViewById(R.id.textviewKanaTitulo);
            textView.setText(texto);
        }
    }


    public RecyclerViewAdpterHiragana(List<List<String>> list){
        this.list = list;

    }

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case 0:
                return 0;
            case 12:
                return -1;
            default:
                return this.list.get(position).size();

        }

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
        }else if(viewType == 0) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_hiragana_version_5, parent, false);

            return new MyviewHolder5(itemView,"Seion");
        }else if(viewType < 0){

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_hiragana_version_5, parent, false);

            return new MyviewHolder5(itemView,"Dakuon");
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


        if(text.getBytes()[1] == -127){

            if(text.getBytes()[2] % 2 == 0 ) {

                switch (text) {
                    case "あ":
                        return R.raw.a;
                    case "い":
                        return R.raw.i;
                    case "う":
                        return R.raw.u;
                    case "え":
                        return R.raw.e;
                    case "お":
                        return R.raw.o;
                    case "つ":
                        return R.raw.tsu;
                    case "て":
                        return R.raw.te;
                    case "と":
                        return R.raw.to;
                    case "な":
                        return R.raw.na;
                    case "ぬ":
                        return R.raw.nu;
                    case "の":
                        return R.raw.no;
                    case "ひ":
                        return R.raw.hi;
                    case "へ":
                        return R.raw.he;
                    case"ま":
                        return R.raw.ma;
                    case"が":
                        return R.raw.ga;
                    case"ぎ":
                        return R.raw.gi;
                    case"ぐ":
                        return R.raw.gu;
                    case"げ":
                        return R.raw.ge;
                    case"ご":
                        return R.raw.go;
                    case"ざ":
                        return R.raw.za;
                    case"じ":
                        return R.raw.ji;
                    case"ず":
                        return R.raw.zu;
                    case"ぜ":
                        return R.raw.ze;
                    case"ぞ":
                        return R.raw.zo;
                    case"だ":
                        return R.raw.da;
                    case"ぢ":
                        return R.raw.ji_;
                    case"ば":
                        return R.raw.ba;
                    case"ぼ":
                        return R.raw.bo;
                    case"ぶ":
                        return R.raw.bu;
                    case "ぴ":
                        return R.raw.pi;
                    case "ぺ":
                        return R.raw.pe;
                    default:
                        return R.raw.n;

                }
            }else {
                switch (text){
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
                    case"に":
                        return R.raw.ni;
                    case"ね":
                        return R.raw.ne;
                    case"は":
                        return R.raw.ha;
                    case"ふ":
                        return R.raw.fu;
                    case"ほ":
                        return R.raw.ho;
                    case"み":
                        return R.raw.mi;
                    case"カ":
                        return R.raw.ka;
                    case"キ":
                        return R.raw.ki;
                    case"ク":
                        return R.raw.ku;
                    case"ケ":
                        return R.raw.ke;
                    case"コ":
                        return R.raw.ko;
                    case"サ":
                        return R.raw.sa;
                    case"シ":
                        return R.raw.shi;
                    case"ス":
                        return R.raw.su;
                    case"セ":
                        return R.raw.se;
                    case"ソ":
                        return R.raw.so;
                    case"タ":
                        return R.raw.so;
                    case"づ":
                        return R.raw.zu_;
                    case"で":
                        return R.raw.de;
                    case"ど":
                        return R.raw.do_;
                    case"び":
                        return R.raw.bi;
                    case "べ":
                        return R.raw.be;
                    case "ぱ":
                        return R.raw.pa;
                    case "ぷ":
                        return R.raw.pu;
                    case "ぽ":
                        return R.raw.po;
                    default:
                        return R.raw.n;

                }

            }
        }else if(text.getBytes()[1] == -126){

            if(text.getBytes()[2] % 2 == 0 ) {

                switch (text) {
                    case "ア":
                        return R.raw.a;
                    case "イ":
                        return R.raw.i;
                    case "ウ":
                        return R.raw.u;
                    case "エ":
                        return R.raw.e;
                    case "オ":
                        return R.raw.o;
                    case "む":
                        return R.raw.mu;
                    case "も":
                        return R.raw.mo;
                    case "り":
                        return R.raw.ri;
                    case "れ":
                        return R.raw.re;
                    case "や":
                        return R.raw.ya;
                    case "ゆ":
                        return R.raw.yu;
                    case "よ":
                        return R.raw.yo;
                    case "を":
                        return R.raw.wo;
                    case "ガ" :
                        return R.raw.ga;
                    case "ギ" :
                        return R.raw.gi;
                    case "グ" :
                        return R.raw.gu;
                    case "ゲ" :
                        return R.raw.ge;
                    case "ゴ" :
                        return R.raw.go;
                    case "ザ" :
                        return R.raw.za;
                    case "ジ" :
                        return R.raw.ji;
                    case "ズ" :
                        return R.raw.zu;
                    case "ゼ" :
                        return R.raw.ze;
                    case "ゾ" :
                        return R.raw.zo;
                    default:
                        return R.raw.n;
                }

            }else{
                switch (text) {

                    case "め":
                        return R.raw.me;
                    case "ら":
                        return R.raw.ra;
                    case "る":
                        return R.raw.ru;
                    case "ろ":
                        return R.raw.ro;
                    case "わ":
                        return R.raw.wa;
                    case "ん":
                        return R.raw.n;
                    case  "カ" :
                        return R.raw.ka;
                    case  "キ" :
                        return R.raw.ki;
                    case  "ク" :
                        return R.raw.ku;
                    case  "ケ" :
                        return R.raw.ke;
                    case  "コ" :
                        return R.raw.ko;
                    case  "サ" :
                        return R.raw.sa;
                    case  "シ" :
                        return R.raw.shi;
                    case  "ス" :
                        return R.raw.su;
                    case  "セ" :
                        return R.raw.se;
                    case  "ソ" :
                        return R.raw.so;
                    case  "タ" :
                        return R.raw.ta;
                    default:
                        return R.raw.n;

                }

            }

        }else{

            if(text.getBytes()[2] % 2 == 0 ) {
                switch (text) {
                    case "ツ":
                        return R.raw.tsu;
                    case "テ":
                        return R.raw.te;
                    case "ト":
                        return R.raw.to;
                    case "ナ":
                        return R.raw.na;
                    case "ヌ":
                        return R.raw.nu;
                    case "ノ":
                        return R.raw.no;
                    case "ヒ":
                        return R.raw.hi;
                    case "ヘ":
                        return R.raw.he;
                    case "マ":
                        return R.raw.ma;
                    case "ム":
                        return R.raw.mu;
                    case "ヤ":
                        return R.raw.ya;
                    case "ユ":
                        return R.raw.yu;
                    case "ヨ":
                        return R.raw.yo;
                    case "リ":
                        return R.raw.ri;
                    case "レ":
                        return R.raw.re;
                    case "ヲ":
                        return R.raw.wo;
                    case "ダ":
                        return R.raw.da;
                    case "ヂ":
                        return R.raw.ji;
                    case "バ":
                        return R.raw.ba;
                    case "ブ":
                        return R.raw.bu;
                    case "ピ":
                        return R.raw.pi;
                    case "ペ":
                        return R.raw.pe;
                    default:
                        return R.raw.n;

                    }
                }else {

                switch (text) {
                    case "チ":
                        return R.raw.chi;
                    case "ニ":
                        return R.raw.ni;
                    case "ネ":
                        return R.raw.ne;
                    case "ハ":
                        return R.raw.ha;
                    case "フ":
                        return R.raw.fu;
                    case "ホ":
                        return R.raw.ho;
                    case "ミ":
                        return R.raw.mi;
                    case "メ":
                        return R.raw.me;
                    case "ラ":
                        return R.raw.ra;
                    case "ル":
                        return R.raw.ru;
                    case "ロ":
                        return R.raw.ro;
                    case "ワ":
                        return R.raw.wa;
                    case "ン":
                        return R.raw.n;
                    case "ヅ":
                        return R.raw.zu;
                    case "デ":
                        return R.raw.de;
                    case "ド":
                        return R.raw.do_;
                    case "ビ":
                        return R.raw.bi;
                    case "ベ":
                        return R.raw.be;
                    case "パ":
                        return R.raw.pa;
                    case "プ":
                        return R.raw.pu;
                    case "ポ":
                        return R.raw.po;
                    default:
                        return R.raw.n;

                }

            }
        }
    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
