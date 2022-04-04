package com.example.recycleview;

import static com.example.recycleview.DataClass.dataClassList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton addButton= findViewById(R.id.Add);
        TextView textViewAge=findViewById(R.id.text_view_age);
        TextView textViewName=findViewById(R.id.text_view_name);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        PersonAdapter personAdapter=new PersonAdapter(dataClassList);
        recyclerView.setAdapter(personAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= textViewName.getText().toString();
                int age = Integer.parseInt(textViewAge.getText().toString());

                DataClass personData=new DataClass(name,age);
                // Add new Item to list
                personData.addToList();
                Log.d("Data List",personData.getName());

                // Notify the Adapter
                personAdapter.notifyItemInserted(0);
                Log.d("Data List",dataClassList.toString());

            }
        });
    }


}