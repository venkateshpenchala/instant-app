package com.fraunhofer.fraunhofer.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.fraunhofer.fraunhofer.data.LocationServiceContract;
import com.fraunhofer.fraunhofer.data.model.DataModel;
import com.fraunhofer.fraunhofer.ui.LocationDetailAdapter;
import com.fraunhofer.fraunhofer.utilities.JSONhelper;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private LocationDetailAdapter mAdapter;
    RecyclerView locationDetailRecyclerView;
    private ProgressBar empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        locationDetailRecyclerView = (RecyclerView) this.findViewById(R.id.detail_recycler_view);
        empty = (ProgressBar) findViewById(android.R.id.empty);

        Intent intentThatStartedThisActivity = getIntent();
        Uri data = getIntent().getData();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {

            String clickedPainting = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            new Viewdata().execute(LocationServiceContract.LocationServiceEntry.LOCATION_DETAIL_BYNAME_URL+clickedPainting);
        }
        else if(data != null) { // If the childactivity has started from deeplink
            new Viewdata().execute(LocationServiceContract.LocationServiceEntry.LOCATION_DETAIL_BYID_URL+data.getPath().substring(1));
        }
        else {
            new Viewdata().execute(LocationServiceContract.LocationServiceEntry.LOCATION_DETAIL_BYNAME_URL+"");
        }
    }

    class Viewdata extends AsyncTask<String,String,List<DataModel>> {

        @Override
        protected List<DataModel> doInBackground(String... params) {
            JSONhelper jsonhelper = new JSONhelper();
            List<DataModel>  data = jsonhelper.getdatafromurl(params[0], "ChildActivity");
            return data;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<DataModel> dataModels) {
            super.onPostExecute(dataModels);
            empty.setVisibility(View.GONE);
            mAdapter = new LocationDetailAdapter(dataModels, DetailActivity.this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this);
            locationDetailRecyclerView.setLayoutManager(layoutManager);
            locationDetailRecyclerView.setItemAnimator(new DefaultItemAnimator());
            locationDetailRecyclerView.setAdapter(mAdapter);
        }
    }
}
