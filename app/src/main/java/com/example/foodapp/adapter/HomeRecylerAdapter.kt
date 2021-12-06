package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.model.Restaurants
import com.squareup.picasso.Picasso

class HomeRecylerAdapter(val context: Context, val itemList: ArrayList<Restaurants>): RecyclerView.Adapter<HomeRecylerAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_home_single_row, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val restaurant = itemList[position]
        holder.txtDishName.text = restaurant.restaurantName
        holder.txtDishRating.text = restaurant.restaurantRating
        holder.txtDishPrice.text = "${restaurant.restaurantPrice}/person"
        Picasso.get().load(restaurant.restaurantImage).into(holder.imgDishImage)

        holder.cvContent.setOnClickListener{
            Toast.makeText(context, "Clicked on ${holder.txtDishName.text}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class HomeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtDishName: TextView = view.findViewById(R.id.txtDishName)
        val txtDishRating: TextView = view.findViewById(R.id.txtDishRating)
        val txtDishPrice: TextView = view.findViewById(R.id.txtDishPrice)
        val imgDishImage: ImageView = view.findViewById(R.id.imgDishImage)
        val cvContent: CardView = view.findViewById(R.id.cvContent)
    }
}