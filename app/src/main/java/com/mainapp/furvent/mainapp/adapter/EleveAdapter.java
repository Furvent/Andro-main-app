package com.mainapp.furvent.mainapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mainapp.furvent.mainapp.R;
import com.mainapp.furvent.mainapp.data.EleveBean;
import com.mainapp.furvent.mainapp.interfaces.OnEleveClickListener;

import java.util.List;

public class EleveAdapter extends RecyclerView.Adapter<EleveAdapter.ViewHolder>{

    private List<EleveBean> data;
    private OnEleveClickListener onEleveClickListener;

    public EleveAdapter(List<EleveBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_eleve, parent, false);
        return new EleveAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EleveBean datum = data.get(position);
        holder.tvEleveName.setText(datum.getName());
        holder.tvEleveFirstName.setText(datum.getFirstName());
        holder.eleveLinearLayoutInCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEleveClickListener != null) {
                    onEleveClickListener.OnEleveClick(datum);
                }
            }
        });
        holder.eleveLinearLayoutInCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onEleveClickListener != null) {
                    onEleveClickListener.OnEleveLongClick(datum);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public OnEleveClickListener getOnEleveClickListener() {
        return onEleveClickListener;
    }

    public void setOnEleveClickListener(OnEleveClickListener onEleveClickListener) {
        this.onEleveClickListener = onEleveClickListener;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvEleveName, tvEleveFirstName;
        ImageView imageViewEleve;
        View eleveLinearLayoutInCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            eleveLinearLayoutInCardView = itemView.findViewById(R.id.eleveLinearLayoutInCardView);
            tvEleveName = itemView.findViewById(R.id.tvEleveName);
            tvEleveFirstName = itemView.findViewById(R.id.tvEleveFirstName);
        }
    }
}


