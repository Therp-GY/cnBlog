package com.example.my_test6.ui.blink;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.my_test6.Bean.Token;
import com.example.my_test6.Pool.TokenPool;
import com.example.my_test6.R;
import com.example.my_test6.ui.user.login;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliverFragment extends Fragment {

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
        setUI();
        return view;
    }

    private void setUI(){
        if(TokenPool.getTokenPool().isLogin()){
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.deliver_frame,new DeliverLoginFragment());
            fragmentTransaction.commit();
        }else{
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.deliver_frame,new LoginFragment());
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUI();
        Log.d(TAG, "onResume: ");
    }

}
