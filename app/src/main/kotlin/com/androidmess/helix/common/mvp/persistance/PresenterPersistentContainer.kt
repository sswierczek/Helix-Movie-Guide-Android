package com.androidmess.helix.common.mvp.persistance

import android.arch.lifecycle.ViewModel
import com.androidmess.helix.common.presentation.Mvp

// FIXME Make it better :D
class PresenterPersistentContainer : ViewModel() {

    var presenter: Mvp.Presenter<*>? = null
}