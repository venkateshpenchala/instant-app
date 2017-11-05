package com.fraunhofer.fraunhofer.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fraunhofer.fraunhofer.data.LocationServiceContract;
import com.fraunhofer.fraunhofer.data.model.DataModel;
import com.fraunhofer.fraunhofer.ui.LocationListAdapter;
import com.fraunhofer.fraunhofer.utilities.JSONhelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LocationListAdapter mAdapter;
    private RecyclerView locationlistRecyclerView;
    private ProgressBar empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri data = getIntent().getData();

        locationlistRecyclerView = (RecyclerView) this.findViewById(R.id.image_grid);
        empty = (ProgressBar) findViewById(android.R.id.empty);

        // Get the actual data
        if(data != null) {
            new Viewdata().execute(LocationServiceContract.LocationServiceEntry.LOCATION_ALL_BYKATEGORY_URL + data.getPath().substring(1));
        }
        else {
            new Viewdata().execute(LocationServiceContract.LocationServiceEntry.LOCATION_ALL_BYKATEGORY_URL+"Floor1");
        }
    }

    // Action called when clicked on each painting
     public void getDetails(View view) {

        String name;

        if(view instanceof ImageView) {
            ImageView imageClicked = (ImageView) view.findViewById(com.fraunhofer.fraunhofer.fraunhoferiem.R.id.photo);
            name = (String) imageClicked.getTag();
        }
        else {
            TextView titleClicked = (TextView) view.findViewById(com.fraunhofer.fraunhofer.fraunhoferiem.R.id.title_text_view);
            name = titleClicked.getText().toString();
        }

         MainActivity activity = MainActivity.this;
         final Intent intent = getDetailActivityStartIntent(
                 activity, name);

         activity.startActivity(
                 intent);
    }


    @NonNull
    private static Intent getDetailActivityStartIntent(Context context, String name) {
        final Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://midplan.de/detail"));
        intent.setPackage(context.getPackageName());
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        // Working around unboxing issues with multiple dex files on platforms prior to N.

        intent.putExtra(Intent.EXTRA_TEXT, name);
        return intent;
    }

    class Viewdata  extends AsyncTask<String,String,List<DataModel>> {

        @Override
        protected List<DataModel> doInBackground(String... params) {
            JSONhelper jsonhelper = new JSONhelper();
            List<DataModel>  data = jsonhelper.getdatafromurl(params[0], "MainActivity");
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
            mAdapter = new LocationListAdapter(dataModels);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            locationlistRecyclerView.setLayoutManager(layoutManager);
            locationlistRecyclerView.setItemAnimator(new DefaultItemAnimator());
            locationlistRecyclerView.setAdapter(mAdapter);
        }
    }
}
