package com.example.guest.movietime.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.guest.movietime.R;
import com.example.guest.movietime.adapters.MovieListAdapter;
import com.example.guest.movietime.models.Movie;
import com.example.guest.movietime.services.MovieService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {
    @Bind(R.id.listView) ListView mListView;
    public ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String year = intent.getStringExtra("year");
        getMovies(title, year);
    }

    private void getMovies(String title, String year) {
        final MovieService movieService = new MovieService();
        movieService.findMovies(title, year, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mMovies = movieService.processResults(response);
                MovieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] movieTitles = new String[mMovies.size()];
                        for (int i = 0; i < movieTitles.length; i++) {
                            movieTitles[i] = mMovies.get(i).getTitle();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(MovieActivity.this, android.R.layout.simple_list_item_1, movieTitles);
                        mListView.setAdapter(adapter);
                    }
                });
            }
        });
    }
}
