package com.example.recycleview;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.persondb.PersonDAO;
import com.example.recycleview.persondb.PersonRepository;
import com.example.recycleview.persondb.personDataClass;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private ArrayList<personDataClass> mPersonData;
    private PersonRepository mRepository;
    private RecycleViewItemOnClickListener recycleViewItemOnClickListener;

    // Interface
    public interface RecycleViewItemOnClickListener {
        void onRecycleViewClickListener(int position);
    }

    // View holder nested class
    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Variables for views that will be rendered in the Recycle view
        public TextView textAge;
        public TextView textName;
        public TextView textJob;
        public ImageView imgView;
        public Button buttonDelete;
        RecycleViewItemOnClickListener recycleViewItemOnClickListener;

        // Constructor for ViewHolder
        public PersonViewHolder(View itemView,RecycleViewItemOnClickListener recycleViewItemOnClickListener ){
            super(itemView);
            textAge=itemView.findViewById(R.id.tv_item_age);
            textName=itemView.findViewById(R.id.tv_item_name);
            textJob=itemView.findViewById(R.id.tv_item_job);
            imgView=itemView.findViewById(R.id.img_item);
            buttonDelete=itemView.findViewById(R.id.Delete);
            this.recycleViewItemOnClickListener=recycleViewItemOnClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.recycleViewItemOnClickListener.onRecycleViewClickListener(getAdapterPosition());
        }
    }



    public PersonAdapter(ArrayList<personDataClass> personData,RecycleViewItemOnClickListener recycleViewItemOnClickListener){
        this.recycleViewItemOnClickListener=recycleViewItemOnClickListener;
        this.mPersonData=personData;
    }
    @NonNull

//    We need to implement all three to finish the adapter

    @Override
//    onCreateViewHolder to inflate the item layout and create the holder
    // Usually involves inflating a layout from XML and returning the holder
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get context for layout inflater
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        // get application for room DB
        Application application = new App();
        mRepository=new PersonRepository(application);
        // Inflate the custom layout
        View personView=layoutInflater.inflate(R.layout.item_custom_row,parent,false);
        // Get ViewHolder for personView
        PersonViewHolder viewHolder=new PersonViewHolder(personView,this.recycleViewItemOnClickListener);
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // handle item click
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"clicked"+viewHolder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
//            }
//        });

        return viewHolder;
    }

    @Override
//    onBindViewHolder to set the view attributes based on the data
    // Involves populating data into the item through holder
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        personDataClass person=mPersonData.get(position);
        holder.textAge.setText(String.valueOf(person.getAge()));
        holder.textName.setText(person.getName());
        holder.textJob.setText(person.getJob());
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mRepository.deletePerson(mPersonData.get(holder.getAdapterPosition()));
                mRepository.deletePersonById(mPersonData.get(holder.getAdapterPosition()).getId());
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



}
