package com.example.fraunhofer.ui;

import android.content.Context;
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

import java.io.InputStream;
import java.util.List;

/**
 * Created by Venkatesh on 6/12/2017.
 */

public class LocationDetailAdapter extends RecyclerView.Adapter<LocationDetailAdapter.LocationDetailViewHolder> {
    private List<DataModel> recyclerdata;
    private Context mContext;

    public LocationDetailAdapter(List<DataModel> recyclerdata, Context context) {
        this.recyclerdata = recyclerdata;
        this.mContext = context;
    }

    @Override
    public LocationDetailAdapter.LocationDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_detail,parent,false);
        return new LocationDetailAdapter.LocationDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationDetailAdapter.LocationDetailViewHolder holder, int position) {

        DataModel dataModel = recyclerdata.get(position);
        //int drawableResourceId = this.mContext.getResources().getIdentifier(dataModel.getImage(), "drawable", this.mContext.getPackageName());

        holder.titleTextView.setText(dataModel.getName());
        holder.infoTextView.setText(dataModel.getInfo());
        holder.referencesTextView.setText(dataModel.getReferences());
        holder.otherTextView.setText(dataModel.getOther());
        //holder.paintImageView.setImageResource(drawableResourceId);

        new LocationDetailAdapter.DownloadImageTask(holder.paintImageView)
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

    class LocationDetailViewHolder extends RecyclerView.ViewHolder {

        // Declare all the variables here
        TextView titleTextView;
        TextView infoTextView;
        TextView referencesTextView;
        TextView otherTextView;
        ImageView paintImageView;

        public LocationDetailViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.paint_title);
            infoTextView = (TextView) itemView.findViewById(R.id.paint_info);
            referencesTextView = (TextView) itemView.findViewById(R.id.paint_references);
            otherTextView = (TextView) itemView.findViewById(R.id.paint_other);
            paintImageView = (ImageView) itemView.findViewById(R.id.paint_image);
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
