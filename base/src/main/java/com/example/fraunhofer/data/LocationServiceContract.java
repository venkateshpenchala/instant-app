package com.example.fraunhofer.data;

import android.provider.BaseColumns;

/**
 * Created by Venkatesh on 6/12/2017.
 */

public class LocationServiceContract {
    public static final class LocationServiceEntry implements BaseColumns {
        public static final String LOCATION_ALL_URL = "http://10.0.2.2:8000/locationdata/all/";
        public static final String LOCATION_ALL_BYKATEGORY_URL = "http://10.0.2.2:8000/locationdata/allkategory/?kategory=";

        public static final String LOCATION_DETAIL_BYNAME_URL = "http://10.0.2.2:8000/locationdata/detailByName/?name=";
        public static final String LOCATION_DETAIL_BYID_URL = "http://10.0.2.2:8000/locationdata/detailById/?id=";

        public static final String STATIC_LOCATION = "http://10.0.2.2:8000/static/img/";
    }
}
