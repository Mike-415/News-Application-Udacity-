package com.example.android.newsapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RecyclerViewAdapter {
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

/*
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private List<Site> mSites;
    private Context mContext;


    public RecyclerViewAdapter(List<Site> sites, Context context) {
        this.mSites = sites;
        this.mContext = context;
    }

    /**
     * This inner class contains all the data that is within each list item
     */
//public class ViewHolder extends RecyclerView.ViewHolder{
//    @BindView(R.id.parent_layout)
//    RelativeLayout mParentLayout;
//    @BindView(R.id.list_item_image_view)
//    ImageView mImage;
//    @BindView(R.id.list_item_text_view)
//    TextView mText;
//    public ViewHolder(View itemView) {
//        super(itemView);
//        ButterKnife.bind(this, itemView);
//    }
//}
//
//    /**
//     * This callback method inflates the ViewHolder
//     */
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
//        return new ViewHolder(listItemView);
//    }
//
//    /**
//     * This callback method dynamically binds the ViewHolder to the RecyclerView
//     */
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//        final Site currentSite = mSites.get(position);
//        holder.mImage.setImageResource(currentSite.getImageResourceId());
//        holder.mText.setText(currentSite.getName());
//        holder.mParentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Site clickedSite = mSites.get(position);
//                Log.d(TAG, "onClick:  "+clickedSite);
//                segueToDetailActivity(clickedSite);
//            }
//        });
//
//
//    }
//
//    /**
//     * This callback method is used to determine the number of list items within the RecyclerView
//     */
//    @Override
//    public int getItemCount() {
//        return mSites.size();
//    }
//
//    /**
//     * Transfers to the Details Activity along with the Site instance
//     *  that the user clicked
//     * @param site is the Site instance being transferred to the Detail Activity
//     */
//    private void segueToDetailActivity(Site site){
//        Intent intent = new Intent(mContext, DetailsActivity.class);
//        intent.putExtra(Site.INTENT_TAG, site);
//        mContext.startActivity(intent);
//    }
//}
 */