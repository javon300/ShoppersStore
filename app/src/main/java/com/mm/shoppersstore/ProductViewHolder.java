package com.mm.shoppersstore;

//import android.support.* ;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder
{
        TextView productName;
        TextView productDescription;
        TextView productBrand;
        View imageView;

        ProductViewHolder(View itemView)
        {
            super(itemView);
            productName = (TextView)itemView.findViewById(R.id.cardNameTf);
            productBrand = (TextView)itemView.findViewById(R.id.cardBranTf);
            productDescription = (TextView)itemView.findViewById(R.id.cardDescriptionTf);
            imageView  = (ImageView) itemView.findViewById(R.id.cardImage);
        }
    }
