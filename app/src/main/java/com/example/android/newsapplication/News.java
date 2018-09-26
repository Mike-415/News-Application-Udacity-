package com.example.android.newsapplication;

public class News {
    private String mTitle;
    private String mSection;
    private String mAuthor;
    private String mImageUrl;
    private String mGuardianUrl;
    private String mDate;


    public News(String title, String section, String date, String author, String imageUrl, String guardianUrl) {
        mTitle = title;
        mSection = section;
        mDate = date;
        mAuthor = author;
        mImageUrl = imageUrl;
        mGuardianUrl = guardianUrl;
    }

    /**
     * Get the title of the news article
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the section name of the news article
     */
    public String getSection() {
        return mSection;
    }

    /**
     * Get the publication date of the news article
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Get the author of the news article
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * Get the thumbnail image of the news article
     */
    public String getImageUrl() {
        return mImageUrl;
    }

    /**
     * Get the link to the url of the news article
     */
    public String getGuardianUrl() {
        return mGuardianUrl;
    }

    /**
     * True if the article has an author
     */
    public boolean hasAuthor(){
        return mAuthor != null;
    }

    /**
     * True if the article has a thumbnail image
     */
    public boolean hasImage(){
        return mImageUrl != null;
    }

    /**
     * True if the article has a publication date
     */
    public boolean hasDate(){
        return mDate != null;
    }

    @Override
    public String toString() {
        return "News{" +
                "mTitle='" + mTitle + '\'' +
                ", mSection='" + mSection + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                ", mGuardianUrl='" + mGuardianUrl + '\'' +
                ", mDate='" + mDate + '\'' +
                '}';
    }
}
