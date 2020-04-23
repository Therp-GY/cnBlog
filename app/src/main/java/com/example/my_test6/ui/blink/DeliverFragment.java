package com.example.my_test6.ui.blink;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.my_test6.Pool.TokenPool;
import com.example.my_test6.R;
import com.example.my_test6.login;

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
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.blink_fragment_deliver, container, false);
        deliver = view.findViewById(R.id.deliver);
        deliver.setOnClickListener(this);
        if(!TokenPool.getTokenPool().isLogin()){
            Intent intent = new Intent(getActivity(), login.class);
            startActivity(intent);
        }
        return view;
    }

    @Override
    public void onResume() {
        deliver.setOnClickListener(this);
        Log.d(TAG, "onResume: ");
        Log.d(TAG, "onResume: "+TokenPool.getTokenPool().getUserToken());
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.deliver:
                Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
