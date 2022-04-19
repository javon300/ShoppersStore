package com.mm.shoppersstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
{
    private static final String TAG = "HOME CLASS";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        readFromDatabase();
        List<Product> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ProductRecycleViewAdapter adapter = new ProductRecycleViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Product> fill_with_data() {

        List<Product> data = new ArrayList<>();
        data.add(new Product("C","sdfsdf","sdfsf"));
        data.add(new Product("C++", "",""));
        data.add(new Product("C++", "",""));
        data.add(new Product("C++", "",""));
        data.add(new Product("C++", "",""));
        data.add(new Product("C++", "",""));
        data.add(new Product("C++", "",""));
        data.add(new Product("C++", "",""));
        data.add(new Product("C++", "",""));
        data.add(new Product("C++", "",""));
        data.add(new Product("C++", "",""));

        return data;
    }


    public void readFromDatabase()
    {
        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


}