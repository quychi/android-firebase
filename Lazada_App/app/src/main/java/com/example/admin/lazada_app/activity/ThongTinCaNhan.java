package com.example.admin.lazada_app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admin.lazada_app.Dialogs.MyDialog;
import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.model.Khachhang;

import static com.example.admin.lazada_app.activity.Dang_KyActivity.kh_dn;

//import static com.example.admin.lazada_app.activity.Dang_KyActivity.kh_dn;

public class ThongTinCaNhan extends AppCompatActivity {

    private TextView txt_acount,txt_hoten,txt_gmail,txt_sdt,txt_diachi;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        AnhXa();
        Action_toolbar();
        txt_sdt.setText("Số điện Thoại : "+kh_dn.getSdt());
        txt_hoten.setText("Họ Tên : "+kh_dn.getHoten());
        txt_gmail.setText("Gmail : "+kh_dn.getHoten());
        txt_diachi.setText("Địa Chỉ : "+kh_dn.getDiachi());
        txt_acount.setText("Acount : "+kh_dn.getAcount());




    }

    private void Action_toolbar() {
        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        txt_acount=findViewById(R.id.ttcn_acount);
        txt_diachi=findViewById(R.id.ttcn_diachi);
        txt_gmail=findViewById(R.id.ttcn_gmail);
        txt_hoten=findViewById(R.id.ttcn_hoten);
        txt_sdt=findViewById(R.id.ttcn_sdt);
        toolbar=findViewById(R.id.toolbar_thongtincanhan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_ttcn,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            onBackPressed();
        else if(item.getItemId()==R.id.ttcn_edit){
            MyDialog myDialog=new MyDialog(this);
            myDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
