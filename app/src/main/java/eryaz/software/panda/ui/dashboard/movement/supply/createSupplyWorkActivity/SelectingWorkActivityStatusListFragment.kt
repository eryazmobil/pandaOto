package eryaz.software.panda.ui.dashboard.movement.supply.createSupplyWorkActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import eryaz.software.panda.data.enums.ReplenishStatusEnum
import eryaz.software.panda.databinding.FragmentSelectingWorkActivityStatusListBinding
import eryaz.software.panda.ui.base.BaseFragment
import eryaz.software.panda.util.adapter.movement.supply.replenishmentTypeAdapter.ReplenishmentTypeAdapter


class SelectingWorkActivityStatusListFragment : BaseFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSelectingWorkActivityStatusListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        return binding.root
    }

    override fun subscribeToObservables() {

        adapter.submitList(ReplenishStatusEnum.entries.toList())

    }

    override fun setClicks() {

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        adapter.onItemSelected = {
            val action = SelectingWorkActivityStatusListFragmentDirections
                .actionCreateSupplyWorkActivityFragmentToCreateSupplyWorkActivityAndShelfListInformation()
            action.supplyStatus = it.status
            findNavController().navigate(action)
        }

    }

    private val adapter by lazy {
        ReplenishmentTypeAdapter().also {
            binding.replenishmentRecycler.adapter = it
        }
    }


}