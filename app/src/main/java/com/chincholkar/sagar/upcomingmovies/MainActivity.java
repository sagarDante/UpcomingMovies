package com.chincholkar.sagar.upcomingmovies;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.chincholkar.sagar.upcomingmovies.Adapter.MovielistAdapter;
import com.chincholkar.sagar.upcomingmovies.ModelClasses.MoviesList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String UPCOMING_MOVIES = "https://api.themoviedb.org/3/movie/upcoming?api_key=b7cd3340a794e5a2f35e3abb820b497f";
    private ArrayList<MoviesList> UpcomingMovie;
    private ProgressDialog pDialog;
    private String responce;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MovielistAdapter movielistAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiprefreshlayout);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new MainActivity.UpcomingMovielist().execute();

        UpcomingMovie = new ArrayList<>();

        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue,R.color.red);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                UpcomingMovie.clear();
                new MainActivity.UpcomingMovielist().execute();
            }
        });


    }

    /**
     * Async task to get all  Movie data
     */
    private class UpcomingMovielist extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Fetching Data...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            ServiceHandler jsonParser = new ServiceHandler();
            String json = jsonParser.makeServiceCall(UPCOMING_MOVIES, ServiceHandler.GET);

            Log.e("Response: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        JSONArray categories = jsonObj
                                .getJSONArray("results");

                        for (int i = 0; i < categories.length(); i++) {
                            JSONObject catObj = (JSONObject) categories.get(i);
                            MoviesList cat = new MoviesList(catObj.getString("id"),catObj.getString("title"),catObj.getString("poster_path"),catObj.getString("adult"),catObj.getString("release_date"),catObj.getString("overview"),catObj.getString("vote_average"));
                            UpcomingMovie.add(cat);
                        }
                    } else {
                        responce=jsonObj.getString("result");
                        Toast.makeText(getApplicationContext(), ""+responce,Toast.LENGTH_SHORT).show();
                        Log.e("Create Category Error: ", "> " + jsonObj.getString("message"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            movielistAdapter=new MovielistAdapter(getApplicationContext(),UpcomingMovie);
            recyclerView.setAdapter(movielistAdapter);
            swipeRefreshLayout.setRefreshing(false);
            //Toast.makeText(LoginActivity.this,""+ins_id,Toast.LENGTH_SHORT).show();
        }
    }
    /***
     *  Async task end
     * * */
}
