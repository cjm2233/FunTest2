package com.example.funtest2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funtest2.R;
import com.example.funtest2.interfaces.OnItemClickListener;

import java.util.List;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 9:18
 */
public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{
    private List<String> list;
    private OnItemClickListener onItemTouchListener;
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_main);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemTouchListener != null) {
                        onItemTouchListener.onItemClicked(v,getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemTouchListener != null) {
                        onItemTouchListener.onItemClicked(v,getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    public MyAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s = list.get(position);
        holder.textView.setText(s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.onItemTouchListener = clickListener;
    }
    public void setList(List<String> list){
        this.list = list;
    }
}
