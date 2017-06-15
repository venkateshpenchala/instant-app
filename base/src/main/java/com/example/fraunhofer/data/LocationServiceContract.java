package com.example.fraunhofer.data;

import android.provider.BaseColumns;

/**
 * Created by Venkatesh on 6/12/2017.
 */

public class LocationServiceContract {
    public static final class LocationServiceEntry implements BaseColumns {
        public static final String LOCATION_ALL_URL = "https://midplan.de/locationdata/all/";
        public static final String LOCATION_ALL_BYKATEGORY_URL = "https://midplan.de/locationdata/allkategory/?kategory=";

        public static final String LOCATION_DETAIL_BYNAME_URL = "https://midplan.de/locationdata/detailByName/?name=";
        public static final String LOCATION_DETAIL_BYID_URL = "https://midplan.de/locationdata/detailById/?id=";

        public static final String STATIC_LOCATION = "https://midplan.de/static/img/";
    }
}
