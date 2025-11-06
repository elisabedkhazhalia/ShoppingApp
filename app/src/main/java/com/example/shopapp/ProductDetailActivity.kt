package com.example.shopapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true // Dark icons on light background
        }

        val addToCartBtn: Button = findViewById(R.id.addToCartBtn)
        val productPrice = 120 // Example price

        addToCartBtn.setOnClickListener {
            Log.d("ProductDetail", "Add to Cart clicked")
            val intent = Intent(this, ShoppingCartActivity::class.java)
            intent.putExtra("PRODUCT_PRICE", productPrice)
            startActivity(intent)
        }

    }
}
