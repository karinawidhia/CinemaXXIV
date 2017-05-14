package id.sch.smktelkom_mlg.privateassignment.xirpl115.projectpribadi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Detail2Activity extends AppCompatActivity {

    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/now_playing?api_key=995d0fd758a9c51cc891161b42de18db";
    public TextView textViewHeading2;
    public TextView textViewDesc2;
    public TextView textViewReview2;
    public ImageView imageViewDetail2;
    public String url2;

    private Integer mPostkey2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        mPostkey2 = getIntent().getExtras().getInt("blog_id");

        loadRecyclerViewData();

        textViewHeading2 = (TextView) findViewById(R.id.textViewHeading2);
        textViewDesc2 = (TextView) findViewById(R.id.textViewDesc2);
        textViewReview2 = (TextView) findViewById(R.id.textViewReview2);
        imageViewDetail2 = (ImageView) findViewById(R.id.imageViewDetail2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("results");
                    JSONObject o = array.getJSONObject(mPostkey2);

                    setTitle("Movie Details");
                    textViewHeading2.setText(o.getString("title"));
                    textViewDesc2.setText("Popularity : " + o.getString("popularity"));
                    textViewReview2.setText("Overview : " + o.getString("overview"));
                    url2 = o.getJSONObject("link").getString("url");
                    Glide
                            .with(Detail2Activity.this)
                            .load("http://image.tmdb.org/t/p/w500" + o.getString("poster_path"))
                            .into(imageViewDetail2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
