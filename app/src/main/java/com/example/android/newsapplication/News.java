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

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getDate() {
        return mDate;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getGuardianUrl() {
        return mGuardianUrl;
    }

    public boolean hasAuthor(){
        return mAuthor != null;
    }

    public boolean hasImage(){
        return mImageUrl != null;
    }

    public boolean hasDate(){
        return mDate != null;
    }

    @Override
    public String toString() {
        return "News{" +
                "mTitle='" + mTitle + '\'' +
                ", mSection='" + mSection + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                '}';
    }
}
