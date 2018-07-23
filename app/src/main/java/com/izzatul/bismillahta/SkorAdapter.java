package com.izzatul.bismillahta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Izzatul on 7/23/2018.
 */

public class SkorAdapter extends RecyclerView.Adapter<SkorAdapter.ViewHolder>{
    private Context context;
    private List<SkorUser> list;

    public SkorAdapter(Context context, List<SkorUser> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public SkorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_skor, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SkorAdapter.ViewHolder holder, int position) {
        SkorUser skorUser = list.get(position);

        holder.textTanggal.setText(skorUser.getTanggalSimpan());
        holder.textSkor.setText(String.valueOf(skorUser.getSkor()));
        Log.d("jalan", "jalan");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTanggal, textSkor;
        public ViewHolder(View itemView) {
            super(itemView);

            textTanggal = itemView.findViewById(R.id.tv_tanggal);
            textSkor = itemView.findViewById(R.id.tv_skor_profil);
        }
    }
}
