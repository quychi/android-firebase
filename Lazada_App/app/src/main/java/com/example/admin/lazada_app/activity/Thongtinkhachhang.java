package com.example.admin.lazada_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.lazada_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Thongtinkhachhang extends AppCompatActivity {

    private EditText edt_ten, edt_sdt, edt_gmail;
    private Button btn_xacnhan, btntrove;

    private DatabaseReference mData;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkhachhang);
        Anhxa();



        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String ten = edt_ten.getText().toString().trim();
                final String sdt = edt_sdt.getText().toString().trim();
                final String gmail = edt_gmail.getText().toString().trim();
                if (ten.length() > 0 && sdt.length() > 0 && gmail.length() > 0) {
                    Toast.makeText(Thongtinkhachhang.this, "Start", Toast.LENGTH_SHORT).show();



                    class donhang implements Serializable {
                        public int id;
                        public String tenkhachhang;
                        public String sodienthoai;
                        public String email;

                        public donhang(int id, String tenkhachhang, String sodienthoai, String email) {
                          this.id = id;
                          this.tenkhachhang = tenkhachhang;
                          this.sodienthoai = sodienthoai;
                          this.email = email;
                        }

                        public donhang() {
                        }

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getTenkhachhang() {
                            return tenkhachhang;
                        }

                        public void setTenkhachhang(String tenkhachhang) {
                            this.tenkhachhang = tenkhachhang;
                        }

                        public String getSodienthoai() {
                            return sodienthoai;
                        }

                        public void setSodienthoai(String sodienthoai) {
                            this.sodienthoai = sodienthoai;
                        }

                        public String getEmail() {
                            return email;
                        }

                        public void setEmail(String email) {
                            this.email = email;
                        }
                    }
                    donhang d = new donhang(100, ten, sdt, gmail);// cu them don hang la id 100 :)
                    mData = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference keyRef  = mData.child("donhang").push();

                    String key = keyRef.getKey();
                    keyRef.setValue(d);
                    String madonhang = key;

                    String err = (madonhang==null)?"nullllllllll":madonhang;
                            Log.e("Mã Đơn Hàng : ", err);
                            if(madonhang != null){



                                        for(int i=0;i<MainActivity.List_cart.size();i++){

                                                int masp = MainActivity.List_cart.get(i).getIdsp();
                                                String tensanpham = MainActivity.List_cart.get(i).getTensp();
                                                long giasanpham = MainActivity.List_cart.get(i).getGiamoi();
                                                int soluongsanpham = MainActivity.List_cart.get(i).getSoluong();

                                            class chitietdonhang implements Serializable {
                                                public int id;
                                                public long giasanpham;
                                                public String madonhang;
                                                public int masanpham;
                                                public int soluongsanpham;
                                                public String tensanpham;

                                                public chitietdonhang() {
                                                }

                                                public chitietdonhang(int id, long giasanpham, String madonhang, int masanpham, int soluongsanpham, String tensanpham) {
                                                    this.id = id;
                                                    this.giasanpham = giasanpham;
                                                    this.madonhang = madonhang;
                                                    this.masanpham = masanpham;
                                                    this.soluongsanpham = soluongsanpham;
                                                    this.tensanpham = tensanpham;
                                                }

                                                public int getId() {
                                                    return id;
                                                }

                                                public void setId(int id) {
                                                    this.id = id;
                                                }

                                                public long getGiasanpham() {
                                                    return giasanpham;
                                                }

                                                public void setGiasanpham(long giasanpham) {
                                                    this.giasanpham = giasanpham;
                                                }

                                                public String getMadonhang() {
                                                    return madonhang;
                                                }

                                                public void setMadonhang(String madonhang) {
                                                    this.madonhang = madonhang;
                                                }

                                                public int getMasanpham() {
                                                    return masanpham;
                                                }

                                                public void setMasanpham(int masanpham) {
                                                    this.masanpham = masanpham;
                                                }

                                                public int getSoluongsanpham() {
                                                    return soluongsanpham;
                                                }

                                                public void setSoluongsanpham(int soluongsanpham) {
                                                    this.soluongsanpham = soluongsanpham;
                                                }

                                                public String getTensanpham() {
                                                    return tensanpham;
                                                }

                                                public void setTensanpham(String tensanpham) {
                                                    this.tensanpham = tensanpham;
                                                }
                                            }

                                                chitietdonhang ctdh = new chitietdonhang(100, giasanpham, madonhang, masp, soluongsanpham, tensanpham);//cu ghi them chitietdonhang id 100
                                                mData = FirebaseDatabase.getInstance().getReference();
                                                mData.child("chitietdonhang").push().setValue(ctdh);
                                                Toast.makeText(Thongtinkhachhang.this, "Bạn đã thêm giữ liệu giỏ hàng thành công" , Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(Thongtinkhachhang.this,MainActivity.class);
                                                startActivity(intent);
//                                            Toast.makeText(Thongtinkhachhang.this, "Bạn Cần Xem Lại Dữ Liệu Trong Giỏ Hàng", Toast.LENGTH_SHORT).show();
                                        }

                            }


                } else
                    Toast.makeText(Thongtinkhachhang.this, "Bạn Phải Nhập Đầy Đủ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void Anhxa() {
        edt_gmail = findViewById(R.id.info_kh_email);
        edt_sdt = findViewById(R.id.info_kh_sdt);
        edt_ten = findViewById(R.id.info_kh_ten);
        btn_xacnhan = findViewById(R.id.info_btn_xacnhan);
        btntrove = findViewById(R.id.info_btn_trove);
//        edt_ten.setText(kh_dn.getHoten());
//        edt_sdt.setText(kh_dn.getSdt());
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            String EMAIL= mAuth.getCurrentUser().getEmail();
            edt_gmail.setText(EMAIL);
        }

        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




    /*
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
    */
}
