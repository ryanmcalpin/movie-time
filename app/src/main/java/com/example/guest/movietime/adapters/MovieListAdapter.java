package com.example.guest.movietime.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.movietime.R;
import com.example.guest.movietime.models.Movie;
import com.example.guest.movietime.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 6/1/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private static final int MAX_WIDTH = 185;
    private static final int MAX_HEIGHT = 277;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;
    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position){
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount(){
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.placeholder1) TextView placeholder1;
        @Bind(R.id.placeholder2) TextView placeholder2;
        @Bind(R.id.placeholder3) TextView placeholder3;
        @Bind(R.id.placeholder4) ImageView placeholder4;

        private Context mContext;
        public MovieViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("movies", Parcels.wrap(mMovies));
            mContext.startActivity(intent);
        }
        public void bindMovie(Movie movie){
            placeholder1.setText(movie.getTitle());
            placeholder2.setText(movie.getReleaseDate());
            placeholder3.setText(movie.getRating());
            Picasso.with(mContext).load(movie.getPosterPath())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(placeholder4);
        }
    }
}
