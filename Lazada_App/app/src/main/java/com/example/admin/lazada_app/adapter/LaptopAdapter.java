package com.example.admin.lazada_app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter{

    private ArrayList<Sanpham> list;
    private Context context;

    public LaptopAdapter(ArrayList<Sanpham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ArrayList<Sanpham> getList() {
        return list;
    }

    public void setList(ArrayList<Sanpham> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Sanpham getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mnViewholder viewholder;
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.item_laptop,null);
            viewholder=new mnViewholder(convertView);
            convertView.setTag(viewholder);
        }
        viewholder= (mnViewholder) convertView.getTag();
        Sanpham sanpham=getItem(position);
        viewholder.txt_laptop_motasp.setText(sanpham.getMota());
        viewholder.txt_laptop_tensp.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewholder.txt_laptop_motasp.setMaxLines(2);
        viewholder.txt_laptop_motasp.setEllipsize(TextUtils.TruncateAt.END);
        viewholder.txt_laptop_giasp.setText("Giá : "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.m)
                .error(R.drawable.eror)
                .into(viewholder.img_laptop);
        return convertView;
    }
    class mnViewholder{
        ImageView img_laptop;
        TextView txt_laptop_tensp,txt_laptop_giasp,txt_laptop_motasp;
        public mnViewholder(View view) {
            img_laptop=view.findViewById(R.id.item_laptop_img);
            txt_laptop_giasp=view.findViewById(R.id.item_laptop_giasp);
            txt_laptop_tensp=view.findViewById(R.id.item_laptop_tensp);
            txt_laptop_motasp=view.findViewById(R.id.item_laptop_mota);
        }
    }
}
