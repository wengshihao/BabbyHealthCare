package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

public class CircleFragment2 extends Fragment {

    ImageView circleMytouxiang;
    EditText circleEditText;
    Button circleButton_finish;

    public CircleFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.circle_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        circleMytouxiang = getView().findViewById(R.id.circle_mytouxiang);
        circleMytouxiang.setImageResource(R.drawable.circle_mytouxiang);
        circleEditText = getView().findViewById(R.id.circle_mytext);
        circleButton_finish = getView().findViewById(R.id.circle_finish);
        circleButton_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String circleString=circleEditText.getText().toString();
                if(circleString.equals("")){
                    Toast.makeText(getContext(),"输入不能为空",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),"发表成功",Toast.LENGTH_SHORT).show();
                    CircleDatabase circleDatabase =Room.databaseBuilder(getContext(), CircleDatabase.class,"circle_database").allowMainThreadQueries().build();
                    CircleDao circleDao=circleDatabase.getCircleDao();
                    circleDao.insert(new CircleEntity(circleString,1));
                    NavController circleNavController = Navigation.findNavController(v);
                    circleNavController.navigate(R.id.action_circleFragment2_to_circleFragment);
                }
            }
        });

    }
}