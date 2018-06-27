package com.androidapp.alivetch.movies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Display extends Fragment {

    RequestQueue queue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    StringBuilder sb = new StringBuilder();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_display3, container, false);
        final TextView txt = (TextView) view.findViewById(R.id.displayTextview);



        String url = "http://www.omdbapi.com/?i=tt3896198&apikey=da1937a4";

        queue = NetworkController.getInstance(getActivity()).getRequestQueue();

      JsonObjectRequest objectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                     sb.setLength(0);


                    sb.append("Title: " + response.getString("Title") + "\n");
                    sb.append("Year: " + response.getString("Year") + "\n");
                    sb.append("Rated: " + response.getString("Rated") + "\n");
                    sb.append("Released: " + response.getString("Released") + "\n");
                    sb.append("Runtime: " + response.getString("Runtime") + "\n");
                    sb.append("Genre: " + response.getString("Genre") + "\n");
                    sb.append("Director: " + response.getString("Director") + "\n");
                    sb.append("Writer: " + response.getString("Writer") + "\n");
                    sb.append("Actors: " + response.getString("Actors") + "\n");
                    sb.append("Plot: " + response.getString("Plot") + "\n");
                    sb.append("Language: " + response.getString("Language") + "\n");
                    sb.append("Country: " + response.getString("Country") + "\n");
                    sb.append("Awards: " + response.getString("Awards") + "\n");
                    sb.append("Metascore: " + response.getString("Metascore") + "\n");
                    sb.append("imdbRating: " + response.getString("imdbRating") + "\n");
                    sb.append("imdbVotes: " + response.getString("imdbVotes") + "\n");
                    sb.append("imdbID: " + response.getString("imdbID") + "\n");
                    sb.append("Type: " + response.getString("Type") + "\n");
                    sb.append("DVD: " + response.getString("DVD") + "\n");
                    sb.append("BoxOffice: " + response.getString("BoxOffice") + "\n");
                    sb.append("Production: " + response.getString("Production") + "\n");
                    sb.append("Website: " + response.getString("Website") + "\n");
                    sb.append("Response: " + response.getString("Response") + "\n");

                    JSONArray array = response.getJSONArray("Ratings");
                    for (int m = 0; m < array.length(); m++) {
                        JSONObject object2 = array.getJSONObject(m);

                        sb.append("Ratings:"+"\n"+"Source: " + object2.getString("Source") + "\n");
                        sb.append("Value: " + object2.getString("Value") + "\n");

                        txt.setText(sb.toString());}


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(5 * DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 0));
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(objectRequest);
        queue.add(objectRequest);
        return view;
    }


}




