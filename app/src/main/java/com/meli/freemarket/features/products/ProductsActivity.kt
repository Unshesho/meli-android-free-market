package com.meli.freemarket.features.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.meli.freemarket.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {
    private var binding: ActivityProductsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        if (binding == null) binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    companion object {
        fun makeIntent(context: Context): Intent = Intent(context, ProductsActivity::class.java)
    }
}
