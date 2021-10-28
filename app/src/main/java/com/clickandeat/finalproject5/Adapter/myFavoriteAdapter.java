package com.clickandeat.finalproject5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.clickandeat.finalproject5.Model.myFavorite;
import com.clickandeat.finalproject5.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class myFavoriteAdapter extends RecyclerView.Adapter<myFavoriteAdapter.ViewHolder>{
    Context context;
    List<myFavorite> myFavoritesLst;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public myFavoriteAdapter(Context context, List<myFavorite> myFavoritesLst) {
        this.context = context;
        this.myFavoritesLst = myFavoritesLst;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public myFavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myFavoriteAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_in_favorite, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull myFavoriteAdapter.ViewHolder holder, int position) {
        holder.nameMana.setText(myFavoritesLst.get(position).getName());
        holder.price.setText(myFavoritesLst.get(position).getPrice());
        holder.favRating.setText(myFavoritesLst.get(position).getRating());
        holder.resturantName.setText(myFavoritesLst.get(position).getResturant());

        Glide.with(context).load(myFavoritesLst.get(position).getImage()).into(holder.imageItem);
        Glide.with(context).load(myFavoritesLst.get(position).getImage()).into(holder.addToFaivrate);


    }

    @Override
    public int getItemCount() {
        return myFavoritesLst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem ;
        TextView nameMana,favRating,  price, resturantName  ;
        ImageButton addToFaivrate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameMana = itemView.findViewById(R.id.nameManaF);
            price = itemView.findViewById(R.id.priceF);
            resturantName = itemView.findViewById(R.id.resturantNameF);
            imageItem = itemView.findViewById(R.id.imageItemF);
            favRating = itemView.findViewById(R.id.fav_rating);
            addToFaivrate = itemView.findViewById(R.id.addToFaivrateF);


        }
    }
}
