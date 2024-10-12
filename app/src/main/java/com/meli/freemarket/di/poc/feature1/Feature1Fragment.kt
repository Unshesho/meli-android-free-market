package com.meli.freemarket.di.poc.feature1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meli.freemarket.di.poc.feature1.presentation.KoinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Feature1Fragment : Fragment() {

    private val koinViewModel: KoinViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

        koinViewModel.sayHello("Ctm")
    }
}