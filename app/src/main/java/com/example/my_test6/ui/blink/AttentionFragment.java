package com.example.my_test6.ui.blink;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_test6.R;
import com.example.my_test6.ui.blink.adapter.blinkListAdapter;
import com.example.my_test6.ui.blink.domain.blinkInfo;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends ListFragment {

     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         List<blinkInfo> blinkInfoList = new ArrayList<>();
         for(int i = 0; i < 20; i++){
             blinkInfoList.add(new blinkInfo("","..............","true","","","","","","","unknow",""));
         }
         blinkListAdapter blinkListAdapter = new blinkListAdapter(this.getActivity(), blinkInfoList);
         this.setListAdapter(blinkListAdapter);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attention, container, false);
        return view;
    }
}
