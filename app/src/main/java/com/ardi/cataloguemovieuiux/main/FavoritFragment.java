package com.ardi.cataloguemovieuiux.main;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ardi.cataloguemovieuiux.Adapter.FavoritFilmAdapter;
import com.ardi.cataloguemovieuiux.R;

import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritFragment extends Fragment {

    private Cursor list;
    private FavoritFilmAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    public FavoritFragment (){
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    private void showSnackbarMessage(){
        Snackbar.make(recyclerView, "", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadMovieAsync().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorit, container, false);

        recyclerView = view.findViewById(R.id.rv_favorit);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        progressBar = view.findViewById(R.id.progressbar);
        adapter = new FavoritFilmAdapter(getContext());
        adapter.setListFilm(list);
        recyclerView.setAdapter(adapter);
        new LoadMovieAsync().execute();
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadMovieAsync extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContext().getContentResolver().query(CONTENT_URI, null, null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            progressBar.setVisibility(View.GONE);
            list = cursor;
            adapter.setListFilm(list);
            adapter.notifyDataSetChanged();

            if (list.getCount() == 0){
                showSnackbarMessage();
            }
        }
    }
}
