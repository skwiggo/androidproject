package com.example.user.todolistproject;

import android.provider.BaseColumns;

/**
 * Created by user on 05/09/2016.
 */

public class TaskContract {
    public static final String DB_NAME = "com.example.user.todolistproject.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";
        public static final String COL_TASK_TITLE = "title";
    }
}


