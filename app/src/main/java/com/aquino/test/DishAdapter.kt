package com.aquino.test

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class DishAdapter(private val dishList: List<Dish>, private val itemClickListener: (Dish) -> Unit) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishImage: ImageView = itemView.findViewById(R.id.dishImage)
        val dishName: TextView = itemView.findViewById(R.id.dishName)
        val dishPrice: TextView = itemView.findViewById(R.id.dishPrice)
        val btnReserve: MaterialButton = itemView.findViewById(R.id.btnReserve)

        fun bind(dish: Dish) {
            dishImage.setImageResource(dish.imageResId)
            dishName.text = dish.name
            dishPrice.text = "${dish.price} soles"
            btnReserve.setOnClickListener {
                itemClickListener(dish)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(dishList[position])
    }

    override fun getItemCount(): Int = dishList.size
}
