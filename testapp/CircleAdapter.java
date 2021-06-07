package com.example.testapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.CircleViewHolder> {

    List<CircleEntity> allCircleEntity = new ArrayList<>();

    public void setAllCircleEntity(List<CircleEntity> allCircleEntity) {
        this.allCircleEntity = allCircleEntity;
    }

    @NonNull
    @Override
    public CircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater circlelayoutInflater = LayoutInflater.from(parent.getContext());
        View circleitemView = circlelayoutInflater.inflate(R.layout.circle_cell_card,parent,false);
        return new CircleViewHolder(circleitemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleViewHolder holder, int position) {
        CircleEntity circleEntity = allCircleEntity.get(position);
        holder.textViewTitle.setText(circleEntity.getText());

        if(circleEntity.getCircleFlag()==1){
            holder.circleTouxiang.setImageResource(R.drawable.circle_mytouxiang);
            holder.textViewName.setText("洋洋");
            holder.textViewTime.setText("刚刚");
        }
        else{
            Random random = new Random();
            int now = Math.abs(random.nextInt()%3);
            holder.circleSrc = "circle_touxiang_" + String.valueOf(now);
            holder.textViewName.setText(holder.circle_name_list[now]);
            holder.textViewTime.setText(holder.circle_time_list[Math.abs(random.nextInt()%7)]);
            int Rid=holder.itemView.getResources().getIdentifier(holder.circleSrc,"drawable","com.example.testapp");
            holder.circleTouxiang.setBackgroundResource(Rid);
        }


    }


    @Override
    public int getItemCount() {
        return allCircleEntity.size();
    }


    static class CircleViewHolder extends RecyclerView.ViewHolder{
        TextView textViewId,textViewTitle,textViewName,textViewTime;
        ImageButton circleImageButton;
        ImageView circleTouxiang;
        String circleSrc;
        int circle_flag;

        String circle_name_list[] = {
                "小泽",
                "芥子木",
                "WeekOne"
        };

        String circle_time_list[] = {
                "今天 03:37",
                "昨天 19:04",
                "昨天 18:59",
                "昨天 17:56",
                "昨天 17:00",
                "昨天 13:34",
                "昨天 08:01"
        };

        public CircleViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.circle_textview_card_title);
            circleImageButton = itemView.findViewById(R.id.circle_imageView_card_praise);
            circleImageButton.setBackgroundResource(R.drawable.circle_praise_0);
            circleTouxiang = itemView.findViewById(R.id.circle_touxiang);
            textViewName = itemView.findViewById(R.id.circle_name);
            textViewTime = itemView.findViewById(R.id.circle_time);





            circle_flag = 0;

            circleImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(circle_flag==0) {
                        circleImageButton.setImageResource(R.drawable.circle_praise_1);
                        circleImageButton.setColorFilter(Color.RED);

                    }
                    else
                    {
                        circleImageButton.setImageResource(R.drawable.circle_praise_0);
                        circleImageButton.setColorFilter(Color.BLACK);
                    }
                    circle_flag = ~circle_flag;
                }
            });

        }

        interface setOnClickListener
        {
            void Onclick(String s);
        }
        private setOnClickListener msetOnClickListemer;

        public void setItemClickListener(setOnClickListener s1)
        {
            msetOnClickListemer = s1;
        }


    }
}
