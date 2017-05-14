package id.sch.smktelkom_mlg.privateassignment.xirpl115.projectpribadi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Smktelkom on 5/11/2017.
 */

public class Page1Adapter extends RecyclerView.Adapter<Page1Adapter.ViewHolder> {

    private List<Page1ListItem> page1ListItems;
    private Context context;

    public Page1Adapter(List<Page1ListItem> page1ListItems, Context context) {
        this.page1ListItems = page1ListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.page_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Page1ListItem page1ListItem = page1ListItems.get(position);

        holder.textViewTitle.setText(page1ListItem.getTitle());
        holder.textViewContent.setText(page1ListItem.getContent());

        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w500" + page1ListItem.getImageUrl())
                .into(holder.imageViewOtOf);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, page1ListItem.getTitle() + " selected", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(context, DetailActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("blog_id", position);
                context.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return page1ListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewContent;
        public ImageView imageViewOtOf;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewContent = (TextView) itemView.findViewById(R.id.textViewContent);
            imageViewOtOf = (ImageView) itemView.findViewById(R.id.imageViewOtof);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout);
        }
    }
}
