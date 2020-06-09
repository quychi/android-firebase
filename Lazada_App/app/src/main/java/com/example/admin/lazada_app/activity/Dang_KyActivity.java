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

public class Dang_KyActivity extends AppCompatActivity {

    private EditText edt_hoten, edt_sdt, edt_gmail, edt_diachi, edt_acount, edt_pass;
    private Button btn_dk;
    private ArrayList<Khachhang> list;
    public static Khachhang kh_dn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang__ky);
        Init();

    }

    private void Init() {
        edt_diachi = findViewById(R.id.dk_edt_dc);
        edt_gmail = findViewById(R.id.dk_edt_gmail);
        edt_hoten = findViewById(R.id.dk_edt_hoten);
        edt_sdt = findViewById(R.id.dk_edt_sdt);
        edt_acount = findViewById(R.id.dk_edt_acount);
        edt_pass = findViewById(R.id.dk_edt_pass);
        btn_dk = findViewById(R.id.dk_btn_dk);
        list = (ArrayList<Khachhang>) getIntent().getSerializableExtra("key_list");
        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_acount.getText().length() > 0 && edt_pass.getText().length() > 0 && edt_gmail.getText().length() > 0
                        && edt_diachi.getText().length() > 0 && edt_sdt.getText().length() > 0 && edt_hoten.getText().length() > 0) {
                    boolean check=false;
                    for (Khachhang kh : list) {
                        if (edt_acount.getText().toString().equals(kh.getAcount())) {
                            check=true;
                            Toast.makeText(Dang_KyActivity.this, "Tài Khoản này đã có người dùng mời bạn nhập lại!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (!check){

//                      register with Firebase
                        mAuth = FirebaseAuth.getInstance();
                        RegisterUser(edt_gmail.getText().toString().trim(), edt_pass.getText().toString().trim());
                    }
                }
                else
                    Toast.makeText(Dang_KyActivity.this, "Bạn Phải Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    Register user with Firebase
    public void RegisterUser(String Email, String Password){
        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            //check if successful
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Dang_KyActivity.this, MainActivity.class);
                                kh_dn=new Khachhang(edt_acount.getText().toString(),edt_pass.getText().toString(),edt_hoten.getText().toString(),
                                        edt_sdt.getText().toString(),edt_gmail.getText().toString(),edt_diachi.getText().toString());
                                startActivity(intent);
                                Toast.makeText(Dang_KyActivity.this, "Bạn đã đăng ký tài khoản thành công" +
                                        "\nMời Bạn Mua Hàng", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(Dang_KyActivity.this, "Loi dang ky: "+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
}
