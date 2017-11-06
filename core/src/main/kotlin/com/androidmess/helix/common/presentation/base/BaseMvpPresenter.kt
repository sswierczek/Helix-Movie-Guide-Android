package com.androidmess.helix.common.presentation.base

import com.androidmess.helix.common.presentation.Mvp
import com.androidmess.helix.common.rx.SchedulersInjector
import java.lang.ref.WeakReference

/**
 * Presenter that provides basic implementation for handling views.
 */
abstract class BaseMvpPresenter<VIEW : Mvp.View>(protected val schedulers: SchedulersInjector)
    : Mvp.Presenter<VIEW> {

    private lateinit var view: WeakReference<VIEW>

    override fun connect(view: VIEW) {
        this.view = WeakReference(view)
    }

    override fun visible(view: VIEW) {
        this.view = WeakReference(view)
    }

    override fun disconnect() {
        this.view.clear()
    }

    protected fun getView(): VIEW? {
        return view.get()
    }
}