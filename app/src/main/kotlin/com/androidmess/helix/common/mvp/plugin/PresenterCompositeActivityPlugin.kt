package com.androidmess.helix.common.mvp.plugin

import android.os.Bundle
import com.androidmess.helix.common.activity.plugin.CompositeActivityPlugin
import com.androidmess.helix.common.presentation.Mvp

class PresenterCompositeActivityPlugin<VIEW : Mvp.View>(private val view: VIEW,
                                                        private val presenter: Mvp.Presenter<VIEW>)
    : CompositeActivityPlugin {

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter.connect(view)
    }

    override fun onResume() {
        // nothing
    }

    override fun onPause() {
        // nothing
    }

    override fun onStart() {
        presenter.visible(view)
    }

    override fun onStop(isFinishing: Boolean) {
        presenter.invisible()
        if (isFinishing) {
            presenter.disconnect()
        }
    }

    override fun onDestroy() {
        // nothing
    }
}