package eryaz.software.panda.ui.dashboard.outbound.datPicking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import eryaz.software.panda.R
import eryaz.software.panda.data.models.dto.ErrorDialogDto
import eryaz.software.panda.data.persistence.TemporaryCashManager
import eryaz.software.panda.databinding.FragmentDatPickingListBinding
import eryaz.software.panda.ui.base.BaseFragment
import eryaz.software.panda.ui.dashboard.outbound.orderPicking.adapter.OrderWorkActivityListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DatPickingListFragment : BaseFragment() {
    override val viewModel by viewModel<DatPickingListVM>()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentDatPickingListBinding.inflate(layoutInflater)
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
        viewModel.getActiveWorkAction()

        viewModel.orderPickingList
            .observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

        viewModel.searchList()
            .observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

        viewModel.navigateToDetail
            .asLiveData()
            .observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(
                        DatPickingListFragmentDirections.actionDatPickingListFragmentToDatPickingDetailFragment()
                    )
                }
            }
    }

    override fun setClicks() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        adapter.onItemClick = {
            if (it.isLocked) {
                errorDialog.show(
                    context,
                    ErrorDialogDto(
                        titleRes = R.string.warning,
                        messageRes = R.string.locked_work_activity
                    )
                )
            }
            TemporaryCashManager.getInstance().workActivity = it
            viewModel.getWorkActionForPda()
        }
    }

    private val adapter by lazy {
        OrderWorkActivityListAdapter().also {
            binding.recyclerView.adapter = it
        }
    }
}