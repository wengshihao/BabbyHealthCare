package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button loginbutton;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        NavController navController= Navigation.findNavController(this,R.id.fragment);
        AppBarConfiguration configuration=new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,configuration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);*/

        loginbutton=findViewById(R.id.loginbtn);
        registerButton=findViewById(R.id.registerbtn);

        EditText usertxt=findViewById(R.id.idtxt);
        EditText passtxt=findViewById(R.id.passwordtxt);
        //hide bar
        //if (getSupportActionBar() != null){
         //   getSupportActionBar().hide();
        //}
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BabbyDatabase babbyDatabase= Room.databaseBuilder(getApplicationContext(),BabbyDatabase.class,"Babby_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                BabbyDao babbyDao=babbyDatabase.getBabbyDao();

                List<BabbyEntity> list=babbyDao.getall();
                String username=usertxt.getText().toString();
                String password=passtxt.getText().toString();
                System.out.println(username+'\n');
                System.out.println(password+'\n');

                int flag=0;
                for(int i=0;i<list.size();i++)
                {
                    if(list.get(i).getUsername().equals(username)&&list.get(i).getPassword().equals(password))
                    {
                        flag=1;
                        break;
                    }
                }

                if(flag==1)
                {
                    Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = null;
                    intent = new Intent(MainActivity.this, functionActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=null;
                intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}