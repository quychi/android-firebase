package com.example.admin.lazada_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.activity.Dang_NhapActivity;
import com.example.admin.lazada_app.activity.MainActivity;
import com.example.admin.lazada_app.activity.ShowAllProductActivity;
import com.example.admin.lazada_app.activity.ShowSingleRecordActivity;
import com.example.admin.lazada_app.model.Sanpham;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class allSanPhamAdapter extends RecyclerView.Adapter<allSanPhamAdapter.MyViewHolder>{
    private List<Sanpham> spList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public allSanPhamAdapter(Context context,List<Sanpham> spList) {
        this.spList = spList;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView ten, gia;

        public MyViewHolder(View itemView) {
            super(itemView);
            ten = (TextView) itemView.findViewById(R.id.ten);
            gia = (TextView) itemView.findViewById(R.id.gia);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = mLayoutInflater.inflate(R.layout.product_list_row,parent,false);
        return new allSanPhamAdapter.MyViewHolder(item);
    }


    @Override
    public void onBindViewHolder(final allSanPhamAdapter.MyViewHolder holder, final int position) {
        Sanpham s = spList.get(position);
        holder.ten.setText(s.getTensanpham());
        holder.gia.setText(s.getGiasanpham() + "" +" VND");                     //int set String crash app

        holder.ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(),holder.ten.getText(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ShowSingleRecordActivity.class);
                intent.putExtra("sanpham", spList.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return spList.size();
    }

//    public List<Sanpham> getList (){			//thua
//        return this.spList;
//    }
}
