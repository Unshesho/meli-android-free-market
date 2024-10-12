package com.meli.freemarket.di.poc.feature1

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.meli.freemarket.di.poc.feature1.di.feature1Module
import org.koin.androidx.scope.activityScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class Feature1Activity: AppCompatActivity(), KoinScopeComponent {

    override val scope by activityScope()
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        loadKoinModules(feature1Module)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(feature1Module)
    }
}
