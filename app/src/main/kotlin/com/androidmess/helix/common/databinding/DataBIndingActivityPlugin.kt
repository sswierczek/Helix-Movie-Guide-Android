package com.androidmess.helix.common.databinding

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.util.ArrayMap
import com.androidmess.helix.BR
import com.androidmess.helix.common.activity.plugin.CompositeActivityPlugin

class DataBindingActivityPlugin(
    private val activity: Activity,
    private val viewModel: ViewModel,
    private val resLayoutId: Int
) : CompositeActivityPlugin {

    private val bindings: MutableMap<Int, Any> = ArrayMap()

    fun addBinding(bindId: Int, objectToBind: Any) {
        bindings[bindId] = objectToBind
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ViewDataBinding = DataBindingUtil.setContentView(activity, resLayoutId)
        binding.setVariable(BR.viewModel, viewModel)
        for ((key, value) in bindings) {
            binding.setVariable(key, value)
        }
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStart() {
    }

    override fun onStop(isFinishing: Boolean) {
    }

    override fun onDestroy() {
    }
}