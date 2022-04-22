package com.example.recycleview;

import static com.example.recycleview.persondb.personDataClass.personDataClassList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycleview.persondb.PersonRoomDataBase;
import com.example.recycleview.persondb.personDataClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity implements PersonAdapter.RecycleViewItemOnClickListener{
    private PersonViewModel mPersonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton addButton= findViewById(R.id.Add);
        TextView textViewAge=findViewById(R.id.text_view_age);
        TextView textViewName=findViewById(R.id.text_view_name);
        TextView textViewJob=findViewById(R.id.text_view_job);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        mPersonViewModel=new ViewModelProvider(this).get(PersonViewModel.class);
        try {
            personDataClassList= (ArrayList<personDataClass>) getPersonData2();
            Log.d("personDataClassList", String.valueOf(personDataClassList.size()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PersonAdapter personAdapter=new PersonAdapter(personDataClassList,this);
        recyclerView.setAdapter(personAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        addToDBAuto();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= textViewName.getText().toString();
                String job= textViewJob.getText().toString();

                int age=0;
                if(name.equals("")){
                    Toast.makeText(getApplicationContext()," Nhap tuoi",Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    age = Integer.parseInt(textViewAge.getText().toString());
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext()," Nhap tuoi",Toast.LENGTH_SHORT).show();
                    return;
                }

                personDataClass personData=new personDataClass(name,age,job);
                // Add new Item to list
                personData.addToList();

                // A
                mPersonViewModel.insert(personData);
                Log.d("Data List",personData.getName());

                // Notify the Adapter
                personAdapter.notifyItemInserted(0);
                Log.d("Data List", personDataClassList.toString());

            }
        });
    }

    public List<personDataClass> getPersonData() throws ExecutionException, InterruptedException {

        Callable<List<personDataClass>> callable = new Callable<List<personDataClass>>() {
            @Override
            public List<personDataClass> call() throws Exception {
                return mPersonViewModel.getAllPersonList();
            }
        };

        Future<List<personDataClass>> future = Executors.newSingleThreadExecutor().submit(callable);

        return future.get();
    }

    public List<personDataClass> getPersonData2() throws ExecutionException, InterruptedException {

        Callable<List<personDataClass>> callable = new Callable<List<personDataClass>>() {
            @Override
            public List<personDataClass> call() throws Exception {
                return mPersonViewModel.getAllPersonList();
            }
        };

        CompletableFuture<List<personDataClass>> completableFuture = CompletableFuture.supplyAsync(()-> mPersonViewModel.getAllPersonList());

        return completableFuture.get();
    }

    public void addToDBAuto(){
        String name= "auto_added";
        String job= "dev";
        int age=20;

        for(int i =0;i<50000;i++) {
            personDataClass personData = new personDataClass(name, age, job);
            // Add new Item to list
//            personData.addToList();

            // A
            mPersonViewModel.insert(personData);
        }

    }


    @Override
    public void onRecycleViewClickListener(int position) {
        Toast.makeText(MainActivity.this,"Clicked: "+position,Toast.LENGTH_SHORT).show();
    }
}