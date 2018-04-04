package com.izzatul.bismillahta;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Izzatul on 1/23/2018.
 */

public class ZakatAdapter extends RecyclerView.Adapter<ZakatAdapter.ZakatViewHolder>{

    private OnRecyclerViewListener listener;
    private List<Zakat> zakatList;

    public class ZakatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv;
        TextView nama_zakat;

        ZakatViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            nama_zakat = itemView.findViewById(R.id.nama_zakat);

            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickCardView(getAdapterPosition());
        }

    }

    public ZakatAdapter(OnRecyclerViewListener listener, List<Zakat> zakats){
        this.listener = listener;
        this.zakatList = zakats;
    }

    @Override
    public int getItemCount() {
        return zakatList.size();
    }

    @Override
    public ZakatViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_zakat, viewGroup, false);
        ZakatViewHolder zvh = new ZakatViewHolder(v);
        return zvh;
    }

    // COMPLETED tambahkan nama zakat sesuai recyclerview, contohnya ada di RVAdapter
    @Override
    public void onBindViewHolder(ZakatViewHolder holder, int position) {
        holder.nama_zakat.setText(zakatList.get(position).nama);
//        holder.detail.setText(zakatList.get(position).detail);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface OnRecyclerViewListener {
        void onClickCardView(int position);
    }
}
