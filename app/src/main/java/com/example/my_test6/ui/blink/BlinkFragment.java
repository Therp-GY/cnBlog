package com.example.my_test6.ui.blink;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.my_test6.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class BlinkFragment extends Fragment {

    static final int NUM_ITEMS = 4;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private String[] strings = new String[]{"推荐","关注","我的","发布"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_blink, container, false);
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new AttentionFragment());
        fragmentList.add(new MineFragment());
        fragmentList.add(new DeliverFragment());
        TabLayout tab_layout = root.findViewById(R.id.tab_layout);
        ViewPager viewPager = root.findViewById(R.id.viewPager);
        MyAdapter fragmentAdater = new  MyAdapter(getChildFragmentManager()); //    注意使用getChildFragmentManager()
        viewPager.setAdapter(fragmentAdater);
        tab_layout.setupWithViewPager(viewPager);
        return root;
    }


    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return strings[position];
        }
    }

}
