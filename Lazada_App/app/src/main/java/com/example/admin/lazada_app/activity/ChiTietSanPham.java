package com.example.admin.lazada_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.adapter.NhanXetAdapter;
import com.example.admin.lazada_app.model.GioHang;
import com.example.admin.lazada_app.model.NhanXet;
import com.example.admin.lazada_app.model.Sanpham;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPham extends AppCompatActivity {

    private Sanpham sanpham;
    private Toolbar toolbar;
    private TextView txt_mota,txt_tensp,txt_gia;
    private ImageView imageView;
    private Spinner spinner;
    private Button btn_add;
    private Button btn_luu;
    private EditText edtNhanXet;

    private List<NhanXet> nxList = new ArrayList<>();         //tao comment section
    private RecyclerView recyclerView;
    private NhanXetAdapter nxAdapter;

    private DatabaseReference mData;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        AnhXa();//ánh xạ các thành phần bên xml
        ActionToolbar();// xử lí toolbar
        GetData();// lấy dữ liệu
        EventButton_Add();
        saveNhanXet();

        prepareNhanXetData();             //load du lieu nhan xet

        nxAdapter = new NhanXetAdapter(this,nxList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(nxAdapter);

    }

    private void prepareNhanXetData() {
//test
//        NhanXet nx = new NhanXet(1, 1, "Mad Max: Fury Road", "Action & Adventure");
//        nxList.add(nx);
//
//        NhanXet nx2 = new NhanXet(2, 2, "Mad Max: Fury Road 2", "Action & Adventure 2");
//        nxList.add(nx2);

        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("khachhangnhanxet").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {

                for (com.google.firebase.database.DataSnapshot ds : dataSnapshot.getChildren()) {
                    String accountkh = ds.child("accountKH").getValue(String.class);
                    int id = Integer.parseInt(ds.child("id").getValue().toString());
                    int idsp = Integer.parseInt(ds.child("idSP").getValue().toString());
                    String review = ds.child("review").getValue(String.class);
                    NhanXet nx = new NhanXet(id, idsp, accountkh, review);
                    if (idsp == sanpham.getID()) {
                        nxList.add(nx);
                        nxAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveNhanXet() {
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idSp = sanpham.getID();
                mAuth = FirebaseAuth.getInstance();
                if (mAuth.getCurrentUser() != null){
                    String EMAIL= mAuth.getCurrentUser().getEmail();
                    final String accountKH = EMAIL;

                    final String review = edtNhanXet.getText().toString();
                    NhanXet nx = new NhanXet(idSp, accountKH, review);

//                  int keyTable = nxList.size() + 1;                                   //hinh nhu ngoai ham onDataChange nxList xoa mat du lieu (size luon tro ve 0)
                    NhanXet nxKey = new NhanXet(100, idSp, accountKH, review);//them binh luan id 100 :)

                    mData = FirebaseDatabase.getInstance().getReference();
                    mData.child("khachhangnhanxet").push().setValue(nxKey);

                    Toast.makeText(ChiTietSanPham.this, "Nhan xet da luu thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart_menu:
                Intent intent=new Intent(ChiTietSanPham.this,GioHang_Activity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton_Add() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.List_cart.size()>0){
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = sl*sanpham.getGiasanpham();
                    boolean check=false;
                    for(int i=0;i<MainActivity.List_cart.size();i++){
                        if(MainActivity.List_cart.get(i).getIdsp()==sanpham.getID()){
                            MainActivity.List_cart.get(i).setGiamoi(giamoi+MainActivity.List_cart.get(i).getGiamoi());
                            MainActivity.List_cart.get(i).setSoluong(sl+MainActivity.List_cart.get(i).getSoluong());
                            check=true;
                            Toast.makeText(ChiTietSanPham.this, ""+MainActivity.List_cart.size(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(!check){
                        MainActivity.List_cart.
                                add(new GioHang(sanpham.getID(),sanpham.getTensanpham(),
                                        sl,sanpham.getHinhanhsanpham(),giamoi));
                        Toast.makeText(ChiTietSanPham.this, ""+MainActivity.List_cart.size(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = sl*sanpham.getGiasanpham();
                    MainActivity.List_cart.
                            add(new GioHang(sanpham.getID(),sanpham.getTensanpham(),
                                    sl,sanpham.getHinhanhsanpham(),giamoi));
                    Toast.makeText(ChiTietSanPham.this, ""+MainActivity.List_cart.size(), Toast.LENGTH_SHORT).show();
                }

                Intent intent=new Intent(ChiTietSanPham.this,GioHang_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void GetData() {
        Picasso.with(getApplicationContext()).load(sanpham.getHinhanhsanpham()).into(imageView);// load ảnh từ 1 link sử dụng thưu viện picasso
        txt_mota.setText(sanpham.getMota());// set mô tả vào txt
        txt_tensp.setText(sanpham.getTensanpham());//set tên vào txt. hiển thi lên màn hình
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");// dùng để xử lí số có nhiefu chu so sao cho 3 số sẽ có 1 dấy phảy 1,000,000
        txt_gia.setText("Giá : "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        Integer[] in=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> list_spinner=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,in);
        // khởi tạo arraylistadapter để phục vụ cho việc vẽ spiner
        spinner.setAdapter(list_spinner);


    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);// set tool bar vào activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// tạo ra nút back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// xử lí sự kiên khi bấm nút back
                //TO DO ....
                finish();
            }
        });
    }

    private void AnhXa() {
        sanpham= (Sanpham) getIntent().getSerializableExtra("sanpham");// lấy ra đối tượng san phẩm truyền từ màn hình trước sang
        toolbar=findViewById(R.id.chitiet_toolbar);
        txt_tensp=findViewById(R.id.chitiet_tensp);
        txt_gia=findViewById(R.id.chtiet_giasp);
        txt_mota=findViewById(R.id.chitiet_mota);
        btn_add=findViewById(R.id.chitiet_add);
        imageView=findViewById(R.id.chitietsp_img);
        spinner=findViewById(R.id.chitietsp_spinner);
        btn_add=findViewById(R.id.chitiet_add);
        btn_luu=findViewById(R.id.buttonLuu);
        edtNhanXet=findViewById(R.id.editTextNhanXet);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }
}
