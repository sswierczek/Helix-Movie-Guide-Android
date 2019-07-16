package com.androidmess.helix.common.databinding.extensions

import androidx.databinding.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

@Suppress("UNCHECKED_CAST")
fun <T : Observable> T.addOnPropertyChanged(callback: (T) -> Unit): Disposable =
        object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) =
                    callback(observable as T)
        }.also { addOnPropertyChangedCallback(it) }.let {
            Disposables.fromAction { removeOnPropertyChangedCallback(it) }
        }
