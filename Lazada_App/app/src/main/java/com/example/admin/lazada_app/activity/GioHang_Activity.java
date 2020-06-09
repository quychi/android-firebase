package com.example.admin.lazada_app.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.lazada_app.Interfaces.inter;
import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.adapter.GioHangAdapter;
import com.example.admin.lazada_app.model.GioHang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHang_Activity extends AppCompatActivity implements inter, View.OnClickListener {

    private Toolbar toolbar;
    private Button btn_thanhtoan, btn_tieptuc;
    private TextView txt_tong;
    private GioHangAdapter gioHangAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang_);
        AnhXa();
        ActionBar();
        TongTien();
    }

    private void TongTien() {
        long sum = 0;
        ArrayList<GioHang> list = gioHangAdapter.getList();
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i).getGiamoi();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txt_tong.setText("Tổng Tiền : " + decimalFormat.format(sum) + " Đ");
    }

    private void ActionBar() {
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
        toolbar = findViewById(R.id.giohang_toolbar);
        btn_thanhtoan = findViewById(R.id.giohang_btn_thanhtoan);
        btn_tieptuc = findViewById(R.id.giohang_btn_ttmua);
        txt_tong = findViewById(R.id.giohang_txt_tong);
        gioHangAdapter = new GioHangAdapter(MainActivity.List_cart, getApplicationContext(), this);
        recyclerView = findViewById(R.id.giohang_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(gioHangAdapter);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        btn_thanhtoan.setOnClickListener(this);
        btn_tieptuc.setOnClickListener(this);

    }

    @Override
    public void Add(int position) {
        GioHang gioHang = MainActivity.List_cart.get(position);
        gioHang.setGiamoi(gioHang.getGiamoi() + gioHang.getGiamoi() / gioHang.getSoluong());
        gioHang.setSoluong(gioHang.getSoluong() + 1);
        MainActivity.List_cart.set(position, gioHang);
        gioHangAdapter.setList(MainActivity.List_cart);
        gioHangAdapter.notifyDataSetChanged();
        TongTien();
    }

    @Override
    public void Sub(int position) {
        GioHang gioHang = MainActivity.List_cart.get(position);
        gioHang.setGiamoi(gioHang.getGiamoi() - gioHang.getGiamoi() / gioHang.getSoluong());
        gioHang.setSoluong(gioHang.getSoluong() - 1);
        MainActivity.List_cart.set(position, gioHang);
        gioHangAdapter.setList(MainActivity.List_cart);
        gioHangAdapter.notifyDataSetChanged();
        TongTien();
    }

    @Override
    public void Delete(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setIcon(R.drawable.del)
                .setTitle("Xóa mặt hàng")
                .setMessage("Bạn Có Chắc Chắn Muốn Xóa Không");
        alertDialog.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //NO TO DO...
            }
        });
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.List_cart.remove(position);
                gioHangAdapter.setList(MainActivity.List_cart);
                gioHangAdapter.notifyDataSetChanged();
                Toast.makeText(GioHang_Activity.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                long sum = 0;
                for (int i = 0; i < MainActivity.List_cart.size(); i++) {
                    sum = sum + MainActivity.List_cart.get(i).getGiamoi();
                }
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

                txt_tong.setText("Tổng Tiền : " + decimalFormat.format(sum) + " Đ");
            }
        });
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.giohang_btn_thanhtoan:
                if (MainActivity.List_cart.size() == 0)
                    Toast.makeText(this, "Giỏ Hàng Của Bạn Đang Trống", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent2 = new Intent(GioHang_Activity.this, Thongtinkhachhang.class);
                    startActivity(intent2);
                }
                break;
            case R.id.giohang_btn_ttmua:
                Intent intent = new Intent(GioHang_Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
