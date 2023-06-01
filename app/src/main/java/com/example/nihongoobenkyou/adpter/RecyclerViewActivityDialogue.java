package com.example.nihongoobenkyou.adpter;


import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
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

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class RecyclerViewActivityDialogue extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    List<Inters_of_dialogues> list;
    AssetManager assetManager;
    AssetFileDescriptor assetFileDescriptor;

    public RecyclerViewActivityDialogue( List<Inters_of_dialogues> list){
        this.list = list;

    }

    public class ViewHolderSpeechLeft extends RecyclerView.ViewHolder{
        ImageButton imageButton;
        TextView textView;
        MediaPlayer mediaPlayer;

        public ViewHolderSpeechLeft(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.btnAudio);
            textView = itemView.findViewById(R.id.txtdialogue);

            assetManager = itemView.getContext().getAssets();

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.start();
                }
            });


        }
    }
    public class ViewHolderSpeechRight extends RecyclerView.ViewHolder{
        ImageButton imageButton;
        TextView textView;
        MediaPlayer mediaPlayer;

        public ViewHolderSpeechRight(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.btnAudio2);
            textView = itemView.findViewById(R.id.txtdialogue2);

            assetManager = itemView.getContext().getAssets();

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.start();
                }
            });

        }

    }
    public class ViewHolderAudio extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        MediaPlayer mediaPlayer;
        ProgressBar progressBar;

        public ViewHolderAudio(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.btnAudio3);

            progressBar = itemView.findViewById(R.id.seekBar);
            assetManager = itemView.getContext().getAssets();


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


            if(TextAnswer.replaceAll("\\s", "").equals(CorrectAnswer.replaceAll("\\s","")))
                Toast.makeText(itemView.getContext(), "Correto", Toast.LENGTH_SHORT).show();

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

                return new ViewHolderSpeechLeft(itemView);
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_dialogue_version_2, parent, false);

                return new ViewHolderSpeechRight(itemView);
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

        Log.i("Tipo", "" + list.get(position).getType());

        switch (list.get(position).getType()){
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
                ViewHolderSpeechLeft HolderSpechLeft = (ViewHolderSpeechLeft) holder;

                HolderSpechLeft.textView.setText( ((Speech) list.get(position)).getText() );
                HolderSpechLeft.mediaPlayer = new MediaPlayer();

                try {
                    assetFileDescriptor = assetManager.openFd(((Speech) list.get(HolderSpechLeft.getAdapterPosition())).getAudio());
                    HolderSpechLeft.mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    HolderSpechLeft.mediaPlayer.prepare();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 1:
                ViewHolderSpeechRight HolderSpechRight = (ViewHolderSpeechRight) holder;

                HolderSpechRight.textView.setText(((Speech) list.get(position)).getText());
                HolderSpechRight.mediaPlayer = new MediaPlayer();

                try {
                    assetFileDescriptor = assetManager.openFd(((Speech) list.get(HolderSpechRight.getAdapterPosition())).getAudio());
                    HolderSpechRight.mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    HolderSpechRight.mediaPlayer.prepare();


                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case 2:
                ViewHolderAudio HolderAudio = (ViewHolderAudio) holder;

                HolderAudio.mediaPlayer = new MediaPlayer();

                try {
                    assetFileDescriptor = assetManager.openFd( ((Audio) list.get(HolderAudio.getAdapterPosition())).getAudio() );
                    HolderAudio.mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    HolderAudio.mediaPlayer.prepare();
                    HolderAudio.progressBar.setMax(HolderAudio.mediaPlayer.getDuration());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Thread thread = new Thread(() -> {
                    while (HolderAudio.mediaPlayer != null && !Thread.currentThread().isInterrupted() ) {
                        int currentPosition = HolderAudio.mediaPlayer.getCurrentPosition();
                        HolderAudio.progressBar.setProgress(currentPosition);
                        Log.i( "Valor", "Valor: " + currentPosition  + " / "+ HolderAudio.mediaPlayer.getDuration() );
                    }

                });
                thread.start();

                HolderAudio.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        thread.interrupt();
                        HolderAudio.progressBar.setProgress(HolderAudio.mediaPlayer.getDuration());
                    }
                });

                HolderAudio.mediaPlayer.start();

                break;
            case 3:
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

                                if(HolderQuestionVersion.buttons[view.getId()].getText().equals(((Question_version_1) list.get(HolderQuestionVersion.getAdapterPosition())).getCorrectAnswer()))
                                Toast.makeText(HolderQuestionVersion.itemView.getContext(), "Correto", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(HolderQuestionVersion.itemView.getContext(), "Errado", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                break;
            case 4:

                ViewQuestuion_version_2 HolderQuestionVersion2 = (ViewQuestuion_version_2) holder;

                HolderQuestionVersion2.itens.addAll(( (Question_version_2) list.get(position)).getWords());

                HolderQuestionVersion2.CorrectAnswer =  ((Question_version_2) list.get(position)).getCorrectAnswer() ;

                break;

            default:

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
