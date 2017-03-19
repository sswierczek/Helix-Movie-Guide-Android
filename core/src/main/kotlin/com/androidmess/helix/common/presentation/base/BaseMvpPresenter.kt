package com.androidmess.helix.common.presentation.base

import com.androidmess.helix.common.presentation.Mvp
import java.lang.ref.WeakReference

/**
 * Presenter that provides basic implementation for handling views.
 */
abstract class BaseMvpPresenter<VIEW : Mvp.View> : Mvp.Presenter<VIEW> {

    protected var view: WeakReference<VIEW>? = null

    override fun connect(view: VIEW) {
        this.view = WeakReference(view)
    }

    override fun disconnect() {
        this.view?.clear()
    }
}