package com.example.shopapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val price: Int,
    private val onTotalChanged: (total: Int, quantity: Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var quantity = 1
    private var total = price

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quantityText: TextView = itemView.findViewById(R.id.quantityText)
        val plusBtn: CardView = itemView.findViewById(R.id.increaseButton)
        val minusBtn: CardView = itemView.findViewById(R.id.decreaseButton)
        val itemTotal: TextView = itemView.findViewById(R.id.itemTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.quantityText.text = quantity.toString()
        holder.itemTotal.text = "$$total"

        holder.plusBtn.setOnClickListener {
            quantity++
            total = price * quantity
            holder.quantityText.text = quantity.toString()
            holder.itemTotal.text = "$$total"
            onTotalChanged(total, quantity)
        }

        holder.minusBtn.setOnClickListener {
            if (quantity > 1) {
                quantity--
                total = price * quantity
                holder.quantityText.text = quantity.toString()
                holder.itemTotal.text = "$$total"
                onTotalChanged(total, quantity)
            }
        }
    }

    override fun getItemCount(): Int = 1 // Only one item
}
