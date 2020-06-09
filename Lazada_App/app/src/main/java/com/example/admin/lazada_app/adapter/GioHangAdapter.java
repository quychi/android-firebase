package com.example.admin.lazada_app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.lazada_app.Interfaces.inter;
import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.model.GioHang;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.mnViewholder> implements View.OnClickListener{

    private ArrayList<GioHang> list;
    private Context context;
    private inter in;


    public GioHangAdapter(ArrayList<GioHang> list, Context context, inter in) {
        this.list = list;
        this.context = context;
        this.in=  in;
    }

    public ArrayList<GioHang> getList() {
        return list;
    }

    public void setList(ArrayList<GioHang> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public mnViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_giohang,null);

        return new mnViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mnViewholder holder, final int position) {
        GioHang gioHang=list.get(position);
        holder.item_tensp.setText(gioHang.getTensp());
        holder.item_sl.setText(gioHang.getSoluong()+"");
        long giamoi=gioHang.getGiamoi();
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");

        holder.item_gia.setText("Giá : "+decimalFormat.format(giamoi)+"");
        Picasso.with(context).load(gioHang.getHinhanhsp()).placeholder(R.drawable.m).error(R.drawable.eror).into(holder.item_img);

        int sl=list.get(position).getSoluong();
        holder.item_sub.setTag(position+"");
        holder.item_add.setTag(position+"");
        if(sl==1) {
            holder.item_sub.setClickable(false);
            holder.item_add.setOnClickListener(this);
        }
        else if(sl>1){
            holder.item_sub.setOnClickListener(this);
            holder.item_add.setOnClickListener(this);
        }
        holder.item_giohang_ln.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                in.Delete(position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        int position = 0;
        if(!v.getTag().toString().equals(""))
            position=Integer.parseInt(v.getTag().toString());
        switch (v.getId()){
            case R.id.item_giohang_btn_tru:
                in.Sub(position);
                break;
            case R.id.item_giohang_btn_cong:
                in.Add(position);
                break;
        }
    }

    class mnViewholder extends RecyclerView.ViewHolder{

        ImageView item_img,item_sub,item_add;
        TextView item_tensp,item_gia,item_sl;
        LinearLayout item_giohang_ln;
        public mnViewholder(View itemView) {
            super(itemView);
            item_add=itemView.findViewById(R.id.item_giohang_btn_cong);
            item_sub=itemView.findViewById(R.id.item_giohang_btn_tru);
            item_gia=itemView.findViewById(R.id.item_giohang_giasp);
            item_tensp=itemView.findViewById(R.id.item_giohang_tensp);
            item_sl=itemView.findViewById(R.id.item_giohang_sl);
            item_img=itemView.findViewById(R.id.item_giohang_img);
            item_giohang_ln=itemView.findViewById(R.id.item_giohang_ln);
        }
    }
}
