package eryaz.software.panda.ui.dashboard.outbound.controlPoint.orderHeaderDialog.controlPointDetail.updatePackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import eryaz.software.panda.R
import eryaz.software.panda.databinding.DialogUpdatePackageControlBinding
import eryaz.software.panda.ui.base.BaseDialogFragment
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener
import eryaz.software.panda.util.extensions.observe
import eryaz.software.panda.util.extensions.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UpdatePackageControlDialog : BaseDialogFragment() {
    private val safeArgs by navArgs<UpdatePackageControlDialogArgs>()

    override val viewModel by viewModel<UpdatePackageControlVM> {
        parametersOf(safeArgs.packageDto, safeArgs.packageId)
    }

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        DialogUpdatePackageControlBinding.inflate(layoutInflater)
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

    override fun setClicks() {
        binding.saveBtn.setOnSingleClickListener {
            viewModel.createPackage()
        }
    }

    override fun subscribeToObservables() {
        viewModel.savePackage.observe(this) {
            if (it) {
                toast(getString(R.string.packageUpdated))
            }
        }
    }
}