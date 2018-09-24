package com.example.android.newsapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

//TODO: Find the most recent type of LoaderManager

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{
    private static final String TAG = "MainActivity";
    private static final String GUARDIAN_QUERY_URL = "http://content.guardianapis.com/world?show-most-viewed=true&page-size=1&show-fields=headline,thumbnail&show-tags=contributor&api-key=ef581bc8-daf3-4727-9d6d-67623f4f80d9";
    private static final int NEWS_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connMgr.getActiveNetworkInfo();
        if(network != null && network.isConnected()){

        }


    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsLoader(MainActivity.this, GUARDIAN_QUERY_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsList) {
        Log.d(TAG, "onLoadFinished: GOT THE LIST!!!! LIST SIZE: "+newsList.size());
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}
