package com.example.my_test6.ui.blink;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.my_test6.R;
import com.example.my_test6.ui.blink.adapter.blinkListAdapter;
import com.example.my_test6.ui.blink.domain.accessToken;
import com.example.my_test6.ui.blink.domain.clientCredential;
import com.example.my_test6.ui.blink.domain.blinkInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSource;

public class RecommendFragment extends ListFragment {

    private accessToken accessToken;
    private  static  String TAG = "RecommendFragment" ;
    private  List<blinkInfo> blinkInfoList;
    private Button show_recommend;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accessToken = new accessToken("","","");
        blinkInfoList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            blinkInfoList.add(new blinkInfo("","DDDDDDDDDD","true","","","","","","","all",""));
        }
        blinkListAdapter blinkListAdapter = new blinkListAdapter(getActivity(), blinkInfoList);
        this.setListAdapter(blinkListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_recommend, container, false);
        return view;
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

    private void getBlink(){
        String url = "https://api.cnblogs.com/api/statuses/@3?pageIndex=1&pageSize=10&tag=1";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .header("application","x-www-form-urlencoded")
                .addHeader("Authorization","Bearer "+accessToken.getToken())
                .build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    String s = response.body().string();
                    Type blinkListType = new TypeToken<ArrayList<blinkInfo>>(){}.getType();
                    Gson gson = new Gson();
                    blinkInfoList = gson.fromJson(s,blinkListType);
                    Log.d(TAG, "onResponse getBlink: "+accessToken.getToken());
                    Log.d(TAG, "onResponse getBlink: "+response.code());
                    Log.d(TAG, "onResponse getBlink: " +s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
