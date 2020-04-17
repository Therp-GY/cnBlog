package com.example.my_test6.ui.blink.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_test6.R;
import com.example.my_test6.ui.blink.domain.blinkInfo;

import java.util.List;

public class blinkListAdapter extends  BaseAdapter{
    private static String TAG = "GY blinkListAdapter";
    private  List<blinkInfo> blinkInfoList;
    private  LayoutInflater layoutInflater;
    public blinkListAdapter(Context context , List<blinkInfo> blinkInfoList) {
        this.blinkInfoList = blinkInfoList;
        layoutInflater = LayoutInflater.from(context);
        Log.d(TAG, "construct:"+this.blinkInfoList.size());
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: "+blinkInfoList.size());
        return blinkInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d(TAG, "getItem: "+blinkInfoList.get(position));
        return blinkInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId: "+position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: "+position);
        View view = layoutInflater.inflate(R.layout.attention_blink,null);
        blinkInfo blinkInfo = blinkInfoList.get(position);
        ImageView blinkImage = view.findViewById(R.id.lv_headPortrait);
        TextView blinkName = view.findViewById(R.id.tv_blinkName);
        TextView blinkBlink = view.findViewById(R.id.tv_blinkBlink);
        blinkName.setText(blinkInfo.getUserId());
        blinkBlink.setText(blinkInfo.getContent());
        return view;
    }
}