package com.androidapp.alivetch.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    String url = "http://www.omdbapi.com/?i=tt3896198&apikey=da1937a4";
    RecyclerView recyclerView;
    List<MovieContent> movieList = new ArrayList<MovieContent>();
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new MyAdapter(movieList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
        //Getting Instance of Volley Request Queue
        queue = NetworkController.getInstance(this).getRequestQueue();
                        //Volley's inbuilt class to make Json array request

                        final JsonObjectRequest objectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET ,url,null,new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //for (int i = 0; i < response.length(); i++) {
                                try {
                                    movieList.clear();
                                    MovieContent mcontents = new MovieContent(response.getString("Title"), response.getString("Director"), response.getString("Poster"));
                                    movieList.add(mcontents);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //finally {
                                //Notify adapter about data changes
                                // adapter.notifyItemChanged(i);
                                // }


                            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());

            }
        });
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(5 * DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 0));
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(objectRequest);
        queue.add(objectRequest);


    }
}

