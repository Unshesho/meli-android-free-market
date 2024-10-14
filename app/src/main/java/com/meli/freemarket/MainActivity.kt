package com.meli.freemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meli.freemarket.databinding.ActivityMainBinding
import com.meli.freemarket.features.products.list.ProductsListActivity
import com.meli.freemarket.features.products.search.SearchActivity
import com.meli.utils.animations.onAnimationEnd

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onStart() {
        super.onStart()
        runAppOnAnimationEnd()
    }

    private fun runAppOnAnimationEnd() = binding?.splashAnimation?.apply {
        onAnimationEnd {
            goToSearch()
            finish()
        }
    }

    private fun goToSearch() {
        val intent = SearchActivity.makeIntent(this)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
