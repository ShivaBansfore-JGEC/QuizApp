package com.example.shiva.quizapp1;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shiva on 9/29/2018.
 */

public class rv_category extends RecyclerView.Adapter<rv_category.ViewHolder> {
    Context context;
ArrayList<rv_model> list=new ArrayList<>();
    private onItemClickListener mlistener;
    public interface onItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        mlistener=listener;
    }

    public rv_category(Context context,ArrayList<rv_model> list) {
        this.list = list;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.rv_cartview,parent,false);
        return new ViewHolder(view,mlistener);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());
        holder.iv.setImageResource(list.get(position).getImage_id());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv;
        TextView tv;
        public ViewHolder(View itemView,final onItemClickListener listener) {
            super(itemView);
            iv=(CircleImageView)itemView.findViewById(R.id.rv_img);
            tv=(TextView) itemView.findViewById(R.id.rv_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null)
                    {
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });


        }


    }
}
