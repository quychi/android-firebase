package com.example.admin.lazada_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.adapter.LaptopAdapter;
import com.example.admin.lazada_app.model.Sanpham;
import com.example.admin.lazada_app.ultil.CheckConnection;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LaptopActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private LaptopAdapter adapter;
    private ArrayList<Sanpham> list;
    private View footerview;
    private int page=1;
    private boolean isloading=false;
    private boolean limitdata=false;
    private myHanler hanler;

    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            AnhXa();
            ActionToolbar();
            GetData(page);
            LoadMore();

        }
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
                Intent intent=new Intent(LaptopActivity.this,GioHang_Activity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMore() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(LaptopActivity.this,ChiTietSanPham.class);
                intent.putExtra("sanpham",adapter.getList().get(position));
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(totalItemCount!=0&&firstVisibleItem+visibleItemCount==totalItemCount&&!isloading&&!limitdata){
                    isloading=true;
                    ThreadData threadData=new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int page) {
        list.clear();
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("sanpham").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {

                for (com.google.firebase.database.DataSnapshot ds : dataSnapshot.getChildren()) {
                    int giasp = Integer.parseInt(ds.child("giasanpham").getValue().toString());
                    String Imagesp = ds.child("hinhanhsanpham").getValue(String.class);
                    int idsp = Integer.parseInt(ds.child("id").getValue().toString());
                    int loaisp = Integer.parseInt(ds.child("idsanpham").getValue().toString());
                    String motasp = ds.child("mota").getValue(String.class);
                    String tensp = ds.child("tensanpham").getValue(String.class);
                    Sanpham sp = new Sanpham(idsp, tensp, motasp, Imagesp, giasp, loaisp);
                    if (sp.getIDsanpham() == 2)         //laptop co idSP='1'
                        list.add(sp);
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listView.removeFooterView(footerview);
                limitdata=true;
                Toast.makeText(LaptopActivity.this, "Đã Hết Dữ Liệu onCancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ActionToolbar() {
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
        toolbar=findViewById(R.id.toolbar_laptop);
        listView=findViewById(R.id.listview_laptop);
        list=new ArrayList<>();
        adapter=new LaptopAdapter(list,getApplicationContext());
        listView.setAdapter(adapter);
        LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview=layoutInflater.inflate(R.layout.progressbar,null);
        hanler=new myHanler();


    }
    public class myHanler extends Handler{
        @Override
        public void handleMessage(Message msg) {// dùng để quản lí các công việc do các tiến trình của luồng gửi lên
            switch (msg.what){
                case 0:
                    listView.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isloading=false;

                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            hanler.sendEmptyMessage(0);
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=hanler.obtainMessage(1);// obtainmessage là dùng để liên kết thread với ông chủ hanler
            hanler.sendMessage(message);
            super.run();
        }
    }

}
