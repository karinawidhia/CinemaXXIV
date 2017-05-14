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
public class Page3Fragment extends Fragment {

    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/upcoming?api_key=995d0fd758a9c51cc891161b42de18db";
    public List<Page3ListItem> listItems3;
    private RecyclerView recyclerView3;
    private RecyclerView.Adapter adapter3;

    public Page3Fragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page3, container, false);

        recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerView3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));

        listItems3 = new ArrayList<>();

        loadRecycleViewData();

        /*for (int i = 0; i <= 5; i++) {
            Page1ListItem listItem = new Page1ListItem(
                    "",
                    "Title" + (i + 1),
                    "Lorem ipsum");
            listItems.add(listItem);
        }
        */
        adapter3 = new Page3Adapter(listItems3, getActivity());
        recyclerView3.setAdapter(adapter3);

        return view;
        // Inflate the layout for this fragment
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
                                Page3ListItem item3 = new Page3ListItem(
                                        o.getString("poster_path"),
                                        o.getString("title"),
                                        o.getString("release_date")
                                );
                                listItems3.add(item3);
                            }
                            adapter3 = new Page3Adapter(listItems3, getActivity().getApplication());//apabila di acyvity getactivity hanya ditulis this saja.
                            recyclerView3.setAdapter(adapter3);
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
