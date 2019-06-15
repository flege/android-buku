package com.flege.buku.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flege.buku.EditActivity;
import com.flege.buku.Model.Buku;
import com.flege.buku.R;

import java.util.List;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.MyViewHolder>{
    List<Buku> mBukuList;

    public BukuAdapter(List <Buku> KontakList) {
        mBukuList = KontakList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.buku_list, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.mTextViewId.setText("Id = " + mBukuList.get(position).getId());
        holder.mTextViewNama.setText("Title = " + mBukuList.get(position).getTitle());
        holder.mTextViewNomor.setText("Author Name = " + mBukuList.get(position).getAuthor_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditActivity.class);
                mIntent.putExtra("Id", mBukuList.get(position).getId());
                mIntent.putExtra("Title", mBukuList.get(position).getTitle());
                mIntent.putExtra("Author Name", mBukuList.get(position).getAuthor_name());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mBukuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNama, mTextViewNomor;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = (TextView) itemView.findViewById(R.id.tvId);
            mTextViewNama = (TextView) itemView.findViewById(R.id.tvTitle);
            mTextViewNomor = (TextView) itemView.findViewById(R.id.tvAuthor_name);
        }
    }
}
