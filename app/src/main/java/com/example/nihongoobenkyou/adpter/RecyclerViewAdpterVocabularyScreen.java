package com.example.nihongoobenkyou.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.Interfaces.InterfaceDialogue;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.classes.Vocabulary_of_Vocabulary_Screen;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdpterVocabularyScreen extends RecyclerView.Adapter<RecyclerViewAdpterVocabularyScreen.MyViewHolder> {


    private List<Vocabulary_of_Vocabulary_Screen> list = new ArrayList<>();
    private InterfaceDialogue interfaceDialogue;
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView speech,speech2,speech3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_of_item);
            speech = itemView.findViewById(R.id.speech);
            speech2 = itemView.findViewById(R.id.speech2);
            speech3 = itemView.findViewById(R.id.speech3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfaceDialogue.openDialogueActivity("texto");
                }
            });
        }
    }

    public RecyclerViewAdpterVocabularyScreen(List<Vocabulary_of_Vocabulary_Screen> list, InterfaceDialogue interfaceDialogue){
        this.list = list;
        this.interfaceDialogue = interfaceDialogue;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_adpter_screen_version_1, parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Vocabulary_of_Vocabulary_Screen item = list.get(position);

        holder.title.setText(item.getTitle());
        holder.speech.setText(item.getSpeech());
        holder.speech2.setText(item.getSpeech2());
        holder.speech3.setText(item.getSpeech3());

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }



}
