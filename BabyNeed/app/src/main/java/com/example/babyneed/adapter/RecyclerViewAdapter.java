package com.example.babyneed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babyneed.R;
import com.example.babyneed.model.Model;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public Context context;
    public List<Model> list;

    public RecyclerViewAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recviewrowxml,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pName.setText(list.get(position).getP_name());
        holder.pColor.setText(list.get(position).getColor());
        holder.pSize.setText(list.get(position).getSize()+"");
        holder.pQuantity.setText(list.get(position).getQuantity()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pName;
        public TextView pColor;
        public TextView pSize;
        public TextView pQuantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pName=itemView.findViewById(R.id.name_id_o);
            pColor=itemView.findViewById(R.id.color_id_o);
            pSize=itemView.findViewById(R.id.size_id_o);
            pQuantity=itemView.findViewById(R.id.quantity_id_o);
        }
    }
}
