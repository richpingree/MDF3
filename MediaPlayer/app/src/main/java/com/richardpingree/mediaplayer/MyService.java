package com.richardpingree.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Richard Pingree MDF3 1504 Week 1 on 3/31/15.
 */
public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BoundServiceBinder mServiceBinder = new BoundServiceBinder();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new BoundServiceBinder();
    }

    public class BoundServiceBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }
}
