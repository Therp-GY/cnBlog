package com.example.my_test6.ui.blink;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.my_test6.R;
import com.example.my_test6.ui.blink.domain.accessToken;
import com.example.my_test6.ui.blink.domain.clientCredential;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliverFragment extends Fragment implements View.OnClickListener{

    private Button deliver;
    private Button test_service;
    private  static  String TAG = "DeliverFragment";

    public DeliverFragment() {
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deliver, container, false);
        deliver = view.findViewById(R.id.deliver);
        deliver.setOnClickListener(this);
        test_service = view.findViewById(R.id.test_service);
        test_service.setOnClickListener(this);
        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.deliver:
                Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.test_service:
                Intent intent = new Intent(getActivity(),test_service.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "测试服务", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
