package com.ardi.cataloguemovieuiux.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ardi.cataloguemovieuiux.BuildConfig;
import com.ardi.cataloguemovieuiux.R;
import com.ardi.cataloguemovieuiux.entity.Film;
import com.ardi.cataloguemovieuiux.search.DetailFilmActivity;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private List<Film> films;
    private Context context;
    @BindString(R.string.detail)
    String detail;
    @BindString(R.string.share)
    String share;

    public ContentAdapter(List<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_playing_list, parent, false);
        ButterKnife.bind(this, v);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Film film = films.get(position);
        holder.title.setText(film.getJudulFilm());
        holder.overview.setText(film.getOverviewFilm());

        String release_date = film.getTanggalRilis();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("E, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            holder.date.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.get().load(BuildConfig.POSTER_URL + film.getPosterFilm())
                .into(holder.poster);

        Picasso.get().load(BuildConfig.BACKDROP_URL + film.getBackdropFilm());

        holder.btnDetail.setOnClickListener(new OnItemClickListener(position, new OnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_TITLE, film.getJudulFilm());
                intent.putExtra(DetailFilmActivity.EXTRA_OVERVIEW, film.getOverviewFilm());
                intent.putExtra(DetailFilmActivity.EXTRA_IMAGE_MOVIE, film.getPosterFilm());
                intent.putExtra(DetailFilmActivity.EXTRA_RELEASE_DATE, film.getTanggalRilis());
                intent.putExtra(DetailFilmActivity.EXTRA_BACKDROP, film.getBackdropFilm());
                context.startActivity(intent);
            }

        }));

        holder.btnShare.setOnClickListener(new OnItemClickListener(position, new OnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, film.getJudulFilm() + " \n" + film.getOverviewFilm());
                intent.setType("text/plain");
                context.startActivity(intent);
            }
        }));

        holder.cvFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Film movieList = films.get(position);
                Intent Intent = new Intent(context, DetailFilmActivity.class);
                Intent.putExtra(DetailFilmActivity.EXTRA_TITLE, movieList.getJudulFilm());
                Intent.putExtra(DetailFilmActivity.EXTRA_OVERVIEW, movieList.getOverviewFilm());
                Intent.putExtra(DetailFilmActivity.EXTRA_RATING, movieList.getRatingFilm());
                Intent.putExtra(DetailFilmActivity.EXTRA_VOTE, movieList.getVoteFilm());
                Intent.putExtra(DetailFilmActivity.EXTRA_IMAGE_MOVIE, movieList.getPosterFilm());
                Intent.putExtra(DetailFilmActivity.EXTRA_RELEASE_DATE, movieList.getTanggalRilis());
                Intent.putExtra(DetailFilmActivity.EXTRA_BACKDROP, movieList.getBackdropFilm());

                context.startActivity(Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_title)
        TextView title;
        @BindView(R.id.tv_item_overview)
        TextView overview;
        @BindView(R.id.tv_item_date)
        TextView date;
        @BindView(R.id.img_item_poster)
        ImageView poster;


        @BindView(R.id.btn_set_detail)
        Button btnDetail;
        @BindView(R.id.btn_set_share)
        Button btnShare;
        @BindView(R.id.cv_film)
        LinearLayout cvFilm;
        private Context context;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
