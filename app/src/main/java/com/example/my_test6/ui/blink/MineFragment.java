package com.example.my_test6.ui.blink;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_test6.R;
import com.example.my_test6.ui.blink.adapter.blinkListAdapter;
import com.example.my_test6.ui.blink.domain.blinkInfo;
import com.example.my_test6.ui.blink.domain.clientInfo;

import java.util.ArrayList;
import java.util.List;

public class MineFragment extends ListFragment {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<blinkInfo> blinkInfoList = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            blinkInfoList.add(new blinkInfo("","!","true","","","","","","","Therp",""));
        }
        blinkListAdapter blinkListAdapter = new blinkListAdapter(this.getActivity(), blinkInfoList);
        this.setListAdapter(blinkListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }
}
