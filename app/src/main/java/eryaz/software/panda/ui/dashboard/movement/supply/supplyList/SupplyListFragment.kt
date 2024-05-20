package eryaz.software.panda.ui.dashboard.movement.supply.supplyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import eryaz.software.panda.R
import eryaz.software.panda.data.models.dto.ErrorDialogDto
import eryaz.software.panda.data.persistence.TemporaryCashManager
import eryaz.software.panda.databinding.FragmentSuplyListBinding
import eryaz.software.panda.ui.base.BaseFragment
import eryaz.software.panda.ui.dashboard.outbound.orderPicking.adapter.OrderWorkActivityListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SupplyListFragment : BaseFragment() {

    override val viewModel by viewModel<SupplyListVM>()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSuplyListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        return binding.root
    }

    override fun subscribeToObservables() {

        viewModel.getActiveWorkAction()

        viewModel.supplyWorkActivityList.observe(viewLifecycleOwner) {
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
                        SupplyListFragmentDirections.actionSupplyListFragmentToSupplyShelfFragment()
                    )
                }
            }
    }

    override fun setClicks() {

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.toolbar.setMenuOnClickListener {
            popupMenu(it)
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

    private fun popupMenu(view: View) {
        PopupMenu(requireContext(), view).apply {
            inflate(R.menu.supply_list_menu)

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.create_work_activity -> {
                        findNavController()
                            .navigate(SupplyListFragmentDirections.actionSupplyListFragmentToCreateSupplyWorkActivityFragment())
                        true
                    }

                    else -> false
                }
            }
            show()
        }
    }

    private val adapter by lazy {
        OrderWorkActivityListAdapter().also {
            binding.recyclerView.adapter = it
        }
    }

}