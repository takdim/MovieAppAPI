package com.example.movieappapi.adapters.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieappapi.R;
import com.example.movieappapi.models.movie.NowPlaying;
import com.example.movieappapi.networks.Consts;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {

    private List<NowPlaying> nowPlayingList;

    public void setNowPlayingList(List<NowPlaying> nowPlayingList){
        this.nowPlayingList = nowPlayingList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.onBind(nowPlayingList.get(position));
    }

    @Override
    public int getItemCount() {
        return nowPlayingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        NowPlaying nowPlaying;
        TextView tvTitle;
        ImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvJudul);
            photo = itemView.findViewById(R.id.photo);

        }
        public void onBind(NowPlaying nowPlaying){
            String uri = Consts.IMAGEBASEURL + nowPlaying.getPosterImage();
            this.nowPlaying = nowPlaying;
            tvTitle.setText(nowPlaying.getTitle());
            Glide.with(this.itemView.getContext()).load(uri).into(photo);
        }
    }
}
