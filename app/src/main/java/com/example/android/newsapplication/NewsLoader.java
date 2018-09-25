package com.example.android.newsapplication;

import android.content.Context;

import android.content.AsyncTaskLoader;


import android.text.TextUtils;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>>{
    private static final String TAG = "NewsLoader";
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if(TextUtils.isEmpty(mUrl)){
            return null;
        }
        List<News> newsList = QueryUtils.fetchNewsData(mUrl);
        return newsList;
    }
}
