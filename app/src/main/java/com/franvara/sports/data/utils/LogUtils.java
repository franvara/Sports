package com.franvara.sports.data.utils;

import android.util.Log;

import com.franvara.sports.BuildConfig;

public class LogUtils {

    public static void LogDebug(String title, String description) {
        if (BuildConfig.LOG_ENABLED) {
            Log.d(title, description);
        }
    }

}
