package com.example.android.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


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
        final News news = mNewsList.get(i);
        ImageView thumbnail = viewHolder.thumbnail;
        Glide.with(mContext).load(news.getImageUrl()).into(thumbnail);
        viewHolder.sectionName.setText(news.getSection());
        viewHolder.headline.setText(news.getTitle());
        if(news.hasAuthor()){
            viewHolder.author.setText("by "+news.getAuthor());
        }
        viewHolder.dateAndTime.setText(news.getDate());
        viewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri newsUri = Uri.parse(news.getGuardianUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                mContext.startActivity(websiteIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.listItemCardView) CardView parentView;
        @BindView(R.id.thumbnail) ImageView thumbnail;
        @BindView(R.id.headline) TextView headline;
        @BindView(R.id.author) TextView author;
        @BindView(R.id.sectionName) TextView sectionName;
        @BindView(R.id.dateAndTime) TextView dateAndTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

