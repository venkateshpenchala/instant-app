package com.example.fraunhofer.ui;

/**
 * Created by Venkatesh on 6/12/2017.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fraunhofer.data.LocationServiceContract;
import com.example.fraunhofer.data.model.DataModel;
import com.example.fraunhofer.fraunhoferiem.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LocationListAdapter  extends RecyclerView.Adapter<LocationListAdapter.LocationListViewHolder>  {
    List<DataModel> recyclerdata;

    public LocationListAdapter(List<DataModel> recyclerdata) {
        this.recyclerdata = recyclerdata;
    }

    @Override
    public LocationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout activity_location_list
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item,parent,false);
        return new LocationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationListViewHolder holder, int position) {
        DataModel dataModel = recyclerdata.get(position);
        holder.titleTextView.setText(dataModel.getName());

        new DownloadImageTask(holder.imageView)
                .execute(LocationServiceContract.LocationServiceEntry.STATIC_LOCATION + dataModel.getImage());

    }


    @Override
    public int getItemCount() {
        try {
            return recyclerdata.size();
        }
        catch (NullPointerException ex) {
            return 0;
        }
    }

    class LocationListViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        ImageView imageView;
        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews
         *
         * @param itemView The View that you inflated in
         *                 {@link LocationListAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public LocationListViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
            imageView = (ImageView) itemView.findViewById(R.id.photo);
        }

    }


    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
