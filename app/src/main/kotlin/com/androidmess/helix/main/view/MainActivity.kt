package com.androidmess.helix.main.view

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.databinding.DataBindingActivityPlugin
import com.androidmess.helix.common.di.activity.DiActivityPlugin
import com.androidmess.helix.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : CompositeAppCompatActivity() {

    companion object {
        const val CONTEXT_NAME = "DiscoverActivityContext"
    }

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        registerPlugin(DiActivityPlugin(this, CONTEXT_NAME))
        registerPlugin(
            DataBindingActivityPlugin<ActivityMainBinding>(
                this,
                mainViewModel,
                R.layout.activity_main
            ) {
                it.mainToolbar.title = getString(R.string.app_name)
                setSupportActionBar(it.mainToolbar)
                it.mainToolbar.setupWithNavController(navController())
            })
        super.onCreate(savedInstanceState)
    }

    override fun onSupportNavigateUp() =
        navController().navigateUp()

    private fun CompositeAppCompatActivity.navController() =
        findNavController(this, R.id.nav_host_fragment)
}