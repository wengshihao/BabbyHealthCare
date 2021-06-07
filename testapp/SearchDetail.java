package com.example.testapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchDetail extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("TAfefdwfwefG", "hhhhhh");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        Intent intent = getIntent();


        imageView = findViewById(R.id.search_dimage);
        textView1 = findViewById(R.id.search_dtext);
        textView2 = findViewById(R.id.search_dtitle);


        byte[] in = (byte[])intent.getSerializableExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(in, 0, in.length );
        imageView.setImageBitmap(bmp);

        textView1.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView1.setText(intent.getStringExtra("text"));


        Log.e("TAG", intent.getStringExtra("text"));

        textView2.setText(intent.getStringExtra("title"));

    }
}