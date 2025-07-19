package com.rahulsrenj.moviecatalogue.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rahulsrenj.moviecatalogue.R;
import com.rahulsrenj.moviecatalogue.databinding.ImageSliderBinding;
import com.rahulsrenj.moviecatalogue.models.Movie;

import java.util.ArrayList;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.SliderViewHolder> {

    private ArrayList<Movie> movieArrayList;
    private ImageSliderBinding binding;
    public ImageSliderAdapter(ArrayList<Movie> movieArrayList)
    {
        this.movieArrayList=movieArrayList;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.image_slider,parent,false);
        return new SliderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Movie currentMovie=movieArrayList.get(position);
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

    public class SliderViewHolder extends RecyclerView.ViewHolder{
        ImageSliderBinding binding;

        public SliderViewHolder(ImageSliderBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
