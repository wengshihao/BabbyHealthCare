package com.example.testapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testapp.Search_Listview.FilterListener;
import com.example.testapp.Search_Listview.MyListAdapter;
import com.example.testapp.Search_Listview.Search;
import com.example.testapp.Search_Listview.SearchDao;
import com.example.testapp.Search_Listview.SearchDatabase;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class SearchFragment extends Fragment {

    private EditText mEt;
    private ListView mLv;
    private LiveData<List<Search>> list = null;
    //private SearchDao searchDao = null;
    //private SearchDatabase searchDatabase ;
    boolean isFilter;

    private SearchViewModel searchViewModel = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SearchViewModel mViewModel;
    private MyListAdapter adapter = null;


    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        View view =  inflater.inflate(R.layout.search_fragment, container, false);
        /*searchDatabase = Room.databaseBuilder(getActivity(),SearchDatabase.class,"search_database")
                //.fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        searchDao = searchDatabase.getSearchDao();*/
        setViews();// 控件初始化
        //View view =  View.inflater.inflate(R.layout.search_fragment, container, false);
        mEt = view.findViewById(R.id.search_editText);// EditText控件
        mLv = view.findViewById(R.id.search_listview);// ListView控件

        setData();// 给listView设置adapter

        setListeners();// 设置监听

        super.onCreate(savedInstanceState);


        //getActivity().setContentView(R.layout.search_fragment);





        return view;//inflater.inflate(R.layout.search_fragment, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);


        // TODO: Use the ViewModel
    }

    private void setData() {


        searchViewModel.getCount().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 0){
                    initData(getActivity());// 初始化数据
                }
            }
        });/*
        int amount = 0;
        amount = searchViewModel.getCount().getValue();
        searchViewModel.*/
        //Cursor c = searchViewModel.getSearchDatabase().rawQuery("select * from history_info", null);



        //searchViewModel.getAllSearchLive().list = searchDao.;
        //searchViewModel.setAllSearchLive(GetAllWorks());
        //searchViewModel.setAllSearchLive(searchViewModel.getSearchDao().GetAllWorks());



        //Log.e("list个数", String.valueOf(searchViewModel.getAllSearchLive().getValue().size()));
        // 这里创建adapter的时候，构造方法参数传了一个接口对象，这很关键，


        //Log.e("TAG", String.valueOf(searches.size()));
        //MyListAdapter adapter = null;
/*
        adapter = new MyListAdapter(list, getActivity(), new FilterListener() {
            // 回调方法获取过滤后的数据
            public void getFilterData(List<Search> list) {
                // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
                Log.e("TAGdddddddddddddd", String.valueOf(list.size()));

                setItemClick(list);
            }
        });
        mLv.setAdapter(adapter);*/
        //list= searchDao.GetAllWorks();
        searchViewModel.getAllSearchLive().observe(getActivity(), new Observer<List<Search>>() {
            @Override
            public void onChanged(List<Search> searches) {
                setItemClick(searches);
                Log.e("TAfffffffffffffffG", String.valueOf(searches.size()));
                //MyListAdapter adapter = null;
                adapter = new MyListAdapter(searches, getActivity(), new FilterListener() {
                    // 回调方法获取过滤后的数据
                    public void getFilterData(List<Search> list) {
                        // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
                        Log.e("TAG", "接口回调成功");

                        setItemClick(list);
                    }
                });
                mLv.setAdapter(adapter);
            }
        });

    }

    /**
     * 给listView添加item的单击事件
     * @param filter_lists  过滤后的数据集
     */

    //SearchDetail  dfragment = new SearchDetail();
    protected void setItemClick(List<Search> filter_lists) {
        Log.e("TAG", String.valueOf(filter_lists.size()));
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 点击对应的item时，弹出toast提示所点击的内容
                Log.e("TAG", String.valueOf(position));
                //NavController controller = Navigation.findNavController(view);
                //controller.navigate(R.id.action_masterFragment_to_detailFragment);
                //Intent intent=new Intent(SearchFragment.this,);
                //startActivityForResult(intent,0);
                Intent intent = new Intent();
                intent = new Intent(getActivity(), SearchDetail.class);

                //intent.put
                //intent.putStringArray("StringArray", new String[]{"呵呵","哈哈"});
                intent.putExtra("image", filter_lists.get(position).getBitmap());
                intent.putExtra("text", filter_lists.get(position).getContent());
                intent.putExtra("title", filter_lists.get(position).getTitle());


                Log.e("TAG", filter_lists.get(position).getTitle());
                startActivity(intent);


                //Toast.makeText(getActivity(), filter_lists.get(position).getContent(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 简单的list集合添加一些测试数据
     */

    private void initData(Context context) {

        //searchDao.DeleteAll();
        //new SearchViewModel.DeleteAllAsyncTask(searchDao).execute();
        searchViewModel.deleteAllWorksSearchs();


        InputStreamReader inputReader = null;
        List<String> Filename = new ArrayList<String>();


        Filename.add("01.txt");
        Filename.add("02.txt");
        Filename.add("03.txt");
        Filename.add("04.txt");
        Filename.add("05.txt");
        Filename.add("06.txt");
        Filename.add("07.txt");
        Filename.add("08.txt");
        Filename.add("09.txt");
        Filename.add("10.txt");

        Log.e("TAG", String.valueOf(Filename.size()));
        for(int i = 0; i < Filename.size(); i++){


            try {
                Bitmap bmp = null;
                switch (i){
                    case 0 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.first);
                        break;
                    case 1 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.second);
                        break;
                    case 2 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.third);
                        break;
                    case 3 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.fourth);
                        break;
                    case 4 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.fifth);
                        break;
                    case 5 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sixth);
                        break;
                    case 6 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.seventh);
                        break;
                    case 7 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.eighth);
                        break;
                    case 8 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ninth);
                        break;
                    case 9 : bmp = BitmapFactory.decodeResource(getResources(), R.drawable.tenth);
                        break;



                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);


                Search search = new Search();
                inputReader = new InputStreamReader(context.getResources().getAssets().open(Filename.get(i)));
                BufferedReader bufReader = new BufferedReader(inputReader);
                StringBuilder result = new StringBuilder();
                String line;



                search.setBitmap(baos.toByteArray());
                search.setTitle(bufReader.readLine());
                search.setImage(bufReader.readLine());

                Log.e("TAG", search.getTitle());

                while((line = bufReader.readLine()) != null) {
                    result.append(line);
                }
                String var6 = result.toString();
                search.setContent(var6);


                //searchDao.InsertWorks(search);
                //list = searchDao.GetAllWorks();
                //Log.e("llll",String.valueOf(list.size()));
                //new SearchViewModel.InsertAsyncTask(searchDao).execute(search);
                searchViewModel.insertSearchs(search);


            } catch (Exception var16) {
                var16.printStackTrace();
            } finally {
                if(null != inputReader) {
                    try {
                        inputReader.close();
                    } catch (IOException var15) {
                        var15.printStackTrace();
                    }
                }
            }
        }
        //Search search = new Search();
        //searchViewModel.insertSearchs(search);

        //searchViewModel.d
        //searchViewModel.setAllSearchLive(searchViewModel.getSearchDao().GetAllWorks());

    }



    /*
        private void initData() {

            list[0].add("看着飞舞的尘埃   掉下来");
            list[1].add("没人发现它存在");
            list[2].add("多自由自在");
            list[0].add("可世界都爱热热闹闹");
            list[1].add("容不下   我百无聊赖");
            list[2].add("不应该   一个人 发呆");
            list[0].add("只有我   守着安静的沙漠");
            list[1].add("等待着花开");
            list[2].add("只有我   看着别人的快乐");
        }
    */
    private void setListeners() {
        // 没有进行搜索的时候，也要添加对listView的item单击监听
        //setItemClick(list);

        /**
         * 对编辑框添加文本改变监听，搜索的具体功能在这里实现
         * 很简单，文本该变的时候进行搜索。关键方法是重写的onTextChanged（）方法。
         */
        mEt.addTextChangedListener(new TextWatcher() {

            /**
             *
             * 编辑框内容改变的时候会执行该方法
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
                if(adapter != null){
                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    /**
     * 控件初始化
     */
    private void setViews() {
//        View view =  View.inflater.inflate(R.layout.search_fragment, container, false);
//        mEt = view.findViewById(R.id.search_editText);// EditText控件
//        mLv = view.findViewById(R.id.search_listview);// ListView控件
    }


}