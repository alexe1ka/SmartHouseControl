package com.example.alexe1ka.iotalexe1ka.data;

import android.provider.BaseColumns;

/**
 * Created by alexe1ka on 07.01.2017.
 */

public class DataDbSchema {
    public static final class DataTable {
        public static final String NAME = "dataTable";


        public static final class Cols {
            public static final String TEMP = "temperature";
            public static final String DATATIME = "date";
        }
    }
}