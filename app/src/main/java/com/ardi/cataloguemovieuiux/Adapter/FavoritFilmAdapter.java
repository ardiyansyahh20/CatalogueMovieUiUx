package com.ardi.cataloguemovieuiux.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardi.cataloguemovieuiux.BuildConfig;
import com.ardi.cataloguemovieuiux.R;
import com.ardi.cataloguemovieuiux.entity.Film;
import com.ardi.cataloguemovieuiux.search.DetailFilmActivity;
import com.squareup.picasso.Picasso;

public class FavoritFilmAdapter extends RecyclerView.Adapter<FavoritFilmAdapter.FilmViewHolder> {

    private Cursor listFilm;
    private Context context;
    class FilmViewHolder extends RecyclerView.ViewHolder{

        TextView tvJudul, tvOverview, tvRilis;
        ImageView imagePoster;
        Button btnDetail, btnShare;

        FilmViewHolder(View itemView){
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_item_title);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            tvRilis = itemView.findViewById(R.id.tv_item_date);
            imagePoster = itemView.findViewById(R.id.img_item_poster);
            btnDetail = itemView.findViewById(R.id.btn_set_detail);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }
    }


    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_playing_list, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        final Film film = getItem(position);
        holder.tvJudul.setText(film.getJudulFilm());
        holder.tvOverview.setText(film.getOverviewFilm());
        holder.tvRilis.setText(film.getTanggalRilis());
        Picasso.get()
                .load(BuildConfig.POSTER_URL +film.getPosterFilm())
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
                .into(holder.imagePoster);
        holder.btnShare.setOnClickListener(new OnItemClickListener(position, new OnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, film.getJudulFilm()+" \n"+film.getOverviewFilm());
                intent.setType("text/plain");
                context.startActivity(intent);
            }
        }));

        holder.btnDetail.setOnClickListener(new OnItemClickListener(position, new OnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_TITLE, film.getJudulFilm());
                intent.putExtra(DetailFilmActivity.EXTRA_OVERVIEW, film.getOverviewFilm());
                intent.putExtra(DetailFilmActivity.EXTRA_IMAGE_MOVIE, film.getPosterFilm());
                intent.putExtra(DetailFilmActivity.EXTRA_RELEASE_DATE, film.getTanggalRilis());
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        if (listFilm == null){
            return 0;
        }
        return listFilm.getCount();
    }

    private Film getItem(int position){
        if (!listFilm.moveToPosition(position)){
            throw new IllegalStateException("position invalid");
        }

        return new Film(listFilm);
    }
    public FavoritFilmAdapter(Context context){
        this.context = context;
    }

    public void setListFilm(Cursor listFilm) {
        this.listFilm = listFilm;
    }
}
