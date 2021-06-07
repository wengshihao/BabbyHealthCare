package com.example.testapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.renderer.LineChartRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link b3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class b3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public b3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment b3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static b3Fragment newInstance(String param1, String param2) {
        b3Fragment fragment = new b3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    private LineChart lineChart,lineChart2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b3, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView()
    {
        lineChart=getActivity().findViewById(R.id.babby_linechart1);
        lineChart2=getActivity().findViewById(R.id.babby_linechart2);

        showAlone();
        LineChartManager lineChartManager=new LineChartManager(lineChart2);
        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 11; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();

        List<Float> y1Value = new ArrayList<>();
        List<Float> y2Value = new ArrayList<>();

        List<String> lableNameList = new ArrayList<>();
        lableNameList.add("体重(kg)");
        lableNameList.add("身高(cm)");

        y1Value.add(2.9f);y1Value.add(3.6f);
        y1Value.add(4.3f);y1Value.add(5.0f);
        y1Value.add(5.7f);y1Value.add(6.3f);
        y1Value.add(6.9f);y1Value.add(7.8f);
        y1Value.add(8.6f);y1Value.add(9.1f);
        y1Value.add(9.8f);y1Value.add(10.3f);

        y2Value.add(48.2f);y2Value.add(52.1f);
        y2Value.add(55.5f);y2Value.add(58.5f);
        y2Value.add(61.0f);y2Value.add(63.2f);
        y2Value.add(65.1f);y2Value.add(68.3f);
        y2Value.add(71.0f);y2Value.add(73.4f);
        y2Value.add(76.6f);y2Value.add(79.4f);


        yValues.add(y1Value);
        yValues.add(y2Value);

        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#6785f2"));
        colorList.add(Color.parseColor("#eecc44"));
        lineChartManager.showLineChart(xValues, yValues, lableNameList, colorList);
        lineChartManager.setDescription("");


    }

    private void showAlone() {
        LineChartManager lineChartManager=new LineChartManager(lineChart);
        List<Float> xAxisValues=new ArrayList<>();
        List<Float> yAxisValues=new ArrayList<>();
        for(int i=0;i<=28;i++)
        {
            xAxisValues.add((float)(i));
        }
        Random random=new Random();
        for(int i=0;i<=28;i++)
        {
            int minn=65;
            int maxn=90;
            yAxisValues.add((float)(random.nextInt(maxn)%(maxn-minn+1) + minn)+random.nextFloat());
        }
        lineChartManager.showLineChart(xAxisValues,yAxisValues,"", Color.parseColor("#da6268"));
        lineChartManager.setDescription("");
    }


}