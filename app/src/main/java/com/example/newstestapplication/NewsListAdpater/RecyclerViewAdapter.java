package com.example.newstestapplication.NewsListAdpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newstestapplication.News;
import com.example.newstestapplication.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    public ArrayList<News> item=new ArrayList<>();
    int pos;
    private Context context;
    private onItemClicked listener;
    public RecyclerViewAdapter(Context context, onItemClicked listener) {
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_news,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        News news=item.get(position);
        pos=position;
        holder.title.setText(news.title);
        holder.author.setText(news.author);
        Glide.with(holder.itemView.getContext()).load(news.urlToImage).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
     public void update(ArrayList<News> update){
        item.clear();
        item.addAll(update);
        notifyDataSetChanged();
     }
public interface onItemClicked{
        void onClicked(News item);
    }

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView image;
    public TextView author;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.title);
        image=itemView.findViewById(R.id.image);
        author=itemView.findViewById(R.id.author);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClicked(item.get(getAdapterPosition()));
            }
        });
    }

}
}
