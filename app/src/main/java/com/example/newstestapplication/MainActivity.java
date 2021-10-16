package com.example.newstestapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.newstestapplication.NewsListAdpater.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.onItemClicked {
    private RecyclerViewAdapter recyclerViewAdapter;
    private News news;
    private JSONArray jsonArray;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.mainblock);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    private void fetchData(){
        String url="https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=2e4d2725e8a045828ae009920621b633";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonArray = response.getJSONArray("articles");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayList arrayList = new ArrayList<News>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                jsonObject=jsonArray.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {

                                news = new News(
                                        jsonObject.getString("title"),
                                        jsonObject.getString("author"),
                                        jsonObject.getString("url"),
                                        jsonObject.getString("urlToImage"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            arrayList.add(news);
                        }
                        recyclerViewAdapter.update(arrayList);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                });



// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
    @Override
    public void onClicked(News item) {
     //   Toast.makeText(this,item,Toast.LENGTH_SHORT).show();
    }
}