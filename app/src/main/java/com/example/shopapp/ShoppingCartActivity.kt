package com.example.shopapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private var productPrice: Int = 0

    private var totalPrice: Int = 0
    private var quantity: Int = 1
    private lateinit var totalTextView: TextView
    private lateinit var subtotalTextView: TextView
    private lateinit var subtotalLabelText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        recyclerView = findViewById(R.id.cartRecyclerView)
        totalTextView = findViewById(R.id.totalText)
        subtotalTextView = findViewById(R.id.subtotalText)
        subtotalLabelText = findViewById(R.id.subtotalLabel)

        productPrice = intent.getIntExtra("PRODUCT_PRICE", 0)
        totalPrice = productPrice

        adapter = CartAdapter(productPrice) { updatedTotal, updatedQuantity ->
            totalPrice = updatedTotal
            quantity = updatedQuantity
            totalTextView.text = "$$totalPrice"
            subtotalTextView.text = "$$totalPrice"
            subtotalLabelText.text = "Subtotal $quantity items"
        }


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val placeOrderBtn: Button = findViewById(R.id.placeOrderButton)
        val backButton : ImageView = findViewById(R.id.backButton)
        placeOrderBtn.setOnClickListener {
            val intent = Intent(this, PaymentSuccessActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            val intent = Intent(this, ProductDetailActivity::class.java)
            startActivity(intent)
        }

        totalTextView.text = "$$totalPrice"
        subtotalTextView.text = "$$totalPrice"
        subtotalLabelText.text = "Subtotal $quantity items"
    }
}
