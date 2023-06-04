package com.example.nihongoobenkyou.adpter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.Interfaces.InterfaceAnswerAndItens;
import com.example.nihongoobenkyou.Interfaces.InterfaceCondition;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Audio;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Inters_of_dialogues;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Question_version_1;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Question_version_2;
import com.example.nihongoobenkyou.classes.Inters_of_dialogues.Speech;
import com.example.nihongoobenkyou.classes.Word;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.transition.Hold;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;


public class RecyclerViewActivityDialogue extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Inters_of_dialogues> list;
    InterfaceCondition interfaceCondition;
    PlayerAudio playerAudio;

    public RecyclerViewActivityDialogue(List<Inters_of_dialogues> list , InterfaceCondition interfaceCondition, Context context){
        this.list = list;
        this.interfaceCondition = interfaceCondition;
        this.playerAudio = new PlayerAudio(context);

    }

    public class ViewHolderSpeech extends RecyclerView.ViewHolder{
        ImageButton imageButton;
        TextView textView;

        public ViewHolderSpeech(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.ButtonAudio);
            textView = itemView.findViewById(R.id.TextSpeech);


        }
    }

    public class ViewHolderAudio extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        ProgressBar progressBar;

        public ViewHolderAudio(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.btnAudio3);

            progressBar = itemView.findViewById(R.id.seekBar);

        }

    }
    public class ViewQuestuion_version_1 extends RecyclerView.ViewHolder{

        Button[] buttons = new Button[5];

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
        String CorrectAnswer;

        List<String> itens = new ArrayList<>();
        List<Word> words = new ArrayList<>();

        @SuppressLint("ResourceType")
        public ViewQuestuion_version_2(@NonNull View itemView) {
            super(itemView);

            rcyitens = itemView.findViewById(R.id.itens);
            textView = itemView.findViewById(R.id.answer);

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

            Toast.makeText(itemView.getContext(), TextAnswer.trim() + "\n" + CorrectAnswer.trim(), Toast.LENGTH_LONG).show();

            if(TextAnswer.replaceAll("\\s", "").equals(CorrectAnswer.replaceAll("\\s",""))) {
                Toast.makeText(itemView.getContext(), "Correto", Toast.LENGTH_SHORT).show();
                interfaceCondition.Codition(true);
            }else
                interfaceCondition.Codition(false);

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
                spannable.setSpan(clickableSpan,each.getStartPosition(),each.getEndPosition(),Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            }

            textView.setText(spannable);

            if(TextAnswer.replaceAll("\\s", "").equals(CorrectAnswer.replaceAll("\\s",""))) {
                Toast.makeText(itemView.getContext(), "Correto", Toast.LENGTH_SHORT).show();
                interfaceCondition.Codition(true);
            }else
                interfaceCondition.Codition(false);

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

                changeAnswer(position);
            }

        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType){
            case 0:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_1, parent, false);
                return new ViewHolderSpeech(itemView);
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_2, parent, false);
                return new ViewHolderSpeech(itemView);
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_3, parent, false);
                return  new ViewHolderAudio(itemView);
            case 3:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_6, parent, false);
                return  new ViewHolderAudio(itemView);
            case 4:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_4, parent, false);
                return  new ViewQuestuion_version_1(itemView);
            case 5:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_5, parent, false);
                return new ViewQuestuion_version_2(itemView);
            default:
                return null;
        }

    }
    @Override
    public int getItemViewType(int position) {

        Log.i("Tipo", "" + list.get(position).getType());

        switch (list.get(position).getType()){
            case "SpeechLeft":
                return 0;
            case "SpeechRight":
                return 1;
            case "AudioLeft":
                return 2;
            case "AudioRight":
                return 3;
            case "Question_version_1":
                return 4;
            case "Question_version_2":
                return 5;
            default:
                return -1;

        }

    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case 0:
                speech(holder);
                break;
            case 1:
                speech(holder);
                break;
            case 2:
                audio(holder);

                break;
            case 3:
                audio(holder);

                break;
            case 4:
                interfaceCondition.Codition(false);
                ViewQuestuion_version_1 HolderQuestionVersion = (ViewQuestuion_version_1) holder;

                List<String> answers = ( (Question_version_1) list.get(position)).getAnswers();

                for (byte i = 0; i < 5; i++) {
                    HolderQuestionVersion.buttons[i].setText(answers.get(i));
                    HolderQuestionVersion.buttons[i].setId(i);
                }

                for(byte i = 0; i < 5;i++){

                    HolderQuestionVersion.buttons[i].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                                if(HolderQuestionVersion.buttons[view.getId()].getText().equals(((Question_version_1) list.get(HolderQuestionVersion.getAdapterPosition())).getCorrectAnswer())) {
                                    interfaceCondition.Codition(true);
                                    Toast.makeText(HolderQuestionVersion.itemView.getContext(), "Correto", Toast.LENGTH_SHORT).show();
                                }
                            else {
                                    interfaceCondition.Codition(false);
                                    Toast.makeText(HolderQuestionVersion.itemView.getContext(), "Errado", Toast.LENGTH_SHORT).show();
                                }
                        }
                    });

                }
                break;
            case 5:
                interfaceCondition.Codition(false);
                ViewQuestuion_version_2 HolderQuestionVersion2 = (ViewQuestuion_version_2) holder;

                HolderQuestionVersion2.itens.addAll(( (Question_version_2) list.get(position)).getWords());

                HolderQuestionVersion2.CorrectAnswer =  ((Question_version_2) list.get(position)).getCorrectAnswer() ;

                break;

            default:

        }

    }
    public void speech(RecyclerView.ViewHolder holder){
        ViewHolderSpeech HolderSpech = (ViewHolderSpeech) holder;

        HolderSpech.textView.setText(((Speech) list.get(HolderSpech.getAdapterPosition())).getText());


        HolderSpech.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playerAudio.play(HolderSpech);
                HolderSpech.imageButton.setEnabled(false);

                }

        });

    }
    public void audio (RecyclerView.ViewHolder holder){

        ViewHolderAudio HolderAudio = (ViewHolderAudio) holder;

        playerAudio.playAudio(HolderAudio, HolderAudio.progressBar);

        HolderAudio.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerAudio.playAudio(HolderAudio,HolderAudio.progressBar);
               /*
                HolderAudio.imageButton.setEnabled(false);

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HolderAudio.imageButton.setEnabled(true);
                    }
                },5000);
*/
            }
        });



    }

    public class PlayerAudio extends MediaPlayer{
        AssetManager assetManager;
        AssetFileDescriptor assetFileDescriptor;
        Thread thread;
        int currentPosition;

        public PlayerAudio(Context context) {
            this.assetManager = context.getAssets();
        }

        public void play(RecyclerView.ViewHolder holder) {
            if(isPlaying())
                stop();

            reset();
            try {
                assetFileDescriptor = assetManager.openFd(((Speech) list.get(holder.getAdapterPosition())).getAudio());
                setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }

            start();
        }

        public void playAudio(RecyclerView.ViewHolder holder,ProgressBar progressBar){
            if (isPlaying()) {
                stop();
                progressBar.setProgress(0);
            }
            reset();

            try {
                assetFileDescriptor = assetManager.openFd( ((Audio) list.get(holder.getAdapterPosition())).getAudio() );
                setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                prepare();
                progressBar.setMax(getDuration());

            } catch (IOException e) {
                e.printStackTrace();
            }

            setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    thread.interrupt();
                    currentPosition = getDuration();
                    progressBar.setProgress(getDuration());
                    Log.i( "Valor", "" + currentPosition  );

                }
            });

            class MyRunnable implements Runnable {
                @Override
                public void run() {
                    currentPosition = 0;

                    while (currentPosition < getDuration()) {
                        currentPosition = getCurrentPosition();
                        progressBar.setProgress(currentPosition);
                        Log.i( "Valor", "" + getCurrentPosition() + " / " + getDuration());
                    }

                }
            }

            start();

            thread = new Thread(new MyRunnable());

            thread.start();

        }


    }
    @Override
    public int getItemCount() {
        return list.size();
    }


}
