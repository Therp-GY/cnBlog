package com.example.my_test6.ui.blink;

import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.my_test6.Pool.TokenPool;
import com.example.my_test6.R;
import com.example.my_test6.netWork.GetApi;
import com.example.my_test6.netWork.GetUserApi;
import com.example.my_test6.ui.blink.adapter.blinkListAdapter;
import com.example.my_test6.ui.blink.blinkBean.blinkInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineLoginFragment extends Fragment {



    private static final int BLINK_INIT = 1;
    private static final int BLINK_ADD = 2;
    private Integer pageIndex = 1;
    private Integer pageSize = 10;
    private String tag = "1";
    private PullToRefreshListView refreshListView;
    private List<blinkInfo> blinkInfoList = new ArrayList<>();
    private blinkListAdapter blinkListAdapter;
    private static String TAG = "MineLoginFragment";


    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String s = (String) msg.obj;
            Log.d(TAG, "handleMessage: s"+s);
            Type blinkListType = new TypeToken<ArrayList<blinkInfo>>() {
            }.getType();
            List<blinkInfo> blinkInfoList_temp;
            Gson gson = new Gson();
            blinkInfoList_temp = gson.fromJson(s, blinkListType);
            blinkInfoList.addAll(blinkInfoList_temp);
            if (msg.what == BLINK_INIT) {
                Log.d(TAG, "handleMessage: list" + blinkInfoList.size());
                blinkListAdapter = new blinkListAdapter(getActivity(), blinkInfoList);
                refreshListView.setAdapter(blinkListAdapter);
            }
            if (msg.what == BLINK_ADD) {
                blinkListAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        getMineBlink(handler,pageIndex.toString(),pageSize.toString(),tag, BLINK_INIT);
        View view = inflater.inflate(R.layout.blink_fragment_mine_login, container, false);
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.mine_list);
        //设置可上拉刷新和下拉刷新
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新时显示的文本
        ILoadingLayout startLayout = refreshListView.getLoadingLayoutProxy(true, false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");

        ILoadingLayout endLayout = refreshListView.getLoadingLayoutProxy(false, true);
        endLayout.setPullLabel("正在上拉刷新...");
        endLayout.setRefreshingLabel("正在玩命加载中...");
        endLayout.setReleaseLabel("放开以刷新");
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        Log.d(TAG, "doInBackground: ");
                        // TODO Auto-generated method stub
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return null;
                    }

                    protected void onPostExecute(Void result) {
                        Log.d(TAG, "onPostExecute: ");
                        blinkInfoList.clear();
                        pageIndex = 1;
                        getMineBlink(handler,  pageIndex.toString(), pageSize.toString(), "2", BLINK_ADD);
                        refreshView.onRefreshComplete();
                    }
                }.execute();
            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        Log.d(TAG, "doInBackground: ");
                        // TODO Auto-generated method stub
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return null;
                    }

                    protected void onPostExecute(Void result) {
                        Log.d(TAG, "onPostExecute: ");
                        pageIndex ++;
                        getMineBlink(handler,  pageIndex.toString(), pageSize.toString(), "2", BLINK_ADD);
                        refreshView.onRefreshComplete();
                    }

                    ;
                }.execute();
            }
        });
        return view;
    }

    private void getMineBlink(final Handler handler, String pageIndex, String pageSize, String tag, final int what) {
        final String token = TokenPool.getTokenPool().getMyToken();
        String url = "https://api.cnblogs.com/api/statuses/";
        url = url + "@" + "my" + "?pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&tag=" + tag;
        Log.d(TAG, "getBlink: " + url);
        GetUserApi getUserApi = new GetUserApi();
        getUserApi.getMyApi(handler, url, what);
    }
}
