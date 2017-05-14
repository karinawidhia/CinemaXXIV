package id.sch.smktelkom_mlg.privateassignment.xirpl115.CinemaXXIV;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Detail3Activity extends AppCompatActivity {

    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/upcoming?api_key=995d0fd758a9c51cc891161b42de18db";
    public TextView textViewHeading3;
    public TextView textViewDesc3;
    public TextView textViewReview3;
    public ImageView imageViewDetail3;
    public String url3;

    private Integer mPostkey3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail3);

        mPostkey3 = getIntent().getExtras().getInt("blog_id");

        textViewHeading3 = (TextView) findViewById(R.id.textViewHeading3);
        textViewDesc3 = (TextView) findViewById(R.id.textViewDesc3);
        textViewReview3 = (TextView) findViewById(R.id.textViewReview3);
        imageViewDetail3 = (ImageView) findViewById(R.id.imageViewDetail3);

        loadRecyclerViewData();
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
                    JSONObject o = array.getJSONObject(mPostkey3);

                    setTitle("Movie Details");
                    textViewHeading3.setText(o.getString("title"));
                    textViewDesc3.setText("Popularity : " + o.getString("popularity"));
                    textViewReview3.setText("Overview : " + o.getString("overview"));
                    url3 = o.getJSONObject("link").getString("url");
                    Glide
                            .with(Detail3Activity.this)
                            .load("http://image.tmdb.org/t/p/w500" + o.getString("poster_path"))
                            .into(imageViewDetail3);
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
