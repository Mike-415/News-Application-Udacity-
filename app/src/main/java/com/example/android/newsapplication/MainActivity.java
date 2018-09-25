package com.example.android.newsapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.loadingIndicator)
    ProgressBar mLoadingIndicator;
    @BindView(R.id.errorImageView)
    ImageView mErrorImageView;
    private RecyclerViewAdapter mAdapter;
    private static final String TAG = "MainActivity";
    private static final String GUARDIAN_QUERY_URL = "http://content.guardianapis.com/world?show-most-viewed=true&show-fields=headline,thumbnail&show-tags=contributor&api-key=ef581bc8-daf3-4727-9d6d-67623f4f80d9";
    private static final int NEWS_LOADER_ID = 1;
    //private View.OnClickListener listItemListener =

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeRecyclerView(null);
        if(isInternetConnected()){
            getLoaderManager().initLoader(NEWS_LOADER_ID, null, MainActivity.this);
        }else{
            mLoadingIndicator.setVisibility(View.GONE);
            //mErrorImageView.setImageResource(R.drawable.no_internet_connection);

        }


    }

    @Override
    public android.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(MainActivity.this, GUARDIAN_QUERY_URL);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<News>> loader, List<News> newsList) {
        Log.d(TAG, "onLoadFinished: GOT THE LIST!!!! LIST SIZE: "+newsList.size());
        mLoadingIndicator.setVisibility(View.GONE);
        if(newsList != null && !newsList.isEmpty()){
            initializeRecyclerView(newsList);
        }

    }

    @Override
    public void onLoaderReset(android.content.Loader<List<News>> loader) {

    }

    private boolean isInternetConnected(){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void initializeRecyclerView(List<News> newsList ){
        if(newsList == null)
            newsList = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter(this, newsList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}


