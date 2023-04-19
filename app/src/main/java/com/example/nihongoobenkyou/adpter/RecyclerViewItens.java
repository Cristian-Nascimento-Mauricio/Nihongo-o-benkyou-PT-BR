package com.example.nihongoobenkyou.adpter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.Interfaces.InterfaceAnswerAndItens;
import com.example.nihongoobenkyou.R;
import com.example.nihongoobenkyou.classes.Word;

import java.util.List;

public class RecyclerViewItens extends RecyclerView.Adapter<RecyclerViewItens.ViewHolderItens> {

    private List<String> list;
    private InterfaceAnswerAndItens interfaceAnswerAndItens;

    public RecyclerViewItens(List<String> texts , InterfaceAnswerAndItens interfaceAnswerAndItens) {
        this.list = texts;
        this.interfaceAnswerAndItens = interfaceAnswerAndItens;
    }

    public class ViewHolderItens extends RecyclerView.ViewHolder{
        Boolean Onclick = true;
        TextView textView;

        public ViewHolderItens(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txtview);

        }

    }

    @NonNull
    @Override
    public ViewHolderItens onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_textview,parent,false);

        return new ViewHolderItens(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItens holder, int position) {

        holder.textView.setText( "  " +  list.get(position) + "  " );



        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.Onclick) {
                    holder.textView.setBackground(holder.itemView.getResources().getDrawable(R.drawable.space_gray));
                    holder.textView.setTextColor(Color.TRANSPARENT);
                    interfaceAnswerAndItens.inputAnswer(new Word(holder.textView.getText().toString().trim() ,  holder.getAdapterPosition()));

                    holder.Onclick = false;
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
