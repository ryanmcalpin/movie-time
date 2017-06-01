package com.example.guest.movietime.services;

import com.example.guest.movietime.Constants;
import com.example.guest.movietime.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 6/1/17.
 */

public class MovieService {

    public static void findMovies(String title, String year, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY_PARAM, title).addQueryParameter(Constants.YEAR_PARAM, year).addQueryParameter(Constants.SORT_PARAM, Constants.SORT_VALUE).addQueryParameter(Constants.API_PARAM, Constants.MOVIE_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response){
        ArrayList<Movie> movies = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject moviesJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = moviesJSON.getJSONArray("results");
                for (int i = 0; i < resultsJSON.length(); i++){
                    JSONObject movieJSON = resultsJSON.getJSONObject(i);
                    String title = movieJSON.getString("title");
                    String desc = movieJSON.getString("overview");
                    String posterPath = movieJSON.getString("poster_path");
                    String releaseD = movieJSON.getString("release_date");
                    String id = movieJSON.getString("id");
                    String rating = movieJSON.getString("vote_average");
                    Movie movie = new Movie(title, desc, posterPath, releaseD, id, rating);
                        movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
