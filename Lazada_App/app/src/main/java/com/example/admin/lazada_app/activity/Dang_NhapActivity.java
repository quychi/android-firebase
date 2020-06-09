package com.example.admin.lazada_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.model.Khachhang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Dang_NhapActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_dk,btn_dn;
    private ArrayList<Khachhang> list;
    private EditText edt_acount,edt_pass;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang__nhap);
        btn_dk=findViewById(R.id.btn_dk);
        btn_dn=findViewById(R.id.btn_dn);
        btn_dn.setOnClickListener(this);
        edt_acount=findViewById(R.id.dangnhap_acount);
        edt_pass=findViewById(R.id.dangnhap_pass);
        btn_dk.setOnClickListener(this);
        list=new ArrayList<>();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dk:
                Intent intent=new Intent(Dang_NhapActivity.this,Dang_KyActivity.class);
                intent.putExtra("key_list",list);
                startActivity(intent);
                break;
            case R.id.btn_dn:
                if(edt_pass.getText().toString().length()>0&&edt_acount.getText().toString().length()>0){

                    //login with FirebaseAuth
                     mFirebaseAuth = FirebaseAuth.getInstance();
                    loginUser(edt_acount.getText().toString(), edt_pass.getText().toString());
                }
                else
                    Toast.makeText(this, "Lỗi đăng nhập! Bạn phải nhập đầy đủ tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void loginUser(String email, String password) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (edt_acount.getText().toString().equals("a@gmail.com")){                //admin
                                Intent intent1=new Intent(Dang_NhapActivity.this,MainAdminActivity.class);
                                startActivity(intent1);
                            }
                            else {
                                Intent intent1 = new Intent(Dang_NhapActivity.this, MainActivity.class);
                                startActivity(intent1);
                            }
                            finish();
                        } else {
                            Toast.makeText(Dang_NhapActivity.this, "Lỗi đăng nhập " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
