package de.rollkuchen.app.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.rollkuchen.app.R;
import de.rollkuchen.app.core.Rollkuchen;

/**
 * Created by darken (darken@darken.eu) on 07.03.2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.VH> {
    private final List<Rollkuchen> data = new ArrayList<>();
    private final Callback callback;

    public GalleryAdapter(Callback callback) {this.callback = callback;}

    public void setData(List<Rollkuchen> data) {
        this.data.clear();
        if (data != null) this.data.addAll(data);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gallery_line, parent, false);
        return new VH(view, callback);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface Callback {
        public void onShowRollkuchen(Rollkuchen rollkuchen);
    }

    static class VH extends RecyclerView.ViewHolder {
        private final Callback callback;
        @BindView(R.id.gallery_image) ImageView image;

        VH(View itemView, Callback callback) {
            super(itemView);
            this.callback = callback;
            ButterKnife.bind(this, itemView);
        }

        void bind(Rollkuchen item) {
            itemView.setOnClickListener(view -> {
                callback.onShowRollkuchen(item);
            });
            Glide.with(itemView.getContext())
                    .using(new FirebaseImageLoader())
                    .load(item.getThumbnail())
                    .into(image);
        }
    }
}
