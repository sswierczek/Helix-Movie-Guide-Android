package com.androidmess.helix.discover.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidmess.helix.BR
import com.androidmess.helix.R
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.databinding.DiscoverFragmentBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.recyclerview.scrollEvents

class DiscoverFragment : ScopeFragment() {

    companion object {
        fun newInstance() = DiscoverFragment()
    }

    val discoverViewModel: DiscoverViewModel by viewModel()
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
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataContainer(binding?.discoverDataContainer)
        discoverViewModel.viewReady()
    }

    // FIXME Move to data binding
    private fun setupDataContainer(discoverDataContainer: RecyclerView?) {
        discoverDataContainer?.run {
            onScrolledToBottomDetector
                .scrollEvents(scrollEvents(), viewLifecycleOwner.lifecycleScope)
                .onScrolledToBottom
                .onEach {
                    discoverViewModel.onLoadNextData()
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
            setHasFixedSize(true)
            layoutManager = discoverLayoutManager
            adapter = dataAdapter
        }
    }
}