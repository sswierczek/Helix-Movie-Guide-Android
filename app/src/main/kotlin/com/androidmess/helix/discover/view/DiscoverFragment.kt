package com.androidmess.helix.discover.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidmess.helix.BR
import com.androidmess.helix.R
import com.androidmess.helix.common.ui.recyclerview.RecyclerViewOnScrolledToBottomDetector
import com.androidmess.helix.databinding.DiscoverFragmentBinding
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class DiscoverFragment : Fragment() {

    companion object {
        fun newInstance() = DiscoverFragment()
    }

    val discoverViewModel: DiscoverViewModel by viewModel()
    val dataAdapter: DiscoverAdapter by currentScope.inject()
    val discoverLayoutManager: LinearLayoutManager by currentScope.inject()
    val onScrolledToBottomDetector: RecyclerViewOnScrolledToBottomDetector by currentScope.inject()
    var binding: DiscoverFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO Create databinding fragments plugins
        binding = DataBindingUtil.inflate(inflater, R.layout.discover_fragment, container, false)
        binding?.run {
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

    // FIXME Move to data binding
    private fun setupDataContainer(discoverDataContainer: RecyclerView?) {
        discoverDataContainer?.run {
            onScrolledToBottomDetector
                .scrollEvents(scrollEvents())
                .observe()
                .subscribe {
                    discoverViewModel.scroll.notifyChange()
                }
            setHasFixedSize(true)
            layoutManager = discoverLayoutManager
            adapter = dataAdapter
        }
    }
}