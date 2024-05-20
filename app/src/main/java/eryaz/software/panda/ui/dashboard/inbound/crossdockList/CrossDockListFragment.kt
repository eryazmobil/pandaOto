package eryaz.software.panda.ui.dashboard.inbound.crossdockList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import eryaz.software.panda.databinding.FragmentCrossDockListBinding
import eryaz.software.panda.ui.base.BaseFragment
import eryaz.software.panda.util.adapter.inbound.adapter.CrossDockListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CrossDockListFragment : BaseFragment() {

    override val viewModel by viewModel<CrossDockListVM>()

    private val binding by lazy(LazyThreadSafetyMode.NONE){
        FragmentCrossDockListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        return binding.root
    }

    override fun subscribeToObservables() {
        viewModel.crossDockList
            .asLiveData()
            .observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
    }

    override fun setClicks() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private val adapter by lazy {
        CrossDockListAdapter().also {
            binding.recyclerView.adapter = it
        }
    }
}