package com.example.nihongoobenkyou.adpter;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.Interfaces.InterfaceAnswerAndItens;
import com.example.nihongoobenkyou.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
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
        RecyclerView rcyitens , rcyAnswer;
        RecyclerViewAnswer adpterAnswer;
        RecyclerViewItens adpterItens;

        List<String> itens = new ArrayList<>();
        List<String> answer = new ArrayList<>();

        @SuppressLint("ResourceType")
        public ViewQuestuion_version_2(@NonNull View itemView) {
            super(itemView);

            rcyitens = itemView.findViewById(R.id.itens);
            rcyAnswer = itemView.findViewById(R.id.answer);

            itens.add(" watashi ");
            itens.add(" ha ");
            itens.add(" roku ");
            itens.add(" sakura ");
            itens.add(" boku ");
            itens.add(" wo ");
            itens.add(" Anna ");
            itens.add(" yoroshiku onegaishimasu ");
            itens.add(" arigatou ");
            itens.add(" sensei ");
            itens.add(" -san ");
            itens.add(" Nihon ");

            FlexboxItemDecoration itemDecoration = new FlexboxItemDecoration(itemView.getContext());
            itemDecoration.setOrientation(FlexboxItemDecoration.BOTH);
            itemDecoration.setDrawable(itemView.getResources().getDrawable(R.drawable.linha));

            FlexboxLayoutManager flexboxLayoutManagerItens = new FlexboxLayoutManager(itemView.getContext());
            flexboxLayoutManagerItens.setFlexDirection(FlexDirection.ROW);
            flexboxLayoutManagerItens.setFlexWrap(FlexWrap.WRAP);
            flexboxLayoutManagerItens.setJustifyContent(JustifyContent.SPACE_EVENLY);

            FlexboxItemDecoration AnswerDecoration = new FlexboxItemDecoration(itemView.getContext());
            AnswerDecoration.setOrientation(FlexboxItemDecoration.BOTH);
            AnswerDecoration.setDrawable(itemView.getResources().getDrawable(R.drawable.linha));

            FlexboxLayoutManager flexboxLayoutManagerAnswer = new FlexboxLayoutManager(itemView.getContext());
            flexboxLayoutManagerAnswer.setFlexDirection(FlexDirection.ROW);
            flexboxLayoutManagerAnswer.setFlexWrap(FlexWrap.WRAP);
            flexboxLayoutManagerAnswer.setJustifyContent(JustifyContent.FLEX_START);

            adpterItens = new RecyclerViewItens(itens , this);
            adpterAnswer = new RecyclerViewAnswer(answer , this);

            rcyitens.setAdapter(adpterItens);
            rcyitens.setLayoutManager(flexboxLayoutManagerItens);
            rcyitens.addItemDecoration(itemDecoration);

            rcyAnswer.setAdapter(adpterAnswer);
            rcyAnswer.setLayoutManager(flexboxLayoutManagerAnswer);

        }

        @Override
        public void inputAnswer(String text) {
            answer.add(text);
            adpterAnswer.notifyDataSetChanged();

        }

        @Override
        public void unInpuntAnswer(String text) {

            RecyclerViewItens.ViewHolderItens viewHolder = (RecyclerViewItens.ViewHolderItens) rcyitens.findViewHolderForAdapterPosition(itens.indexOf(text));

            viewHolder.textView.setBackground(itemView.getResources().getDrawable(R.drawable.button_activty));
            viewHolder.textView.setTextColor(Color.BLACK);
            viewHolder.Onclick = true;
            answer.remove(answer.indexOf(text));
            adpterAnswer.notifyDataSetChanged();

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
