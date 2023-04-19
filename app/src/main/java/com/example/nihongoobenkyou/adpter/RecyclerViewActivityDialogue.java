package com.example.nihongoobenkyou.adpter;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.Interfaces.InterfaceAnswerAndItens;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.classes.Word;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RecyclerViewActivityDialogue extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    List<Pair<String,ArrayList>> list;

    public class ViewHolderSpeechLeft extends RecyclerView.ViewHolder{
        ImageButton imageButton;
        TextView textView;

        public ViewHolderSpeechLeft(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.btnAudio);
            textView = itemView.findViewById(R.id.txtdialogue);


        }
    }
    public class ViewHolderSpeechRight extends RecyclerView.ViewHolder{
        ImageButton imageButton;
        TextView textView;

        public ViewHolderSpeechRight(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.btnAudio2);
            textView = itemView.findViewById(R.id.txtdialogue2);


        }


    }
    public class ViewHolderAudio extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        MediaPlayer mediaPlayer;
        ProgressBar progressBar;

        public ViewHolderAudio(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.btnAudio3);

            mediaPlayer = MediaPlayer.create(itemView.getContext(), R.raw.feala);
            progressBar = itemView.findViewById(R.id.seekBar);

            progressBar.setMax(mediaPlayer.getDuration());

            new Thread(() -> {
                while (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    progressBar.setProgress(currentPosition);
                }
            }).start();
            mediaPlayer.start();

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mediaPlayer.isPlaying())
                        mediaPlayer.seekTo(0);

                    mediaPlayer.start();

                }
            });

        }
    }
    public class ViewQuestuion_version_1 extends RecyclerView.ViewHolder{

        Button[] buttons = new Button[5];
        String CorrectResponse = "私はAnnaですおろしくおねがいしまいす";

        public ViewQuestuion_version_1(@NonNull View itemView) {
            super(itemView);

            buttons[0] = itemView.findViewById(R.id.Response_1);
            buttons[1] = itemView.findViewById(R.id.Response_2);
            buttons[2] = itemView.findViewById(R.id.Response_3);
            buttons[3] = itemView.findViewById(R.id.Response_4);
            buttons[4] = itemView.findViewById(R.id.Response_5);


        }
    }
    public class ViewQuestuion_version_2 extends RecyclerView.ViewHolder implements InterfaceAnswerAndItens {
        RecyclerView rcyitens;
        RecyclerViewItens adpterItens;
        TextView textView;
        String TextAnswer;
        Spannable spannable;

        List<String> itens = new ArrayList<>();
        List<Word> words = new ArrayList<>();

        @SuppressLint("ResourceType")
        public ViewQuestuion_version_2(@NonNull View itemView) {
            super(itemView);

            rcyitens = itemView.findViewById(R.id.itens);
            textView = itemView.findViewById(R.id.answer);

            itens.add("watashi");
            itens.add("ha");
            itens.add("roku");
            itens.add("sakura");
            itens.add("boku");
            itens.add("wo");
            itens.add("Anna");
            itens.add("yoroshiku onegaishimasu");
            itens.add("arigatou");
            itens.add("sensei");
            itens.add("-san");
            itens.add("Nihon");

            FlexboxItemDecoration itemDecoration = new FlexboxItemDecoration(itemView.getContext());
            itemDecoration.setOrientation(FlexboxItemDecoration.BOTH);
            itemDecoration.setDrawable(itemView.getResources().getDrawable(R.drawable.linha));

            FlexboxLayoutManager flexboxLayoutManagerItens = new FlexboxLayoutManager(itemView.getContext());
            flexboxLayoutManagerItens.setFlexDirection(FlexDirection.ROW);
            flexboxLayoutManagerItens.setFlexWrap(FlexWrap.WRAP);
            flexboxLayoutManagerItens.setJustifyContent(JustifyContent.SPACE_EVENLY);

            adpterItens = new RecyclerViewItens(itens , this);

            rcyitens.setAdapter(adpterItens);
            rcyitens.setLayoutManager(flexboxLayoutManagerItens);
            rcyitens.addItemDecoration(itemDecoration);

        }

        @Override
        public void inputAnswer(Word word) {

            word.setStartPosition(word.getLastLetter());
            word.setEndPosition(word.getLastLetter() + word.getLenght());

            Log.i("Valores", " \ntexto: "  + word.getWord() + "\nLength: " + word.getLenght() + "\nStrat: " + word.getStartPosition() + "\nEnd: " + word.getEndPosition()
                 + "\nLast: " + word.getLastLetter());

            words.add(word);

            List<String> Myanswer = new ArrayList<>();

            for (Word each: words) {
                Myanswer.add(each.getWord());
            }

            TextAnswer = TextUtils.join(" ",Myanswer);

            spannable = new SpannableString(TextAnswer);

            for (Word each: words) {
                CustomClickableSpan clickableSpan = new CustomClickableSpan(words.indexOf(each));
                spannable.setSpan(clickableSpan,each.getStartPosition(),each.getEndPosition(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            textView.setText(spannable);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setSelected(true);

        }
        @Override
        public void changeAnswer(int position) {

            unInpuntAnswer(words.get(position).getPosition());
            words.remove(position);

            Word.setLastLetter(0);

            for (int i = 0; i < words.size(); i++) {
                words.get(i).setStartPosition(words.get(i).getLastLetter());
                words.get(i).setEndPosition(words.get(i).getLastLetter() + words.get(i).getLenght());
            }

            List<String> Myanswer = new ArrayList<>();

            for (Word each: words) {
                Myanswer.add(each.getWord());
            }

            TextAnswer = TextUtils.join(" ",Myanswer);

            spannable = new SpannableString(TextAnswer);

            for (Word each: words) {
                CustomClickableSpan clickableSpan = new CustomClickableSpan(words.indexOf(each));
                spannable.setSpan(clickableSpan,each.getStartPosition(),each.getEndPosition(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }


            textView.setText(spannable);

        }

        public void unInpuntAnswer(int index) {

            RecyclerViewItens.ViewHolderItens viewHolder = (RecyclerViewItens.ViewHolderItens) rcyitens.findViewHolderForAdapterPosition(index);

            viewHolder.textView.setBackground(itemView.getResources().getDrawable(R.drawable.button_activty));
            viewHolder.textView.setTextColor(Color.BLACK);
            viewHolder.Onclick = true;

        }
        public class CustomClickableSpan extends ClickableSpan {
            private int position;

            public CustomClickableSpan(int position) {
                this.position = position;

            }

            @Override
            public void onClick(@NonNull View view) {
                // Use o valor do parâmetro aqui
                changeAnswer(position);
            }

        }

    }
    public RecyclerViewActivityDialogue( List<Pair<String,ArrayList>> list){
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType){
            case 0:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_1, parent, false);

                return new ViewHolderSpeechRight(itemView);
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_2, parent, false);

                return new ViewHolderSpeechLeft(itemView);
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_3, parent, false);
                return  new ViewHolderAudio(itemView);
            case 3:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_4, parent, false);
                return  new ViewQuestuion_version_1(itemView);

            case 4:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_5, parent, false);
                return new ViewQuestuion_version_2(itemView);
            default:
                return null;
        }

    }
    @Override
    public int getItemViewType(int position) {

        switch (list.get(position).first){
            case "SpeechLeft":
                return 0;
            case "SpeechRight":
                return 1;
            case "Audio":
                return 2;
            case "Question_version_1":
                return 3;
            case "Question_version_2":
                return 4;
            default:
                return -1;

        }

    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case 0:
                ViewHolderSpeechRight Holder = (ViewHolderSpeechRight) holder;
                break;
            case 1:
                ViewHolderSpeechLeft Holder2 = (ViewHolderSpeechLeft) holder;
                break;
            case 2:
                ViewHolderAudio Holder3 = (ViewHolderAudio) holder;
                break;
            case 3:
                ViewQuestuion_version_1 Holder4 = (ViewQuestuion_version_1) holder;
                for(byte i = 0; i < 5;i++){
                    Holder4.buttons[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (byte i = 0; i < 5;i++){
                                if(Holder4.buttons[i].getId() == view.getId() ){

                                    if(Holder4.buttons[i].getText().equals(Holder4.CorrectResponse))
                                        Toast.makeText(Holder4.itemView.getContext(), "Correto", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(Holder4.itemView.getContext(), "Errado", Toast.LENGTH_SHORT).show();

                                }

                            }
                        }
                    });

                }
                break;
            case 4:

                ViewQuestuion_version_2 Holder5 = (ViewQuestuion_version_2) holder;

                break;

            default:

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
