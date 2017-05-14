package id.sch.smktelkom_mlg.privateassignment.xirpl115.projectpribadi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Smktelkom on 5/12/2017.
 */

public class Page3Adapter extends RecyclerView.Adapter<Page3Adapter.ViewHolder> {
    private List<Page3ListItem> page3ListItems;
    private Context context3;

    public Page3Adapter(List<Page3ListItem> page3ListItems, Context context3) {
        this.page3ListItems = page3ListItems;
        this.context3 = context3;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.page_item3, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Page3ListItem page3ListItem = page3ListItems.get(position);

        holder.textViewTitle3.setText(page3ListItem.getTitle3());
        holder.textViewContent3.setText(page3ListItem.getContent3());

        Glide
                .with(context3)
                .load("http://image.tmdb.org/t/p/w500" + page3ListItem.getImageUrl3())
                .into(holder.imageViewOtOf3);

    }

    @Override
    public int getItemCount() {
        return page3ListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle3;
        public android.widget.TextView textViewContent3;
        public ImageView imageViewOtOf3;
        public LinearLayout linearLayout3;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle3 = (TextView) itemView.findViewById(R.id.textViewTitle3);
            textViewContent3 = (TextView) itemView.findViewById(R.id.textViewContent3);
            imageViewOtOf3 = (ImageView) itemView.findViewById(R.id.imageViewOtof3);
            linearLayout3 = (LinearLayout) itemView.findViewById(R.id.LinearLayout3);
        }
    }
}
