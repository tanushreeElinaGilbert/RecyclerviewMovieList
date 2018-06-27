package com.androidapp.alivetch.movies;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by user on 6/27/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<MovieContent> moviesList;
    private Context context;
    private LayoutInflater inflater;

    android.support.v4.app.FragmentManager manager;
    private FragmentTransaction transaction;
    public MyAdapter(List<MovieContent> moviesList,Context context) {
        this.moviesList = moviesList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.singleitem_recyclerview, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        MovieContent moviecontent = moviesList.get(position);

        holder.title.setText(moviecontent.getMovieTitle());
        holder.director.setText(moviecontent.getDirector());
        holder.imageview.setImageUrl(moviecontent.getImgURL(), NetworkController.getInstance(context).getImageLoader());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends ViewHolder {
        private TextView director, title;
        private NetworkImageView imageview;


        public MyViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_view);
            director = (TextView) itemView.findViewById(R.id.content_view);

            imageview = (NetworkImageView) itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((MainActivity) itemView.getContext()).getFragmentManager().beginTransaction()
                            .replace(R.id.content, new Display())
                            .commit();
                }
            });
        }
        public void setTitle(String s) {
            title.setText(s);
        }
        public void setDirector(String s) {
            director.setText(s);
        }


    }

}


