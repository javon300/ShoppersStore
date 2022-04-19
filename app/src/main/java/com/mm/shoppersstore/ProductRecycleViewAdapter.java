package com.mm.shoppersstore;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    List<Product> list = Collections.emptyList();
    Context context;

    public ProductRecycleViewAdapter(List<Product> data, Application application) {
        this.list = data;
        this.context = application;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pruduct_card, parent, false);
        ProductViewHolder holder = new ProductViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.productName.setText(list.get(position).name);
        holder.productBrand.setText(list.get(position).bran);
        holder.productDescription.setText(list.get(position).description);
        //holder.view.setImageResource(list.get(position).imageAddress);

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
