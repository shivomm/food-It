package com.shivom.foodit.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shivom.foodit.Interface.ItemClickListener;
import com.shivom.foodit.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView food_name;
    public ImageView food_image;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    private ItemClickListener itemClickListener;


    public FoodViewHolder(View itemView) {
        super(itemView);
     food_name=(TextView)itemView.findViewById(R.id.food_name);
     food_image=(ImageView)itemView.findViewById(R.id.food_image);
        itemView.setOnClickListener(this);
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onclick(view, getAdapterPosition(), false);
    }
}
