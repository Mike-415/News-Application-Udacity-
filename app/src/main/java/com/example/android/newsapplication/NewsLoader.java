package com.example.android.newsapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>>{
    private static final String TAG = "NewsLoader";
    private String mUrl;

    public NewsLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        if(TextUtils.isEmpty(mUrl)){
            return null;
        }
        List<News> newsList = QueryUtils.fetchNewsData(mUrl);
        return newsList;
    }
}
