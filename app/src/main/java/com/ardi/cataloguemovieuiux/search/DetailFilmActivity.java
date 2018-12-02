package com.ardi.cataloguemovieuiux.search;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardi.cataloguemovieuiux.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFilmActivity extends AppCompatActivity {

    public static String EXTRA_TITLE = "extra_title";
    public static String EXTRA_OVERVIEW = "extra_overview";
    public static String EXTRA_RELEASE_DATE = "extra_date_release";
    public static String EXTRA_IMAGE_MOVIE = "extra_image_movie";
    public static String EXTRA_RATING = "extra_rating";
    public static String EXTRA_VOTE = "extra_vote";

    @BindView(R.id.tv_detail_judul)
    TextView tvJudul;
    @BindView(R.id.tv_detail_overview)
    TextView tvOverview;
    @BindView(R.id.tv_detail_release)
    TextView tvRelease;
    @BindView(R.id.image_movie)
    ImageView imageMovie;
    @BindView(R.id.tv_detail_rating)
    TextView tvRating;
    @BindView(R.id.tv_detail_vote)
    TextView tvVote;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        ButterKnife.bind(this);

        String judul = getIntent().getStringExtra(EXTRA_TITLE);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String releaseDate = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        String image = getIntent().getStringExtra(EXTRA_IMAGE_MOVIE);
        String rating = getIntent().getStringExtra(EXTRA_RATING);
        String vote = getIntent().getStringExtra(EXTRA_VOTE);


        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(releaseDate);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            tvRelease.setText(date_of_release);

        }catch (ParseException e){
            e.printStackTrace();
        }

        tvJudul.setText(judul);
        tvOverview.setText(overview);
        tvRating.setText(rating);
        tvVote.setText(vote);
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + image).into(imageMovie);
    }
}
