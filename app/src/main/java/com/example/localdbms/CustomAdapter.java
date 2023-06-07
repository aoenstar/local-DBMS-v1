package com.example.localdbms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList obj_id, obj_name, location, sn;

    CustomAdapter(Activity activity, Context context, ArrayList obj_id, ArrayList obj_name, ArrayList location, ArrayList sn)
    {
        this.activity = activity;
        this.context = context;
        this.obj_id = obj_id;
        this.obj_name = obj_name;
        this.location = location;
        this.sn = sn;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.obj_id_text.setText(String.valueOf( obj_id.get(position)));
        holder.obj_name_text.setText(String.valueOf( obj_name.get(position)));
        holder.location_text.setText(String.valueOf( location.get(position)));
        holder.sn_text.setText(String.valueOf( sn.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(obj_id.get(position)));
                intent.putExtra("name", String.valueOf(obj_name.get(position)));
                intent.putExtra("location", String.valueOf(location.get(position)));
                intent.putExtra("sn", String.valueOf(sn.get(position)));
                activity.startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return obj_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView obj_id_text, obj_name_text, location_text, sn_text;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            obj_id_text = itemView.findViewById(R.id.obj_id_text);
            obj_name_text = itemView.findViewById(R.id.obj_name_text);
            location_text = itemView.findViewById(R.id.location_text);
            sn_text = itemView.findViewById(R.id.sn_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
