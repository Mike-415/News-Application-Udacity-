package com.example.android.newsapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;



public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{
    private static final String TAG = "MainActivity";
    private static final String GUARDIAN_QUERY_URL = "http://content.guardianapis.com/world?show-most-viewed=true&show-fields=headline,thumbnail&show-tags=contributor&api-key=ef581bc8-daf3-4727-9d6d-67623f4f80d9";
    private static final int NEWS_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connMgr.getActiveNetworkInfo();
        if(network != null && network.isConnected()){
            getLoaderManager().initLoader(NEWS_LOADER_ID, null, MainActivity.this);
        }


    }

    @Override
    public android.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(MainActivity.this, GUARDIAN_QUERY_URL);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<News>> loader, List<News> newsList) {
        Log.d(TAG, "onLoadFinished: GOT THE LIST!!!! LIST SIZE: "+newsList.size());
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<News>> loader) {

    }
}


