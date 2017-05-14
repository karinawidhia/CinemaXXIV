package id.sch.smktelkom_mlg.privateassignment.xirpl115.projectpribadi;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class Page1Fragment extends Fragment {

    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/top_rated?api_key=995d0fd758a9c51cc891161b42de18db";
    public List<Page1ListItem> listItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public Page1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page1, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listItems = new ArrayList<>();

        loadRecycleViewData();

        /*for (int i = 0; i <= 5; i++) {
            Page1ListItem listItem = new Page1ListItem(
                    "",
                    "Title" + (i + 1),
                    "Lorem ipsum");
            listItems.add(listItem);
        }
        */
        adapter = new Page1Adapter(listItems, getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void loadRecycleViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();//untuk memghilangkan loading saat data sdh di dapat.
                        try {
                            JSONObject jsonObject = new JSONObject(s);

                            JSONArray array = jsonObject.getJSONArray("results");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Page1ListItem item = new Page1ListItem(
                                        o.getString("poster_path"),
                                        o.getString("title"),
                                        o.getString("release_date")
                                );
                                listItems.add(item);
                            }
                            adapter = new Page1Adapter(listItems, getActivity().getApplication());//apabila di acyvity getactivity hanya ditulis this saja.
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }

                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
