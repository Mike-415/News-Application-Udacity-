package com.example.android.newsapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private List<News> mNewsList;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<News> newsList){
        mContext = context;
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        News news = mNewsList.get(i);
        //We can use Picasso and Butterknife, just as long as it has nothing to do with networking
        //TODO: Load image using Glide or Picasso
        viewHolder.thumbnail.setImageResource(R.drawable.ic_launcher_foreground);
        viewHolder.sectionName.setText(news.getSection());
        viewHolder.headline.setText(news.getTitle());
        viewHolder.author.setText(news.getAuthor());
        viewHolder.dateAndTime.setText(news.getDate());
        viewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Create another field in the News class for the Guardian URL
                //TODO: Create an intent for the Guardian URL
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parentView;
        private ImageView thumbnail;
        private TextView sectionName, headline, author, dateAndTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentView = itemView.findViewById(R.id.listItemCardView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            sectionName = itemView.findViewById(R.id.sectionName);
            headline = itemView.findViewById(R.id.headline);
            author = itemView.findViewById(R.id.author);
            dateAndTime = itemView.findViewById(R.id.dateAndTime);


        }
    }
}
