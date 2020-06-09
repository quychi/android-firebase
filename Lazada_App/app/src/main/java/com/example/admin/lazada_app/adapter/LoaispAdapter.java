package com.example.admin.lazada_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> list;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ArrayList<Loaisp> getList() {
        return list;
    }

    public void setList(ArrayList<Loaisp> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        ImageView img;
        TextView txt;
        public ViewHolder(View view){
            img=view.findViewById(R.id.item_loaisp_img);
            txt=view.findViewById(R.id.item_loaisp_txt);

        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.item_loaisp,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        Loaisp loaisp= (Loaisp) getItem(position);
        viewHolder.txt.setText(loaisp.getTensp());
        Picasso.with(context).load(loaisp.getHinhanhsp())
                .placeholder(R.drawable.m)
                .error(R.drawable.eror)
                .into(viewHolder.img);
        return convertView;
    }
}
