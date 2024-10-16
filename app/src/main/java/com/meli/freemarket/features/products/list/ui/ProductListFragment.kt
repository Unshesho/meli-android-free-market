package com.meli.freemarket.features.products.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meli.freemarket.databinding.FragmentProductListBinding

class ProductListFragment : Fragment() {
    private var binding: FragmentProductListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) binding = FragmentProductListBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}