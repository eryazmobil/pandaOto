package eryaz.software.panda.ui.dashboard.inbound.placement.placementDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eryaz.software.panda.databinding.CrossdockTransfersDialogLayoutBinding
import eryaz.software.panda.ui.base.BaseDialogFragment

class CrossDockTransferDialogFragment : BaseDialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        CrossdockTransfersDialogLayoutBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
