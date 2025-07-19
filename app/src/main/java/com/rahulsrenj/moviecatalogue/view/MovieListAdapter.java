package com.rahulsrenj.moviecatalogue.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rahulsrenj.moviecatalogue.R;
import com.rahulsrenj.moviecatalogue.databinding.ItemMovieLayoutBinding;
import com.rahulsrenj.moviecatalogue.models.Movie;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private ArrayList<Movie> movieArrayList;
    private ItemMovieLayoutBinding binding;

    private MovieClickListener clickListener;
    public  MovieListAdapter(ArrayList<Movie> movieArrayList)
    {
        this.movieArrayList=movieArrayList;
    }

    public void setMovieClickListener(MovieClickListener clickListener)
    {
        this.clickListener=clickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie_layout,parent,false);
        return new MovieViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentMovie= movieArrayList.get(position);
        holder.binding.setMovie(currentMovie);
    }

    @Override
    public int getItemCount() {
        if (movieArrayList!=null)
        {
            return movieArrayList.size();
        }
        return 0;
    }

    public void updateData(ArrayList<Movie> newMovies)
    {
        this.movieArrayList.clear();
        this.movieArrayList.addAll(newMovies);
        notifyDataSetChanged();
    }

    public  class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemMovieLayoutBinding binding;
        public MovieViewHolder(ItemMovieLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener!=null)
            {
                clickListener.onMovieClicked(v,movieArrayList.get(getAdapterPosition()));
            }
        }
    }
}
