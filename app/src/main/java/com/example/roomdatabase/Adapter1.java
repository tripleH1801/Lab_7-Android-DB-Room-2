package com.example.roomdatabase;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.viewHolder> {
    private List<User> listUser;
    private LayoutInflater inflater;
    private Context ctx;

    public Adapter1(List<User> listUser, Context ctx) {
        this.listUser = listUser;
        this.inflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Adapter1.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.item1, parent, false);
        return new viewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter1.viewHolder holder, int position) {
        holder.tvName.setText(listUser.get(position).getName());
        holder.tvStt.setText(String.valueOf(position+1)+".");
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public final TextView tvName;
        public final TextView tvStt;
        public final ImageButton btnEdit;
        public final ImageButton btnDelete;

        public final Adapter1 adt;

        public viewHolder(@NonNull View itemView, Adapter1 adt) {
            super(itemView);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvStt = itemView.findViewById(R.id.tvStt);
            this.btnEdit = itemView.findViewById(R.id.imgBtnEdit);
            this.btnDelete = itemView.findViewById(R.id.imgBtnDelete);
            this.adt = adt;

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User u = listUser.get(getLayoutPosition());
                    ((MainActivity) ctx).setEdit(u);
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) ctx).xoaUser(listUser.get(getLayoutPosition()));
                }
            });
        }
    }
}
