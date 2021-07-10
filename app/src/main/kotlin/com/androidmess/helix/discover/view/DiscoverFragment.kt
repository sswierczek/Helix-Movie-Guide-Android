package com.androidmess.helix.discover.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidmess.helix.BR
import com.androidmess.helix.R
import com.androidmess.helix.common.navigation.Navigator
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.databinding.DiscoverFragmentBinding
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment : ScopeFragment() {

    companion object {
        fun newInstance() = DiscoverFragment()
    }

    val discoverViewModel: DiscoverViewModel by viewModel()
    val navigator: Navigator by scope.inject()
    val dataAdapter: DiscoverAdapter by scope.inject()
    val discoverLayoutManager: LinearLayoutManager by scope.inject()
    val onScrolledToBottomDetector: RecyclerViewOnScrolledToBottomDetector by scope.inject()
    var binding: DiscoverFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO Create databinding fragments plugins
        binding = DataBindingUtil.inflate(inflater, R.layout.discover_fragment, container, false)
        binding?.run {
            lifecycleOwner = this@DiscoverFragment.activity
            setVariable(BR.viewModel, discoverViewModel)
            setVariable(BR.adapter, dataAdapter)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataContainer(binding?.discoverDataContainer)
        discoverViewModel.viewReady()
    }

    // FIXME Remove when navigator use android navigation
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        navigator.attach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        navigator.detach()
    }

    // FIXME Move to data binding
    private fun setupDataContainer(discoverDataContainer: RecyclerView?) {
        discoverDataContainer?.run {
            onScrolledToBottomDetector
                .scrollEvents(scrollEvents())
                .observe()
                .subscribe { discoverViewModel.onLoadNextData() }
            setHasFixedSize(true)
            layoutManager = discoverLayoutManager
            adapter = dataAdapter
        }
    }
}