package com.example.nihongoobenkyou.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongoobenkyou.Interfaces.InterfaceAnswerAndItens;
import com.example.nihongoobenkyou.R;

import java.util.List;

public class RecyclerViewAnswer extends RecyclerView.Adapter<RecyclerViewAnswer.ViewHolderAnswer> {

    private List<String> list;
    private InterfaceAnswerAndItens interfaceAnswerAndItens;

    public RecyclerViewAnswer(List<String> answer , InterfaceAnswerAndItens interfaceAnswerAndItens) {
        this.list = answer;
        this.interfaceAnswerAndItens = interfaceAnswerAndItens;
    }


    public class ViewHolderAnswer extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolderAnswer(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txtview);
        }
    }

    @NonNull
    @Override
    public ViewHolderAnswer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_textview_2, parent, false);

        return new ViewHolderAnswer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAnswer.ViewHolderAnswer holder, int position) {
        holder.textView.setText(list.get(position));

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(),holder.textView.getText(), Toast.LENGTH_SHORT).show();
                interfaceAnswerAndItens.unInpuntAnswer((String) holder.textView.getText());

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
