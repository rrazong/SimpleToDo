package com.example.richellerazon.simpletodo;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by richellerazon on 10/5/16.
 */
public class SimpleToDoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        // FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

        Stetho.initializeWithDefaults(this);

    }
}
