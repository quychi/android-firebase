package com.example.admin.lazada_app.adapter;

import android.content.Context;
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

public class DienthoaiAdapter extends BaseAdapter {
    private ArrayList<Sanpham> list;
    private Context context;

    public DienthoaiAdapter(ArrayList<Sanpham> list, Context context) {
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
        viewholder holder=null;
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.item_dienthoai,null);
            holder=new viewholder(convertView);
            convertView.setTag(holder);
        }
        holder= (viewholder) convertView.getTag();
        Sanpham sanpham=getItem(position);
        holder.item_dienthoai_tensp.setText(sanpham.getTensanpham());
        holder.item_dienthoai_mota.setMaxLines(2);// chỉ hiển thị 2 dòng
        holder.item_dienthoai_mota.setEllipsize(TextUtils.TruncateAt.END);// nó sẽ tự hiểu là nếu còn thì cho dấu 3 chấm ở đoạn cuối
        holder.item_dienthoai_mota.setText(sanpham.getMota());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.item_dienthoai_giasp.setText("Giá : "+decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.m).error(R.drawable.eror).into(holder.item_dienthoai_img);
        return convertView;
    }
    class viewholder{
        ImageView item_dienthoai_img;
        TextView item_dienthoai_tensp,item_dienthoai_giasp,item_dienthoai_mota;


        public viewholder(View view) {
            item_dienthoai_giasp=view.findViewById(R.id.item_dienthoai_giasp);
            item_dienthoai_img=view.findViewById(R.id.item_dienthoai_img);
            item_dienthoai_tensp=view.findViewById(R.id.item_dienthoai_tensp);
            item_dienthoai_mota=view.findViewById(R.id.item_dienthoai_mota);
        }
    }
}
