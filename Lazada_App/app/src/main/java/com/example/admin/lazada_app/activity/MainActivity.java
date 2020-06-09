package com.example.admin.lazada_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.adapter.LoaispAdapter;
import com.example.admin.lazada_app.adapter.SanphamAdapter;
import com.example.admin.lazada_app.model.GioHang;
import com.example.admin.lazada_app.model.Loaisp;
import com.example.admin.lazada_app.model.Sanpham;
import com.example.admin.lazada_app.ultil.CheckConnection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    public static ArrayList<GioHang> List_cart;
    private ListView listView;
    private RecyclerView recyclerView;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private DrawerLayout drawerLayout;
    private NavigationView navigationview;
    private LoaispAdapter loaispAdapter;
    private SanphamAdapter sanphamAdapter;
    private ArrayList<Loaisp> list;
    private ArrayList<Sanpham> listsp_new;
    private int id=0;
    private String tenloaisp="";
    private String hinhanhsp="";

    private DatabaseReference mData1;
    private DatabaseReference mData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFilpper();
            Getdulieu_loaisp();
            Getsanphammoinhat();
            Batsukienlistview();

        }
        else{
            CheckConnection.ThongBao(getApplicationContext(),"Bạn Chưa kết Nối Mạng");
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
                Intent intent=new Intent(MainActivity.this,GioHang_Activity.class);
                startActivity(intent);
                break;
            case R.id.log_out:
                Intent intent1=new Intent(MainActivity.this,Dang_NhapActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Batsukienlistview() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        Intent intent =new Intent(MainActivity.this,DienThoaiActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent2=new Intent(MainActivity.this,LaptopActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3=new Intent(MainActivity.this,LienheActivity.class);

                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4=new Intent(MainActivity.this,ThongTinCaNhan.class);
                        startActivity(intent4);
                        break;

                }
            }
        });
    }

    private void Getsanphammoinhat() {

        mData1 = FirebaseDatabase.getInstance().getReference();
        mData1.child("sanpham").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
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
                    listsp_new.add(sp);
                    sanphamAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void Getdulieu_loaisp() {

        mData2 = FirebaseDatabase.getInstance().getReference();
        mData2.child("loaisanpham").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String hinhanhsanpham = ds.child("hinhanhsanpham").getValue(String.class);
                    int id = Integer.parseInt(ds.child("id").getValue().toString());
                    String tenloaisp = ds.child("tenloaisanpham").getValue(String.class);
                    Loaisp lsp = new Loaisp(id, tenloaisp, hinhanhsanpham);
                    list.add(lsp);
                    loaispAdapter.notifyDataSetChanged();
                }

        list.add(3,new Loaisp(0,"Liên hệ","https://hongngochospital.vn/wp-content/themes/flexible/assets/images/icon_phone_01.png"));
        list.add(4,new Loaisp(0,"Thông Tin","https://cdn2.iconfinder.com/data/icons/perfect-flat-icons-2/512/User_info_man_male_profile_information.png"));
                loaispAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void ActionViewFilpper() {
        ArrayList<String> listquangcao=new ArrayList<>();
        listquangcao.add("http://image.bizlive.vn/670x350/uploaded/nguyentham_pv/2016_07_13/1_60688_lquo.jpg");
        listquangcao.add("http://mobilecenter.com.vn/uploads/image/images/h%C3%ACnh%20qu%E1%BA%A3ng%20c%C3%A1o%20face.jpg");
        listquangcao.add("https://bizzaviet.com/wp-content/uploads/2015/02/%C4%91i%E1%BB%87n-tho%E1%BA%A1i-htc-one-m91.jpg");
        listquangcao.add("http://img-static.redvn.info/2017/11/011ce0fafec2d03415febf59fac71484.jpg");
        for(int i=0;i<listquangcao.size();i++){
            ImageView imageView=new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(listquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(2000);// set 2s lại truyefn ảnh
        viewFlipper.setAutoStart(true);

        // hiệu ứng chuyển động
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        Animation animation2=AnimationUtils.loadAnimation(this,R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation);
        viewFlipper.setOutAnimation(animation2);

    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);// biá»ƒu tÆ°á»£ng Ä‘á»ƒ click cho navigation xá»• ra

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);//GravityCompat.START xá»• ra giá»¯a

            }
        });
    }

    private void AnhXa(){
        //   server.KQ(this);
        navigationView=findViewById(R.id.navigationview);
        drawerLayout=findViewById(R.id.drawerlayout);
        listView=findViewById(R.id.listview);
        recyclerView=findViewById(R.id.recyclerview);
        toolbar=findViewById(R.id.toolbar_manhhinhchinh);
        viewFlipper=findViewById(R.id.viewflipper);

        toolbar=findViewById(R.id.toolbar_manhhinhchinh);
        list=new ArrayList<>();
        list.add(0,new Loaisp(0,"Trang chủ","https://vietadsgroup.vn/Uploads/files/trangchu-la-gi.png"));

        loaispAdapter=new LoaispAdapter(list,getApplicationContext());
        listView.setAdapter(loaispAdapter);

        // sáº£n pháº©m má»›i nháº¥t
        listsp_new=new ArrayList<>();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        sanphamAdapter=new SanphamAdapter(listsp_new,getApplicationContext());
        DividerItemDecoration decoration=new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);

        DividerItemDecoration decoratio2=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.addItemDecoration(decoratio2);
        recyclerView.setAdapter(sanphamAdapter);
        if(List_cart==null)
            List_cart=new ArrayList<>();



    }
}
