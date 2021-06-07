package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText usernametxt=findViewById(R.id.regidtxt);
        EditText passwordtxt=findViewById(R.id.regpasswordtxt);
        EditText cfmpasswordtxt=findViewById(R.id.regcfmpasswordtxt);



        Button regbtn=findViewById(R.id.regbtn);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=usernametxt.getText().toString();
                String password=passwordtxt.getText().toString();
                String cfmpassword=cfmpasswordtxt.getText().toString();
                System.out.println(username+"\n"+password+'\n'+cfmpassword);
                if(password.equals("")||username.equals("")||cfmpassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(cfmpassword))
                {
                    Toast.makeText(getApplicationContext(),"两遍密码不一致",Toast.LENGTH_SHORT).show();
                }
                else
                {


                    BabbyDatabase babbyDatabase= Room.databaseBuilder(getApplicationContext(),BabbyDatabase.class,"Babby_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                    BabbyDao babbyDao=babbyDatabase.getBabbyDao();
                    List<BabbyEntity> list=babbyDao.getall();
                    int  flag=1;
                    for(int i=0;i<list.size();i++)
                    {
                        if(list.get(i).getUsername().equals(username))
                        {
                            flag=0;
                            break;
                        }
                    }
                    if(flag==1) {
                        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                        babbyDao.insert(new BabbyEntity(username, password, 1f, 1f, 1f, 1f, 1f));
                        Intent intent = null;
                        intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "用户名已存在，请换一个", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}