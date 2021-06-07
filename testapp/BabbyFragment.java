package com.example.testapp;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TableLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.*;
import androidx.viewpager2.widget.ViewPager2;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BabbyFragment extends Fragment {

    private BabbyViewModel mViewModel;
    private ViewPager2 viewPager2;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private b1Fragment fragment1;

    private b3Fragment fragment3;
    private b4Fragment fragment4;
    private b5Fragment fragment5;
    private TabLayout tabLayout;

    public static BabbyFragment newInstance() {
        return new BabbyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.babby_fragment, container, false);

    }

    public class MyFragmentStateAdapter extends FragmentStateAdapter {

        private ArrayList<Fragment> fragments;

        public MyFragmentStateAdapter(@NonNull Fragment fragmentActivity, ArrayList<Fragment> fragments) {
            super(fragmentActivity);
            this.fragments = fragments;
        }
        @Override
        public int getItemCount() {
            return fragments.size();
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BabbyViewModel.class);
        // TODO: Use the ViewModel
        viewPager2=getActivity().findViewById(R.id.babby_viewpager);
        tabLayout=getActivity().findViewById(R.id.babby_tablayout);

        fragments=new ArrayList<>();
        fragment1=new b1Fragment();
        fragment3=new b3Fragment();
        fragment4=new b4Fragment();
        fragment5=new b5Fragment();
        fragments.add(fragment1);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);

        titles=new ArrayList<>();
        titles.add("尿液指标");
        titles.add("成长曲线");
        titles.add("隐藏疾病");
        titles.add("医生建议");

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        MyFragmentStateAdapter myFragmentStateAdapter=
                new MyFragmentStateAdapter(this,fragments);
        viewPager2.setAdapter(myFragmentStateAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        }).attach();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("amy",position+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

}