package com.NetWork;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ReponseService extends Service {
    public ReponseService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        String url = "http://172.27.129.252/Mhealthy/PatientRegister";
        final HttpPost httpPost = new HttpPost(url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpResponse response = new DefaultHttpClient().execute(httpPost);
                   if( response.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
                   {
                       String content= EntityUtils.toString(response.getEntity());
                   }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
