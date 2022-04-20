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

    List<Product> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        readFromDatabase();




    }
    public void rc(List<Product> data ){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ProductRecycleViewAdapter adapter = new ProductRecycleViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));}



    public void readFromDatabase()
    {
        db= FirebaseFirestore.getInstance();
        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {


//                                product.name = document.get("name").toString();
//                                product.bran = document.get("brand").toString();
//                                product.description = document.get("description").toString();
//                                product.imageAddress = document.get("imageAddress").toString();
                                Product product1 = new Product(document.get("name").toString(),document.get("brand").toString(), document.get("description").toString(), document.get("imageAddress").toString());


                                Log.d(TAG, document.getId() + " => " + product1.name);
                                data.add(product1);
                                rc(data);
                            }
                        } else
                        {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


    }


}