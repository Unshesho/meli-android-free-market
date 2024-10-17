package com.meli.freemarket.features.products.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meli.freemarket.R
import com.meli.freemarket.databinding.ActivitySearchBinding
import com.meli.freemarket.features.products.list.ProductsListActivity
import com.meli.uicomponents.components.inputs.AttrsSearchInputText

class SearchActivity : AppCompatActivity() {
    private var binding: ActivitySearchBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onStart() {
        super.onStart()
        setSearch()
    }

    private fun setSearch() = binding?.apply {
        search.setAttributes(
            attrs = AttrsSearchInputText(
                hint = getString(R.string.search_for_products),
                onSearch = {
                    val intent =
                        ProductsListActivity.makeIntent(this@SearchActivity, search.getSearchText())
                    startActivity(intent)
                }
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun makeIntent(context: Context): Intent = Intent(context, SearchActivity::class.java)
    }
}