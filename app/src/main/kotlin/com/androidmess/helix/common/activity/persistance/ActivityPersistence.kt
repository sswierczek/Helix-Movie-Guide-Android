package com.androidmess.helix.common.activity.persistance

import android.arch.lifecycle.ViewModel
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

class ActivityPersistence : ViewModel() {

    private val container = ConcurrentHashMap<String, Any>()

    fun put(any: Any) {
        any::class.qualifiedName?.let {
            container.put(it, any)
        }
    }

    fun get(clazz: KClass<*>): Any? {
        return container[clazz.qualifiedName]
    }
}