package com.example.admin.lazada_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.activity.ChiTietSanPham;
import com.example.admin.lazada_app.activity.ShowSingleRecordActivity;
import com.example.admin.lazada_app.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.admin.lazada_app.activity.Dang_KyActivity.kh_dn;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.itemholder>{
    private ArrayList<Sanpham> list;
    private Context context;

    public SanphamAdapter(ArrayList<Sanpham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ArrayList<Sanpham> getList() {
        return list;
    }

    public void setList(ArrayList<Sanpham> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public itemholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_sanphammoinhat,null);

        return new itemholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemholder holder, int position) {// hàm vẽ view
        Sanpham sanpham=list.get(position);
        holder.txt_tensp.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");//hiện giá kieru 1,000,000

        holder.txt_giasanpham.setText("Giá : "+decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.m).error(R.drawable.eror).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class itemholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_tensp,txt_giasanpham;

        public itemholder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id._itemsp_img);
            txt_tensp=itemView.findViewById(R.id.txt_itemsp_tensp);
            txt_giasanpham=itemView.findViewById(R.id.txt_itemsp_giasp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSanPham.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("sanpham", list.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
