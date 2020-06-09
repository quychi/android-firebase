package com.example.admin.lazada_app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.model.Sanpham;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public final class ShowSingleRecordActivity extends AppCompatActivity {

    //ShowSingleRecord this is for Admin

    private int idsanpham;
    private Sanpham sanpham;
    private EditText tensp;
    private EditText giasp;
    private EditText hinhanhsp;
    private EditText motasp;
    private EditText loaisp;
    private Button btnSua;
    private Button btnXoa;

    private Firebase firebase;
    private DatabaseReference updateData;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record);

        Anhxa();
        GetData();
        clickSua();
        clickXoa();
    }

    private void GetData() {
        hinhanhsp.setText(sanpham.getHinhanhsanpham());
        motasp.setText(sanpham.getMota());// set mô tả vào txt
        tensp.setText(sanpham.getTensanpham());//set tên vào txt. hiển thi lên màn hình
        giasp.setText(sanpham.getGiasanpham() + "");
//        Integer[] in=new Integer[]{1,2,3,4,5,6,7,8,9,10};
//        ArrayAdapter<Integer> list_spinner=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,in);
//        // khởi tạo arraylistadapter để phục vụ cho việc vẽ spiner
//        spinner.setAdapter(list_spinner);                                                 ???

        loaisp.setText(sanpham.getIDsanpham() + "");

    }

    private void clickSua() {
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = sanpham.getID();
                final String tensanpham = tensp.getText().toString();
                final String giasanpham = giasp.getText().toString();
                final String hinhanhsanpham = hinhanhsp.getText().toString();
                final String mota = motasp.getText().toString();
                final String idsanpham = loaisp.getText().toString();

                DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("sanpham");

                //equalTo xau danh cho sanpham duoc import tu trc (json form mysql)
                data.orderByChild("id").equalTo(sanpham.getID() + "").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            data.getRef().child("tensanpham").setValue(tensanpham);
                            data.getRef().child("giasanpham").setValue(Integer.parseInt(giasanpham));
                            data.getRef().child("hinhanhsanpham").setValue(hinhanhsanpham);
                            data.getRef().child("mota").setValue(mota);
                            data.getRef().child("idsanpham").setValue(Integer.parseInt(idsanpham));
                        }
                        Toast.makeText(ShowSingleRecordActivity.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //equalTo int danh cho sanpham duoc them vao firebase
                data.orderByChild("id").equalTo(sanpham.getID()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            data.getRef().child("tensanpham").setValue(tensanpham);
                            data.getRef().child("giasanpham").setValue(Integer.parseInt(giasanpham));
                            data.getRef().child("hinhanhsanpham").setValue(hinhanhsanpham);
                            data.getRef().child("mota").setValue(mota);
                            data.getRef().child("idsanpham").setValue(Integer.parseInt(idsanpham));
                        }
                        Toast.makeText(ShowSingleRecordActivity.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void clickXoa() {
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final int id = sanpham.getID();

                DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("sanpham");

                //equalTo xau danh cho sanpham duoc import tu trc (json form mysql)
                data.orderByChild("id").equalTo(sanpham.getID() + "").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            data.getRef().removeValue();

                        }

                        Toast.makeText(ShowSingleRecordActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //equalTo xau danh cho sanpham duoc them vao tu firebase
                data.orderByChild("id").equalTo(sanpham.getID()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            data.getRef().removeValue();

                        }

                        Toast.makeText(ShowSingleRecordActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void Anhxa(){
        sanpham= (Sanpham) getIntent().getSerializableExtra("sanpham");// lấy ra đối tượng san phẩm truyền từ màn hình trước sang
        tensp = findViewById(R.id.TenSP);
        giasp = findViewById(R.id.GiaSP);
        hinhanhsp = findViewById(R.id.Hinhanh);
        motasp = findViewById(R.id.Mota);
        loaisp = findViewById(R.id.Loai);
        btnSua = findViewById(R.id.buttonSua);
        btnXoa = findViewById(R.id.buttonXoa);
    }
}
