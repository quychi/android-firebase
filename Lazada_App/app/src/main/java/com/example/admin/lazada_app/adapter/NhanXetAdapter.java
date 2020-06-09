package com.example.admin.lazada_app.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.lazada_app.R;
import com.example.admin.lazada_app.model.NhanXet;

import java.util.List;

public class NhanXetAdapter extends RecyclerView.Adapter<NhanXetAdapter.MyViewHolder> {
    private List<NhanXet> nhanXetList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public NhanXetAdapter(Context context,List<NhanXet> nhanXetList) {
        this.nhanXetList = nhanXetList;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView account, review;

        public MyViewHolder(View itemView) {
            super(itemView);
            account = (TextView) itemView.findViewById(R.id.nx_accountKH);
            review = (TextView) itemView.findViewById(R.id.nx_review);


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = mLayoutInflater.inflate(R.layout.nhanxet_list_row,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final NhanXetAdapter.MyViewHolder holder, int position) {
        NhanXet nx = nhanXetList.get(position);
        holder.account.setText(nx.getAccountKH());
        holder.review.setText(nx.getReview());

        holder.account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),holder.account.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return nhanXetList.size();
    }

}
