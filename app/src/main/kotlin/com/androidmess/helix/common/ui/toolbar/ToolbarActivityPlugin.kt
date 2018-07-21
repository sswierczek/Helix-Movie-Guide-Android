package com.androidmess.helix.common.ui.toolbar

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.Toolbar
import com.androidmess.helix.common.activity.CompositeAppCompatActivity
import com.androidmess.helix.common.activity.plugin.CompositeActivityPlugin

class ToolbarActivityPlugin(
        private val activity: CompositeAppCompatActivity,
        private val toolbar: Toolbar?,
        @StringRes private val titleId: Int
) : CompositeActivityPlugin {

    override fun onCreate(savedInstanceState: Bundle?) {
        activity.run {
            toolbar?.title = getString(titleId)
            setSupportActionBar(toolbar)
        }
    }
}