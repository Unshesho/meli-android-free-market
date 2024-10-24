package com.meli.freemarket.features.products.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.meli.freemarket.R
import com.meli.freemarket.databinding.ActivityProductsBinding
import com.meli.freemarket.features.products.di.productsModule
import com.meli.freemarket.features.products.ui.detail.ProductDetailFragment
import com.meli.freemarket.features.products.ui.list.ProductListFragment
import com.meli.uicomponents.components.inputs.AttrsSearchInputText
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class ProductsActivity : AppCompatActivity() {
    private var binding: ActivityProductsBinding? = null
    private var productsModule: Module = productsModule()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDependencies()
        if (binding == null) binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onStart() {
        super.onStart()
        setupListFragment()
    }

    private fun setupListFragment() {
        binding?.activityProductsSearch?.apply {
            val searchText = intent.getStringExtra(SEARCH)
            setAttributes(
                attrs = AttrsSearchInputText(
                    hint = getString(R.string.search_for_products),
                    searchText = searchText.orEmpty(),
                    onSearch = {
                        intent.putExtra(SEARCH, it)
                        when (val fragment = getCurrentFragment()) {
                            is ProductListFragment -> fragment.refresh(it)
                            is ProductDetailFragment -> {
                                fragment.findNavController().navigateUp()
                                (getCurrentFragment() as? ProductListFragment)?.refresh(it)
                            }

                            else -> {}
                        }
                    }
                )
            )
        }
    }

    private fun getCurrentFragment(): Fragment? =
        supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.firstOrNull { it.isVisible }

    private fun setupDependencies() {
        try {
            loadKoinModules(productsModule)
        } catch (e: Exception) {
        }
    }
    private fun unloadDependencies() {
        try {
            unloadKoinModules(productsModule)
        } catch (e: Exception) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadDependencies()
        binding = null
    }

    companion object {
        const val SEARCH = "SEARCH"
        fun makeIntent(context: Context, productText: String?): Intent =
            Intent(context, ProductsActivity::class.java).also {
                it.putExtra(SEARCH, productText)
            }
    }
}
