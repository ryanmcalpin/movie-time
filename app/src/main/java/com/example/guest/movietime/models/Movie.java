package com.example.guest.movietime.models;

/**
 * Created by Guest on 6/1/17.
 */

public class Movie {
    private String mTitle;
    private String mOverview;
    private String mReleaseDate;
    private String mPosterPath;
    private String mId;
    private String mRating;

    public Movie(String title, String overview, String posterPath, String releaseDate, String id, String rating){
        this.mTitle = title;
        this.mOverview = overview;
        this.mReleaseDate = releaseDate;
        this.mPosterPath = posterPath;
        this.mId = id;
        this.mRating = rating;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate.substring(0, 4);
    }

    public String getPosterPath() {
        return "http://image.tmdb.org/t/p/w185/" + mPosterPath;
    }

    public String getId() {
        return mId;
    }

    public String getRating() {
        return mRating + " / 10";
    }
}
