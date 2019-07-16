package com.androidmess.helix.common.databinding

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.util.ArrayMap
import com.androidmess.helix.BR
import com.androidmess.helix.common.activity.plugin.CompositeActivityPlugin

class DataBindingActivityPlugin<T : ViewDataBinding>(
    private val activity: Activity,
    private val viewModel: ViewModel,
    private val resLayoutId: Int,
    private val useBindings: (T) -> Unit
) : CompositeActivityPlugin {

    private val bindings: MutableMap<Int, Any> = ArrayMap()

    fun addBinding(bindId: Int, objectToBind: Any) {
        bindings[bindId] = objectToBind
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = DataBindingUtil.setContentView(activity, resLayoutId) as T
        binding.run {
            setVariable(BR.viewModel, viewModel)
            for ((key, value) in bindings) {
                setVariable(key, value)
            }
            useBindings.invoke(this)
        }
    }
}
