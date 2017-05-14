package id.sch.smktelkom_mlg.privateassignment.xirpl115.CinemaXXIV;

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
 * Created by Smktelkom on 5/12/2017.
 */

public class Page2Adapter extends RecyclerView.Adapter<Page2Adapter.ViewHolder> {

    private List<Page2ListItem> page2ListItems;
    private Context context2;

    public Page2Adapter(List<Page2ListItem> page2ListItems, Context context2) {
        this.page2ListItems = page2ListItems;
        this.context2 = context2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.page_item2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Page2ListItem page2ListItem = page2ListItems.get(position);

        holder.textViewTitle2.setText(page2ListItem.getTitle2());
        holder.textViewContent2.setText(page2ListItem.getContent2());

        Glide
                .with(context2)
                .load("http://image.tmdb.org/t/p/w500" + page2ListItem.getImageUrl2())
                .into(holder.imageViewOtOf2);

        holder.linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context2, page2ListItem.getTitle2() + " selected", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(context2, Detail2Activity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.putExtra("blog_id", position);
                context2.startActivity(intent2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return page2ListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle2;
        public TextView textViewContent2;
        public ImageView imageViewOtOf2;
        public LinearLayout linearLayout2;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle2 = (TextView) itemView.findViewById(R.id.textViewTitle2);
            textViewContent2 = (TextView) itemView.findViewById(R.id.textViewContent2);
            imageViewOtOf2 = (ImageView) itemView.findViewById(R.id.imageViewOtof2);
            linearLayout2 = (LinearLayout) itemView.findViewById(R.id.LinearLayout2);
        }
    }
}
