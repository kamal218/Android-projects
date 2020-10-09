package com.example.contactrecview.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactrecview.DetailActivity;
import com.example.contactrecview.MainActivity;
import com.example.contactrecview.R;
import com.example.contactrecview.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Contact> list;

    public RecyclerViewAdapter(Context context, List<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(this.context).inflate(R.layout.singlerow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_.setText(list.get(position).getName());
        holder.number_.setText(list.get(position).getNumer());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name_;
        public TextView number_;
        public ImageView image_;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name_=itemView.findViewById(R.id.name_id);
            number_=itemView.findViewById(R.id.number_id);
            image_=itemView.findViewById(R.id.image_button);
        }

        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();
        Contact contact=list.get(pos);
            Intent intent=new Intent(context,DetailActivity.class);
            intent.putExtra("name",contact.getName());
            intent.putExtra("number",contact.getNumer());
            context.startActivity(intent);
        }
    }
}
