package com.meli.freemarket.features.products.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meli.freemarket.R
import com.meli.freemarket.databinding.ActivityProductsBinding
import com.meli.uicomponents.components.inputs.AttrsSearchInputText

class ProductsListActivity : AppCompatActivity() {
    private var binding: ActivityProductsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onStart() {
        super.onStart()
        binding?.activityProductsSearch?.apply {
            val searchText = intent.getStringExtra("SEARCH")
            setAttributes(
                attrs = AttrsSearchInputText(
                    hint = getString(R.string.search_for_products),
                    searchText = searchText.orEmpty(),
                    onSearch = {}
                )
            )
        }
    }

    companion object {
        fun makeIntent(context: Context): Intent = Intent(context, ProductsListActivity::class.java)
    }
}
