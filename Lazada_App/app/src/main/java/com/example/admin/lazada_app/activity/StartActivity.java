package com.example.admin.lazada_app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.lazada_app.R;

//import static com.example.admin.lazada_app.activity.MainActivity.check_DangNhap;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        if (check_DangNhap){
//            Intent intent=new Intent(StartActivity.this,MainActivity.class);
//            startActivity(intent);
//        }
//        else
//        {
//            Intent intent=new Intent(StartActivity.this,Dang_NhapActivity.class);
//            startActivity(intent);
//        }
    }
}
