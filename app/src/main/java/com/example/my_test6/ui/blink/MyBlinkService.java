package com.example.my_test6.ui.blink;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.my_test6.ui.blink.domain.accessToken;
import com.example.my_test6.ui.blink.domain.blinkInfo;
import com.example.my_test6.ui.blink.domain.clientCredential;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyBlinkService extends Service {
    private static final String TAG = "myBlinkService";
    private LocalBinder mbinder = new LocalBinder();
    private int count;
    private boolean quit = false;
    private accessToken accessToken;
    private List<blinkInfo> blinkInfoList;

    public MyBlinkService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        getToken();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        quit = true;
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mbinder;
    }

    public  class  LocalBinder extends Binder{
        public MyBlinkService getService(){
            return MyBlinkService.this;
        }
        public accessToken getToken(){
            return accessToken;
        }
        public List<blinkInfo> getBlink(){
            getBlink();
            return  blinkInfoList;
        }
    }

    private void getToken(){
        String url = "https://oauth.cnblogs.com/connect/token";
        clientCredential clientCredential = new clientCredential("8ab24367-91d6-4c19-9846-121909a0e01f", "nD63VpKAHFeE8ObrKiPYOZD0yPwoT1pxfgDhZG_E1SpgDyZogA2n0Z_0-3qXOq92z8avekcEzxDmy4Qp","client_credentials");

        RequestBody requestBody = new FormBody.Builder()
                .add("client_id",clientCredential.getClient_id())
                .add("client_secret",clientCredential.getClient_secret())
                .add("grant_type",clientCredential.getGrant_type())
                .build();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)//默认就是GET请求，可以不写
                .build();

        final Call call = okHttpClient.newCall(request);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    String s = response.body().string();
                    Log.d(TAG, "onResponse getToken: " + s);
                    Gson gson = new Gson();
                    accessToken = gson.fromJson(s, accessToken.class);
//                Log.d(TAG, "onResponse: " + s);
                    Log.d(TAG, "onResponse getToken: " + accessToken.getToken());
                    Log.d(TAG, "onResponse getToken: " + accessToken.getExpires_in());
                    Log.d(TAG, "onResponse getToken: " + accessToken.getToken_type());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getBlink() {
        String url = "https://api.cnblogs.com/api/statuses/@3?pageIndex=1&pageSize=10&tag=1";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .header("application", "x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer " + accessToken.getToken())
                .build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    String s = response.body().string();
                    Type blinkListType = new TypeToken<ArrayList<blinkInfo>>() {
                    }.getType();
                    Gson gson = new Gson();
                    blinkInfoList = gson.fromJson(s, blinkListType);
                    Log.d(TAG, "onResponse getBlink: " + accessToken.getToken());
                    Log.d(TAG, "onResponse getBlink: " + response.code());
                    Log.d(TAG, "onResponse getBlink: " + s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
