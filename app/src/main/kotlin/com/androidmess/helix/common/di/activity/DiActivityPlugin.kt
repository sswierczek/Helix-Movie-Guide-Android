package com.androidmess.helix.common.di.activity

import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.activity.plugin.CompositeActivityPlugin
import org.koin.android.ext.android.releaseContext

class DiActivityPlugin(
    private val activity: CompositeAppCompatActivity,
    private val contextName: String
) : CompositeActivityPlugin {

    override fun onStop(isFinishing: Boolean) {
        if (isFinishing) {
            activity.releaseContext(contextName)
        }
    }
}