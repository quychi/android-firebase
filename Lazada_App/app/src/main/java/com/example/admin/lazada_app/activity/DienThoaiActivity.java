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

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.adapter.DienthoaiAdapter;
import com.example.admin.lazada_app.model.Sanpham;
import com.example.admin.lazada_app.ultil.CheckConnection;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DienThoaiActivity extends AppCompatActivity {

    private Toolbar dienthoai_toolbar;
    private ListView dienthoai_listView;
    private ArrayList<Sanpham> list;
    private DienthoaiAdapter dienthoaiAdapter;
    private int page=1;
    private View footerview;
    private boolean isloading=false;
    private myHanler hanler;
    private boolean limitdata=false;

    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            AnhXa();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }
        else
        {
            CheckConnection.ThongBao(getApplicationContext(),"Kiểm Tra Kết Nối");
            finish();
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
                Intent intent=new Intent(DienThoaiActivity.this,GioHang_Activity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMoreData() {

        dienthoai_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(DienThoaiActivity.this,ChiTietSanPham.class);
                intent.putExtra("sanpham",dienthoaiAdapter.getList().get(position));
                startActivity(intent);
            }
        });
        dienthoai_listView.setOnScrollListener(new AbsListView.OnScrollListener() {// bắt sự kiện cuộc của listview
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // khi load listview đến 1 vị trí nào đó mà không phải vị trí cuối thì chương trình sẽ nhầy vào hàm này

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // khi mà load đến vị trí cuối cùng (ở đây định nghĩa cuối cùng là 5,10,15,20,...) thì chương trình sẽ nhẩy vào đây

                // firstvisibleItem ở đây là vị trí view đầu tiên trong lần load thứ i
                //visibleitemcount ở đây là số lượng viewitem nhìn thấy được
                // totalitemcount ở đây là tổng số viewitem đã load
                // firstvisibleitem nếu ở lần load đầu nó là 0 , lần load thứ 2 nó là 5
                // visibleitemcount trong bài này là 5
                // tổng totalitemcount neu ở lần load thứ 2 là 10 ==visibleitemcount+firstvisibleitem luôn đúng với điều kiện sau
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
//        String path= server.Path_dienthoai+page;  liêm edit 00:25 9/4/2018
//        server.KQ(getApplicationContext());
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
                    if (sp.getIDsanpham() == 1)         //dien thoai co idSP='1'
                        list.add(sp);
                    dienthoaiAdapter.setList(list);
                    dienthoaiAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    limitdata=true;
                    dienthoai_listView.removeFooterView(footerview);
                    CheckConnection.ThongBao(getApplicationContext(),"Đã Hết Dữ Liệu onCancelled");
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(dienthoai_toolbar);
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dienthoai_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        dienthoai_toolbar=findViewById(R.id.dienthoai_toolbar);
        dienthoai_listView=findViewById(R.id.dienthoai_listview);
        list=new ArrayList<>();
        dienthoaiAdapter=new DienthoaiAdapter(list,getApplicationContext());
        dienthoai_listView.setAdapter(dienthoaiAdapter);
        LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview=layoutInflater.inflate(R.layout.progressbar,null);
        hanler=new myHanler();

    }
    public class myHanler extends Handler{
        @Override
        public void handleMessage(Message msg) {// dùng để quản lí các công việc do các tiến trình của luồng gửi lên
            switch (msg.what){
                case 0:
                    dienthoai_listView.addFooterView(footerview);
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
