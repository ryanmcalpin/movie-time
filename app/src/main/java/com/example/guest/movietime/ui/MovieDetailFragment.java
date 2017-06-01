package com.example.guest.movietime.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.movietime.R;
import com.example.guest.movietime.models.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 1198;
    @Bind(R.id.posterV) ImageView posterV;
    @Bind(R.id.titleV) TextView titleV;
    @Bind(R.id.yearV) TextView yearV;
    @Bind(R.id.ratingV) TextView ratingV;
    @Bind(R.id.descV) TextView descV;

    private Movie mMovie;

    public static MovieDetailFragment newInstance(Movie movie){
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }


    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mMovie.getPosterPath()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(posterV);
        titleV.setText(mMovie.getTitle());
        ratingV.setText(mMovie.getRating());
        yearV.setText("(" + mMovie.getReleaseDate() + ")");
        descV.setText(mMovie.getOverview());

        titleV.setOnClickListener(this);
        posterV.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        if(v == posterV){
            Intent fullscreenIntent = new Intent();
            fullscreenIntent.setAction(Intent.ACTION_VIEW);
            fullscreenIntent.setDataAndType(Uri.parse(mMovie.getPosterPath()), "image/*");
            startActivity(fullscreenIntent);
        }
        if (v == titleV){
            Intent imdbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mMovie.getImdb()));
            startActivity(imdbIntent);
        }

    }

}
