package com.example.admin.lazada_app.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.adapter.allSanPhamAdapter;
import com.example.admin.lazada_app.model.Sanpham;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public final class ShowAllProductActivity extends AppCompatActivity{

    //ShowAllProduct this is for Admin

    public ArrayList<Sanpham> listallsp = new ArrayList<>();
    public ArrayList< String> list;
//    private ArrayList<Sanpham> listsp2 = new ArrayList<>();
    private RecyclerView recyclerView;
    private allSanPhamAdapter aspAdapter;

    public static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_product);

//        Anhxa();
        //        listsp2.add(new Sanpham(1,"t","m","a",0,1));


        getallsanpham();
        recyclerView = findViewById(R.id.recycler_view_allproduct);
        aspAdapter = new allSanPhamAdapter(ShowAllProductActivity.this, listallsp);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(aspAdapter);

    }

    public void getallsanpham(){
        listallsp.clear();
        Firebase.setAndroidContext(this);
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("sanpham").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int giasp = Integer.parseInt(ds.child("giasanpham").getValue().toString());
                    String Imagesp = ds.child("hinhanhsanpham").getValue(String.class);
                    int idsp = Integer.parseInt(ds.child("id").getValue().toString());
                    int loaisp = Integer.parseInt(ds.child("idsanpham").getValue().toString());
                    String motasp = ds.child("mota").getValue(String.class);
                    String tensp = ds.child("tensanpham").getValue(String.class);
                    Sanpham sp = new Sanpham(idsp, tensp, motasp, Imagesp, giasp, loaisp);
                    listallsp.add(sp);
                    aspAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//    private void Anhxa(){
//          recyclerView = findViewById(R.id.recycler_view_allproduct);
//    }
}
