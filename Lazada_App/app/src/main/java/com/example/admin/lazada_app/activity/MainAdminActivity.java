package com.example.admin.lazada_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.model.Sanpham;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainAdminActivity extends AppCompatActivity {

    private EditText tensp;
    private EditText giasp;
    private EditText hinhanhsp;
    private EditText motasp;
    private EditText loaisp;
    private Button btnThem;
    private Button btnHienDSSP;

    private DatabaseReference mData;
    private ArrayList<Sanpham> listallsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        Anhxa();
//        getallsanpham();//chi de lay idSP (de tang id) nhung hinh nhu ngoai ham onDataChange cua getallSanPham size tro ve 0
        clickThem();
        clickShowListProduct();
    }

    private void clickThem() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tensanpham = tensp.getText().toString();
                final String giasanpham = giasp.getText().toString();
                final String hinhanhsanpham = hinhanhsp.getText().toString();
                final String mota = motasp.getText().toString();
                final String idsanpham = loaisp.getText().toString();

//                int keyTable = listallsp.size();                //hinh nhu ngoai ham onDataChange cua getallSanPham size tro ve 0
                Sanpham s = new Sanpham(100, tensanpham, mota, hinhanhsanpham, Integer.parseInt(giasanpham), Integer.parseInt(idsanpham));//cu them san pham id 100 :)))
                mData = FirebaseDatabase.getInstance().getReference();
                mData.child("sanpham").push().setValue(s);

                Toast.makeText(MainAdminActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickShowListProduct(){
        btnHienDSSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainAdminActivity.this,ShowAllProductActivity.class);
                startActivity(intent1);
//                finish();
            }
        });
    }

    private void Anhxa(){
        tensp = findViewById(R.id.editTextTenSP);
        giasp = findViewById(R.id.editTextGiaSP);
        hinhanhsp = findViewById(R.id.editTextHinhanh);
        motasp = findViewById(R.id.editTextMota);
        loaisp = findViewById(R.id.editTextLoai);
        btnThem = findViewById(R.id.buttonThem);
        btnHienDSSP = findViewById(R.id.buttonShowListProduct);
    }

//    public void getallsanpham() {
//        mData = FirebaseDatabase.getInstance().getReference();
//        mData.child("sanpham").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    int giasp = Integer.parseInt(ds.child("giasanpham").getValue().toString());
//                    String Imagesp = ds.child("hinhanhsanpham").getValue(String.class);
//                    int idsp = Integer.parseInt(ds.child("id").getValue().toString());
//                    int loaisp = Integer.parseInt(ds.child("idsanpham").getValue().toString());
//                    String motasp = ds.child("mota").getValue(String.class);
//                    String tensp = ds.child("tensanpham").getValue(String.class);
//                    Sanpham sp = new Sanpham(idsp, tensp, motasp, Imagesp, giasp, loaisp);
//                    listallsp.add(sp);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
