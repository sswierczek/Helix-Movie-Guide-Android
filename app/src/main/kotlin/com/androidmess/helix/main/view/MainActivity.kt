package com.androidmess.helix.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androidmess.helix.R
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.databinding.DataBindingActivityPlugin
import com.androidmess.helix.databinding.ActivityMainBinding

class MainActivity : CompositeAppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
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