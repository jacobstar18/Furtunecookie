package Eunsung.com;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class newsActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //RequestQueue queue = Volley.newRequestQueue(this);
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mrecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mrecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)


        getNews();
        //1. 화면이 로딩 -> 뉴스정보 받아온다
        //3, 어뎁터 -> 셋팅
    }

    public void getNews(){

        // Request 초기화
        String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=3c94e09f7c544418bd31048568685903";
        queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NEWS",response);

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray arrayArticles =  jsonObj.getJSONArray("articles");

                            List<NewsData> news = new ArrayList<>();
                            for(int i=0, j= arrayArticles.length(); i<j; i++ ){

                                    JSONObject obj = arrayArticles.getJSONObject(i);

                                Log.d("제모옥 : ", obj.getString("title"));
                                if (obj.getString("title").contains("조국")== true) {
                                    Log.d("들어옴 ?  : ", obj.getString("title"));
                                    NewsData newsData = new NewsData();

                                    newsData.setTitle(obj.getString("title"));

                                    newsData.setUrlToImage(obj.getString("urlToImage"));

                                    newsData.setContent(obj.getString("content"));

                                    news.add(newsData);
                                }
                            }
                            // - response -> NewDATA CALSS 분류

                            //2 정보 -> 어뎁터
                            mAdapter = new MyAdapter(news, newsActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                       Object obj = v.getTag();
                                       if(obj !=null){
                                           int position =(int)obj;
                                           ((MyAdapter)mAdapter).getNews(position);

                                       }
                                }
                            });
                            mrecyclerView.setAdapter(mAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
