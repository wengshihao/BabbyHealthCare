package com.example.testapp.Search_Listview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends BaseAdapter implements Filterable {

    private List<Search> list = new ArrayList<>();
    private Context context;
    private MyFilter filter = null;// 创建MyFilter对象
    private FilterListener listener = null;// 接口对象

    public MyListAdapter(List<Search> list, Context context, FilterListener filterListener) {


        this.list = list;
        this.context = context;
        this.listener = filterListener;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.search_cell, null);
            holder = new ViewHolder();
            //holder.imageView = convertView.findViewById(R.id.search_imageView);
            holder.tvTitle = convertView.findViewById(R.id.search_Title);
            holder.tvNumber = convertView.findViewById(R.id.search_Number);
            holder.tvContent = convertView.findViewById(R.id.search_Content);
            holder.imageView = convertView.findViewById(R.id.search_Image);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }





        holder = (ViewHolder) convertView.getTag();
        holder.tvTitle.setText(list.get(position).getTitle());
        //holder.tvTime.setText(list[1].get(position));
        holder.tvContent.setText(list.get(position).getContent());
        holder.tvNumber.setText(String.valueOf(position+1));
        //holder.imageView.setImageURI(Uri.parse(list.get(position).getImage()));
        byte[] in = list.get(position).getBitmap();
        Bitmap bmp = BitmapFactory.decodeByteArray(in, 0, in.length );
        holder.imageView.setImageBitmap(bmp);
        //holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.first));



        //Log.e("TAG", list.get(position).getImage());
        //Glide.with(context).load(list.get(position).getImage()).into(holder.imageView);
        return convertView;
    }

    /**
     * 自定义MyAdapter类实现了Filterable接口，重写了该方法
     */
    @Override
    public Filter getFilter() {
        // 如果MyFilter对象为空，那么重写创建一个
        if (filter == null) {
            filter = new MyFilter(list);
        }
        return filter;
    }


    class MyFilter extends Filter {

        // 创建集合保存原始数据
        private List<Search> original = new ArrayList<>();



        public MyFilter(List<Search> list) {

            this.original = list;
        }

        /**
         * 该方法返回搜索过滤后的数据
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // 创建FilterResults对象
            FilterResults results = new FilterResults();

            /**
             * 没有搜索内容的话就还是给results赋值原始数据的值和大小
             * 执行了搜索的话，根据搜索的规则过滤即可，最后把过滤后的数据的值和大小赋值给results
             *
             */
            if(TextUtils.isEmpty(constraint)){
                results.values = original;
                results.count = original.size();
            }else {
                List<Search> mList=new ArrayList<>();



                // 创建集合保存过滤后的数据
                //List<String> mList = new ArrayList<String>();
                // 遍历原始数据集合，根据搜索的规则过滤数据
                //for(String s: original[0]){
                for(int i = 0; i < original.size(); i++){
                    // 这里就是过滤规则的具体实现【规则有很多，大家可以自己决定怎么实现】

                    if(original.get(i).getTitle().trim().toLowerCase().contains(constraint.toString().trim().toLowerCase())){
                        // 规则匹配的话就往集合中添加该数据
                        mList.add(original.get(i));
                        /*
                        mList[0].add(s.get(i));
                        s = original[1];
                        mList[1].add(s.get(i));
                        s = original[2];
                        mList[2].add(s.get(i));
                        s = original[0];*/
                    }
                }
                results.values = mList;
                results.count = mList.size();
            }

            // 返回FilterResults对象

            return results;
        }

        /**
         * 该方法用来刷新用户界面，根据过滤后的数据重新展示列表
         */
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // 获取过滤后的数据
            list = (List<Search>) results.values;
            // 如果接口对象不为空，那么调用接口中的方法获取过滤后的数据，具体的实现在new这个接口的时候重写的方法里执行
            if(listener != null){
                listener.getFilterData(list);
            }
            // 刷新数据源显示
            notifyDataSetChanged();
        }

    }

    class ViewHolder {
        private ImageView imageView;
        private TextView tvTitle,tvContent,tvNumber;
    }

}