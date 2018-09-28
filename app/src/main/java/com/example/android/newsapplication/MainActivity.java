package com.example.android.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.app.LoaderManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.loadingIndicator)
    ProgressBar mLoadingIndicator;
    @BindView(R.id.errorImageView)
    ImageView mErrorImageView;
    @BindView(R.id.loadingIndicatorLogo)
    ImageView mLoadingIndicatorLogo;
    private RecyclerViewAdapter mAdapter;
    private static final String TAG = "MainActivity";
    //private static final String GUARDIAN_QUERY_URL = "http://content.guardianapis.com/world?show-most-viewed=true&show-fields=headline,thumbnail&show-tags=contributor&api-key="+BuildConfig.API_KEY;
    private static final String GUARDIAN_API_URL = "http://content.guardianapis.com/search";

    private static final int NEWS_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeRecyclerView(null);
        if(isInternetConnected()){
            Log.d(TAG, "onCreate: internetIsConnected");
            getLoaderManager().initLoader(NEWS_LOADER_ID, null, MainActivity.this);
        }else{
            mLoadingIndicatorLogo.setVisibility(View.GONE);
            mLoadingIndicator.setVisibility(View.GONE);
            mErrorImageView.setImageResource(R.drawable.no_internet_image);

        }


    }

    @Override
    public android.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String defautArticleNumber = sharedPref.getString(
                getString(R.string.settings_article_limit_key),
                getString(R.string.settings_article_limit_default)
                );
        Uri baseUri = Uri.parse(GUARDIAN_API_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("page-size", defautArticleNumber);
        uriBuilder.appendQueryParameter("show-fields", "headline,thumbnail");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", BuildConfig.API_KEY);
        return new NewsLoader(MainActivity.this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<News>> loader, List<News> newsList) {
        mErrorImageView.setImageResource(R.drawable.no_news_image);
        mLoadingIndicator.setVisibility(View.GONE);
        mLoadingIndicatorLogo.setVisibility(View.GONE);
        if(newsList != null && !newsList.isEmpty()){
            mErrorImageView.setVisibility(View.GONE);
            initializeRecyclerView(newsList);

        }

    }

    @Override
    public void onLoaderReset(android.content.Loader<List<News>> loader) {

    }

    /**
     * Determine the internet connection
     * @return true if internet is connected
     */
    private boolean isInternetConnected(){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Initialize the RecyclerView
     * @param newsList represents a list of News instances
     */
    private void initializeRecyclerView(List<News> newsList ){
        if(newsList == null)
            newsList = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter(this, newsList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


