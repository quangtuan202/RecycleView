package com.example.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<DataClass> mPersonData;
    public PersonAdapter(ArrayList<DataClass> personData){
        mPersonData=personData;

    }
    @NonNull

//    We need to implement all three to finish the adapter

    @Override
//    onCreateViewHolder to inflate the item layout and create the holder
    // Usually involves inflating a layout from XML and returning the holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get context for layout inflater
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        // Inflate the custom layout
        View personView=layoutInflater.inflate(R.layout.item_custom_row,parent,false);
        // Get ViewHolder for personView
        ViewHolder viewHolder=new ViewHolder(personView);

        return viewHolder;
    }

    @Override
//    onBindViewHolder to set the view attributes based on the data
    // Involves populating data into the item through holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataClass person=mPersonData.get(position);
        holder.textAge.setText(String.valueOf(person.getAge()));
        holder.textName.setText(person.getName());
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPersonData.remove(holder.getAdapterPosition());
                PersonAdapter.this.notifyItemRemoved(holder.getAdapterPosition());
            }
        });

    }

    @Override
//    getItemCount to determine the number of items
    public int getItemCount() {
        return mPersonData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // Variables for views that will be rendered in the Recycle view
        public TextView textAge;
        public TextView textName;
        public ImageView imgView;
        public Button buttonDelete;

        // Constructor for ViewHolder
        public ViewHolder(View itemView){
            super(itemView);
            textAge=itemView.findViewById(R.id.tv_item_age);
            textName=itemView.findViewById(R.id.tv_item_name);
            imgView=itemView.findViewById(R.id.img_item);
            buttonDelete=itemView.findViewById(R.id.Delete);
        }

    }
}
