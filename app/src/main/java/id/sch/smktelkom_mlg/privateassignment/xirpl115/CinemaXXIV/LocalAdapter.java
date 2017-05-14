package id.sch.smktelkom_mlg.privateassignment.xirpl115.CinemaXXIV;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Smktelkom on 5/14/2017.
 */

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder> {

    List<LocalListItem> localListItems;
    Context context;

    public LocalAdapter(List<LocalListItem> localListItem, Context context) {
        this.localListItems = localListItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_local, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocalListItem place = localListItems.get(position);
        holder.textViewHead.setText(place.head);
        holder.imageViewOtof.setImageURI(Uri.parse(place.imageUrl));
    }

    @Override
    public int getItemCount() {
        return localListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public ImageView imageViewOtof;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            imageViewOtof = (ImageView) itemView.findViewById(R.id.imageViewOtof);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
