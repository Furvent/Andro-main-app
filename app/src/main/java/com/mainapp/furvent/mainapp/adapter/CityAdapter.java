package com.mainapp.furvent.mainapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mainapp.furvent.mainapp.R;
import com.mainapp.furvent.mainapp.data.EleveBean;
import com.mainapp.furvent.mainapp.data.city.CityBean;
import com.mainapp.furvent.mainapp.interfaces.OnEleveClickListener;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{

    private List<CityBean> data;

    public CityAdapter(List<CityBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_town, parent, false);
        return new CityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder viewHolder, int position) {
        CityBean datum = data.get(position);
        viewHolder.textViewTownName.setText(datum.getCityName());
        viewHolder.textViewPostalCode.setText(datum.getPostalCode());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTownName, textViewPostalCode;
        View cityLinearLayoutInCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cityLinearLayoutInCardView = itemView.findViewById(R.id.cityLinearLayoutInCardView);
            textViewTownName = itemView.findViewById(R.id.textViewTownName);
            textViewPostalCode = itemView.findViewById(R.id.textViewPostalCode);
        }
    }
}
