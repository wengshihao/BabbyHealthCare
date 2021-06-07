package com.example.testapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Entity;
import androidx.room.Room;

import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link b1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class b1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public b1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of    \
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment b1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static b1Fragment newInstance(String param1, String param2) {
        b1Fragment fragment = new b1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    BabbyViewModel babbyViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b1, container, false);

    }





    //处理雷达图
    private RadarChart radar;
    List<RadarEntry> list_standard;//标准指标
    List<RadarEntry> list_now;//目前指标、
    //PH正常6
    //尿尿次数7
    //白细胞正常3
    //红细胞正常4
    //尿蛋白正常4
    public static String dd1="",dd2="",dd3="";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        radar= getActivity().findViewById(R.id.b1Radar);
        list_standard=new ArrayList<>();
        list_now=new ArrayList<>();
        float PHStandard=(float) 6.2;float niaoTimesStandard=(float)7.3;float whiteCellStandard=(float)3.5;
        float redCellStandard=(float)4.3;float eggStandard=(float)4.7;
        list_standard.add(new RadarEntry(PHStandard));
        list_standard.add(new RadarEntry(niaoTimesStandard));
        list_standard.add(new RadarEntry(whiteCellStandard));
        list_standard.add(new RadarEntry(redCellStandard));
        list_standard.add(new RadarEntry(eggStandard));

        Random random=new Random();
        int maxn,minn;
        maxn=8;minn=4;
        float PHNow=(float)(random.nextInt(maxn)%(maxn-minn+1) + minn)+random.nextFloat();
        maxn=10;minn=4;
        float niaoTimesNow=(float)(random.nextInt(maxn)%(maxn-minn+1) + minn)+random.nextFloat();
        maxn=5;minn=1;
        float whiteCellNow=(float)(random.nextInt(maxn)%(maxn-minn+1) + minn)+random.nextFloat();
        maxn=6;minn=2;
        float redCellNow=(float)(random.nextInt(maxn)%(maxn-minn+1) + minn)+random.nextFloat();
        maxn=6;minn=2;
        float eggNow=(float)(random.nextInt(maxn)%(maxn-minn+1) + minn)+random.nextFloat();


        list_now.add(new RadarEntry(PHNow));
        list_now.add(new RadarEntry(niaoTimesNow));
        list_now.add(new RadarEntry(whiteCellNow));
        list_now.add(new RadarEntry(redCellNow));
        list_now.add(new RadarEntry(eggNow));

        RadarDataSet radarDataSet_standard=new RadarDataSet(list_standard,"正常值");
        radarDataSet_standard.setColor(Color.BLUE);
        RadarDataSet radarDataSet_now=new RadarDataSet(list_now,"当前值");
        radarDataSet_now.setColor(Color.RED);

        RadarData radarData=new RadarData(radarDataSet_now);
        radarData.addDataSet(radarDataSet_standard);
        radar.setData(radarData);

        radar.getYAxis().setAxisMinimum(0);
        radar.setWebColor(Color.BLACK);
        radar.setWebColorInner(Color.BLACK);
        radar.setBackgroundColor(Color.WHITE);

        XAxis xAxis=radar.getXAxis();
        xAxis.setTextColor(Color.BLACK);//X轴字体颜色
        xAxis.setTextSize(12);     //X轴字体大小

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v) {
                if (v==0){
                    return "PH";
                }
                if (v==1){
                    return "尿尿次数";
                }
                if (v==2){
                    return "白细胞";
                }
                if (v==3){
                    return "红细胞";
                }
                if (v==4){
                    return "尿蛋白";
                }
                return "";
            }
        });
        YAxis yAxis=radar.getYAxis();
        //是否绘制Y轴坐标点  和雷达框数据一般不同时存在 否则显得很挤 默认为true
        yAxis.setDrawLabels(false);

        radarDataSet_now.setValueTextSize(8);
        radarDataSet_standard.setValueTextSize(8);

        yAxis.setLabelCount(8,false);

        //对于右下角一串字母的操作
        radar.getDescription().setEnabled(false);                  //是否显示右下角描述
        radar.getDescription().setText("这是修改那串英文的方法");    //修改右下角字母的显示
        radar.getDescription().setTextSize(20);                    //字体大小
        radar.getDescription().setTextColor(Color.CYAN);             //字体颜色

        //图例
        Legend legend=radar.getLegend();
        legend.setEnabled(true);    //是否显示图例
        legend.setTextSize(16f);
        legend.setFormSize(20);
        legend.setForm(Legend.LegendForm.SQUARE);

        radarDataSet_now.setDrawFilled(true); // 绘制填充，默认为false
        radarDataSet_now.setFillColor(Color.RED); // 填充颜色
        radarDataSet_now.setFillAlpha(40); // 填充内容透明度

        radarDataSet_standard.setDrawFilled(true);
        radarDataSet_standard.setFillColor(Color.BLUE); // 填充颜色
        radarDataSet_standard.setFillAlpha(40); // 填充内容透明度

        DecimalFormat df = new DecimalFormat("0.00");

        TextView babby_text1=getActivity().findViewById(R.id.babby_txv1);
        babby_text1.getPaint().setFakeBoldText(true);
        TextView babby_text2=getActivity().findViewById(R.id.babby_txv2);
        babby_text2.getPaint().setFakeBoldText(true);
        TextView babby_text3=getActivity().findViewById(R.id.babby_txv3);
        babby_text3.getPaint().setFakeBoldText(true);
        TextView babby_text4=getActivity().findViewById(R.id.babby_txv4);
        babby_text4.getPaint().setFakeBoldText(true);
        TextView babby_text5=getActivity().findViewById(R.id.babby_txv5);
        babby_text5.getPaint().setFakeBoldText(true);

        ///////////////////////
        dd1="";dd2="";dd3="";
        ImageView iv1=getActivity().findViewById(R.id.biv1);
        ImageView iv2=getActivity().findViewById(R.id.biv2);
        ImageView iv3=getActivity().findViewById(R.id.biv3);
        ImageView iv4=getActivity().findViewById(R.id.biv4);
        ImageView iv5=getActivity().findViewById(R.id.biv5);

        //////////////////////


        //第一个text
        String babby_text1String;
        if(PHNow+1<PHStandard)
        {
            String e = df.format(((PHStandard-PHNow-1)/PHStandard)*100);
            babby_text1String="您的宝宝尿液PH偏小"+"偏离正常范围:"+e+"%";
            babby_text1.setTextColor(getResources().getColor(R.color.babby1_yello));
            dd1+="您的宝宝尿液PH偏小,建议多吃蔬菜水果。";
            iv1.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        }
        else if(PHNow-1>PHStandard)
        {
            String e = df.format(((PHNow-PHStandard-1)/PHStandard)*100);
            babby_text1String="您的宝宝尿液PH偏大"+"偏离正常范围:"+e+"%";
            babby_text1.setTextColor(getResources().getColor(R.color.babby1_red));
            dd1+="您的宝宝尿液PH偏大,建议多运动。";
            iv1.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        }
        else
        {
            babby_text1String="您的宝宝尿液PH属于正常范围";
            babby_text1.setTextColor(getResources().getColor(R.color.babby1_green));
            dd1+="您的宝宝尿液PH属于正常范围。";
            iv1.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24);
        }
        babby_text1.setText(babby_text1String);

        //第二个text
        String babby_text2String;
        if(niaoTimesNow+1<niaoTimesStandard)
        {
            String e = df.format(((niaoTimesStandard-niaoTimesNow-1)/niaoTimesStandard)*100);
            babby_text2String="您的宝宝尿尿次数偏小"+"偏离正常范围:"+e+"%";
            babby_text2.setTextColor(getResources().getColor(R.color.babby1_yello));
            dd1+="您的宝宝尿尿次数偏小,建议多喝水。";
            iv2.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        }
        else if(niaoTimesNow-1>niaoTimesStandard)
        {
            String e = df.format(((niaoTimesNow-niaoTimesStandard-1)/niaoTimesStandard)*100);
            babby_text2String="您的宝宝尿尿次数偏大"+"偏离正常范围:"+e+"%";
            babby_text2.setTextColor(getResources().getColor(R.color.babby1_red));
            dd1+="您的宝宝尿尿次数偏多,建议多休息,避免过度劳累。";
            iv2.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        }
        else
        {
            babby_text2String="您的宝宝尿尿次数属于正常范围";
            babby_text2.setTextColor(getResources().getColor(R.color.babby1_green));
            dd1+="您的宝宝尿尿次数属于正常范围。";
            iv2.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24);
        }
        babby_text2.setText(babby_text2String);

        //第三个text
        String babby_text3String;
        if(whiteCellNow+1<whiteCellStandard)
        {
            String e = df.format(((whiteCellStandard-whiteCellNow-1)/whiteCellStandard)*100);
            babby_text3String="您的宝宝尿液白细胞偏小"+"偏离正常范围:"+e+"%";
            babby_text3.setTextColor(getResources().getColor(R.color.babby1_yello));
            dd2+="您的宝宝白细胞偏少，有感染倾向，建议最好是能够做个血细胞形态学检查，平时要注意锻炼身体。";
            iv3.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        }
        else if(whiteCellNow-1>whiteCellStandard)
        {
            String e = df.format(((whiteCellNow-whiteCellStandard-1)/whiteCellStandard)*100);
            babby_text3String="您的宝宝尿液白细胞偏大"+"偏离正常范围:"+e+"%";
            babby_text3.setTextColor(getResources().getColor(R.color.babby1_red));
            dd2+="您的宝宝白细胞偏多，存在细菌感染可能，如果宝宝正在发烧，请及时就诊。";
            iv3.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        }
        else
        {
            babby_text3String="您的宝宝尿液白细胞属于正常范围";
            babby_text3.setTextColor(getResources().getColor(R.color.babby1_green));
            dd2+="您的宝宝尿液白细胞属于正常范围。";
            iv3.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24);
        }
        babby_text3.setText(babby_text3String);

        //第四个text
        String babby_text4String;
        if(redCellNow+1<redCellStandard)
        {
            String e = df.format(((redCellStandard-redCellNow-1)/redCellStandard)*100);
            babby_text4String="您的宝宝尿液红细胞偏小"+"偏离正常范围:"+e+"%";
            babby_text4.setTextColor(getResources().getColor(R.color.babby1_yello));
            dd2+="您的宝宝红细胞偏少，存在贫血可能性。";
            iv4.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);

        }
        else if(redCellNow-1>redCellStandard)
        {
            String e = df.format(((redCellNow-redCellStandard-1)/redCellStandard)*100);
            babby_text4String="您的宝宝尿液红细胞偏大"+"偏离正常范围:"+e+"%";
            babby_text4.setTextColor(getResources().getColor(R.color.babby1_red));
            dd2+="您的宝宝红细胞偏大，有泌尿道黏膜的损伤的可能,建议及时就诊。";
            iv4.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        }
        else
        {
            babby_text4String="您的宝宝尿液红细胞属于正常范围";
            babby_text4.setTextColor(getResources().getColor(R.color.babby1_green));
            dd2+="您的宝宝尿液红细胞属于正常范围。";
            iv4.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24);
        }
        babby_text4.setText(babby_text4String);

        //第四个text
        String babby_text5String;
        if(eggNow+1<eggStandard)
        {
            String e = df.format(((eggNow-eggStandard-1)/eggStandard)*100);
            babby_text5String="您的宝宝尿蛋白偏小"+"偏离正常范围:"+e+"%";
            babby_text5.setTextColor(getResources().getColor(R.color.babby1_yello));
            dd3+="您的宝宝红细胞偏少，存在贫血可能性。";
            iv5.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);

        }
        else if(eggNow-1>eggStandard)
        {
            String e = df.format(((eggNow-eggStandard-1)/eggStandard)*100);
            babby_text5String="您的宝宝尿蛋白偏大"+"偏离正常范围:"+e+"%";
            babby_text5.setTextColor(getResources().getColor(R.color.babby1_red));
            dd3+="您的宝宝尿蛋白偏高，肾小球功能异常,建议去医院进行进一步检查。";
            iv5.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        }
        else
        {
            babby_text5String="您的宝宝尿蛋白属于正常范围";
            babby_text5.setTextColor(getResources().getColor(R.color.babby1_green));
            dd3+="您的宝宝尿蛋白属于正常范围。";
            iv5.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24);
        }
        babby_text5.setText(babby_text5String);

        BabbyDatabase babbyDatabase=Room.databaseBuilder(getContext(),BabbyDatabase.class,"Babby_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        BabbyDao babbyDao=babbyDatabase.getBabbyDao();
       //babbyDao.deleteAll();




    }
}