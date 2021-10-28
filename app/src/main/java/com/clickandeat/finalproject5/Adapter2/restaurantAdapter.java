package com.clickandeat.finalproject5.Adapter2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.clickandeat.finalproject5.Model.restaurantDomain;
import com.clickandeat.finalproject5.Interface.RestaurantClickListener;
import com.clickandeat.finalproject5.R;

import java.util.ArrayList;

public class restaurantAdapter extends RecyclerView.Adapter<restaurantAdapter.ViewHolder> {
    ArrayList<restaurantDomain> restaurantDomains;
    Context context;
    RestaurantClickListener listener;
    public restaurantAdapter(Context context) {
        this.context= context;
        restaurantDomains = new ArrayList<restaurantDomain>();

        restaurantDomain cat1 = new restaurantDomain();
        cat1.setTitle("Elwedean");
        cat1.setPic(ContextCompat.getDrawable(this.context, R.drawable.elwedean));
        restaurantDomains.add(cat1);


        restaurantDomain cat2 = new restaurantDomain();
        cat2.setTitle("Alofem");
        cat2.setPic(ContextCompat.getDrawable(this.context,R.drawable.alofem));
        restaurantDomains.add(cat2);

        restaurantDomain cat3 = new restaurantDomain();
        cat3.setTitle("Pala");
        cat3.setPic(ContextCompat.getDrawable(this.context,R.drawable.pala));
        restaurantDomains.add(cat3);

        this.restaurantDomains = restaurantDomains;
    }
    public restaurantAdapter(Context context, RestaurantClickListener listener) {
        this(context);
        if (listener != null)
            this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.viewholder_restaurant, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        restaurantDomain restaurantDomain = restaurantDomains.get(position);
        final String restaurantName = restaurantDomain.getTitle();
        holder.categoryName.setText(restaurantName);
        holder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantAdapter.this.listener.onRestaurantClick(restaurantName);
            }
        });

        holder.mainLayout.setBackground(restaurantDomain.getPic());

//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.categoryPic);
    }


    @Override
    public int getItemCount() {
        return restaurantDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button categoryName;
//        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}