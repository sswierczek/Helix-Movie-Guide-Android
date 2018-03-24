package com.androidmess.helix.common.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androidmess.helix.common.activity.plugin.CompositeActivityPlugin

open class CompositeAppCompatActivity : AppCompatActivity() {

    private val plugins = HashSet<CompositeActivityPlugin>()

    protected fun registerPlugin(plugin: CompositeActivityPlugin) {
        plugins.add(plugin)
    }

    protected fun unregisterPlugin(plugin: CompositeActivityPlugin) {
        plugins.remove(plugin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        plugins.forEach { it.onCreate(savedInstanceState) }
    }

    override fun onResume() {
        super.onResume()
        plugins.forEach { it.onResume() }
    }

    override fun onPause() {
        super.onPause()
        plugins.forEach { it.onPause() }
    }

    override fun onStart() {
        super.onStart()
        plugins.forEach { it.onStart() }
    }

    override fun onStop() {
        super.onStop()
        plugins.forEach { it.onStop(isFinishing) }
    }

    override fun onDestroy() {
        super.onDestroy()
        plugins.forEach { it.onDestroy(isFinishing) }
    }
}