package com.ardi.cataloguemovieuiux.search;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardi.cataloguemovieuiux.BuildConfig;
import com.ardi.cataloguemovieuiux.Database.DatabaseContract;
import com.ardi.cataloguemovieuiux.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFilmActivity extends AppCompatActivity {
    private static final String DETAIL_FILM = "movie_detail";

    public static String EXTRA_TITLE = "extra_title";
    public static String EXTRA_OVERVIEW = "extra_overview";
    public static String EXTRA_RELEASE_DATE = "extra_date_release";
    public static String EXTRA_IMAGE_MOVIE = "extra_image_movie";
    public static String EXTRA_RATING = "extra_rating";
    public static String EXTRA_VOTE = "extra_vote";
    public static String EXTRA_BACKDROP = "extra_backdrop";

    @BindView(R.id.tv_detail_film)
    TextView tvJudul;
    @BindView(R.id.tv_detail_item_overview)
    TextView tvOverview;
    @BindView(R.id.tv_rilis_film)
    TextView tvRelease;
    @BindView(R.id.poster_film)
    ImageView imageMovie;
    @BindView(R.id.image_detail)
    ImageView backdropMovie;
    @BindView(R.id.tv_rating_film)
    TextView tvRating;
    @BindView(R.id.tv_vote_film)
    TextView tvVote;
    @BindView(R.id.fab)
    FloatingActionButton fab;


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
        String backdrop = getIntent().getStringExtra(EXTRA_BACKDROP);


        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(releaseDate);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            tvRelease.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvJudul.setText(judul);
        tvOverview.setText(overview);
        tvRating.setText(rating);
        tvVote.setText(vote);
        Picasso.get().load(BuildConfig.POSTER_URL + image).into(imageMovie);
        Picasso.get()
                .load(BuildConfig.BACKDROP_URL + backdrop)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
                .into(backdropMovie);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Favorite", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private boolean isFavorite(String id) {
        String selection = "movie_id = ? ";
        String[] selectionArgs = {id};
        String[] projection = {DatabaseContract.filmColumns.ID_FILM};
        Uri uri = DatabaseContract.CONTENT_URI;
        uri = uri.buildUpon().appendPath(id).build();

        Cursor cursor = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            cursor = getContentResolver().query(uri, projection, selection, selectionArgs, null, null);
        }
        assert cursor != null;
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return  exists;
    }



}
