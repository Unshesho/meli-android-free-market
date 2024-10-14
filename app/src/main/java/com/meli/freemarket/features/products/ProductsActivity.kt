package com.meli.freemarket.features.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.meli.freemarket.databinding.ActivityProductsBinding
import com.meli.uicomponents.components.inputs.AttrsSearchInputText

class ProductsActivity : AppCompatActivity() {
    private var binding: ActivityProductsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onStart() {
        super.onStart()
        setSearch()
    }

    private fun setSearch() = binding?.apply {
        search.setAttributes(
            attrs = AttrsSearchInputText(
                hint = "Busca productos, marcas y más...",
                onSearch = {}
            )
        )
    }

    companion object {
        fun makeIntent(context: Context): Intent = Intent(context, ProductsActivity::class.java)
    }
}
