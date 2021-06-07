package com.example.testapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link b4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class b4Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public b4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment b4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static b4Fragment newInstance(String param1, String param2) {
        b4Fragment fragment = new b4Fragment();
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
    private PieChart pc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b4, container, false);

    }
    private float jiankang,fuxie,fare,bianmi,niaodaoyan;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pc=getActivity().findViewById(R.id.babby_pie);
        jiankang=71f;
        fuxie=10f;
        fare=12f;
        bianmi=4f;
        niaodaoyan=3f;
        String s1="";
        TextView babby4_text=getActivity().findViewById(R.id.babby_pie_tv);
        ForegroundColorSpan greenSpan = new ForegroundColorSpan(Color.parseColor("#00FF33"));
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#FF0033"));
        ForegroundColorSpan redSpan2 = new ForegroundColorSpan(Color.parseColor("#FF0033"));
        ForegroundColorSpan redSpan3 = new ForegroundColorSpan(Color.parseColor("#FF0033"));
        ForegroundColorSpan blackSpan = new ForegroundColorSpan(Color.parseColor("#000000"));
        if(jiankang>60)
        {
            s1+="您的宝宝目前身体健康\n但仍有";
            if(fuxie==Math.max(fuxie,Math.max(fare,Math.max(bianmi,niaodaoyan))))s1+=fuxie+"%概率有腹泻的风险";
            if(fare==Math.max(fuxie,Math.max(fare,Math.max(bianmi,niaodaoyan))))s1+=fare+"%概率有发热的风险";
            if(bianmi==Math.max(fuxie,Math.max(fare,Math.max(bianmi,niaodaoyan))))s1+=bianmi+"%概率有便秘的风险";
            if(niaodaoyan==Math.max(fuxie,Math.max(fare,Math.max(bianmi,niaodaoyan))))s1+=niaodaoyan+"%概率有尿道炎的风险";
            SpannableStringBuilder builder = new SpannableStringBuilder(s1);

            builder.setSpan(greenSpan, 6, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(redSpan, 14, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            babby4_text.setText(builder);

        }
        else
        {
            s1+="您的宝宝目前身体状况不容乐观\n有";
            if(fuxie==Math.max(fuxie,Math.max(fare,Math.max(bianmi,niaodaoyan))))s1+=fuxie+"%概率有腹泻的风险";
            if(fare==Math.max(fuxie,Math.max(fare,Math.max(bianmi,niaodaoyan))))s1+=fare+"%概率有发热的风险";
            if(bianmi==Math.max(fuxie,Math.max(fare,Math.max(bianmi,niaodaoyan))))s1+=bianmi+"%概率有便秘的风险";
            if(niaodaoyan==Math.max(fuxie,Math.max(fare,Math.max(bianmi,niaodaoyan))))s1+=niaodaoyan+"%概率有尿道炎的风险";
            s1+="\n请尽快就医";

            SpannableStringBuilder builder = new SpannableStringBuilder(s1);
            builder.setSpan(redSpan, 10, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(redSpan2, 16, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(redSpan3, 29, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            babby4_text.setText(builder);
        }

        loadData();
        setLegend();
    }


    public void setLegend()
    {
        Legend legend=pc.getLegend();
        legend.setTextSize(16f);
        legend.setTextColor(R.color.babby1_green);
        legend.setFormSize(20);

    }


    private void loadData()
    {
        //不显示描述/标题
        pc.getDescription().setEnabled(false);

        //设置上边和下边的偏移量
        pc.setExtraTopOffset(30);
        pc.setExtraBottomOffset(30);
        //数据源
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(jiankang,"健康"));
        entries.add(new PieEntry(fuxie,"腹泻"));
        entries.add(new PieEntry(fare,"发热"));
        entries.add(new PieEntry(bianmi,"便秘"));
        entries.add(new PieEntry(niaodaoyan,"尿道炎"));
        //添加到PieDataSet中
        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setColors(getResources().getColor(R.color.babby4_blue),getResources().getColor(R.color.babby1_green),
                getResources().getColor(R.color.babby1_red),
                getResources().getColor(R.color.purple_500),
                getResources().getColor(R.color.purple_200));

        //设置连接线的长度，一般取0-1的值即可
        pieDataSet.setValueLinePart1Length(2f);
        pieDataSet.setValueLineWidth(4f);
        pieDataSet.setValueFormatter(new ValueFormatter() {
            private int indd = -1;
            @Override
            public String getFormattedValue(float value) {

                return value + "%";
            }
        });
        //设置饼块之间的间隔
        pieDataSet.setSliceSpace(3f);
        //设置点击某一饼快多出来的距离
        pieDataSet.setSelectionShift(15f);
        pieDataSet.setValueTextSize(16f);//设置在饼图上显示文字的大小

        //可用过该属性可以设置是否绘制连接线
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        //设置饼图上的文字是绘制在内部还是外部
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        pieDataSet.setValueLineVariableLength(true);//设置数据连接线的长度是否可变
        //设置连接线距离饼图的距离，注意为百分数，1-100
        pieDataSet.setValueLinePart1OffsetPercentage(80f);
        //设置连接线的长度，一般取0-1的值即可
        pieDataSet.setValueLinePart1Length(0.8f);
        //设置连接线的宽度
        pieDataSet.setValueLineWidth(2f);


        //设置连接线距离饼图的距离，为百分数
        pieDataSet.setValueLinePart1OffsetPercentage(100f);
        pc.setDrawEntryLabels(false);
        //不绘制空洞
        pc.setDrawHoleEnabled(false);
        //不可旋转
        pc.setRotationEnabled(true);
        PieData pieData = new PieData(pieDataSet);
        //设置数据
        pc.setData(pieData);

    }

}