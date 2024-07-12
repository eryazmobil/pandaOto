package eryaz.software.panda.ui.dashboard.recording.addPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import eryaz.software.panda.R
import eryaz.software.panda.data.models.dto.ProductDto
import eryaz.software.panda.databinding.FragmentAddPackageBinding
import eryaz.software.panda.ui.base.BaseFragment
import eryaz.software.panda.ui.dashboard.recording.dialog.ProductListDialogFragment
import eryaz.software.panda.ui.dashboard.recording.recordBarcode.BarcodeRecordingFragmentDirections
import eryaz.software.panda.util.extensions.hideSoftKeyboard
import eryaz.software.panda.util.extensions.observe
import eryaz.software.panda.util.extensions.parcelable
import eryaz.software.panda.util.extensions.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddPackageFragment : BaseFragment() {

    override val viewModel by viewModel<AddPackageVM>()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentAddPackageBinding.inflate(layoutInflater)
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

    override fun setClicks() {

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.productCodeEdt.setOnEditorActionListener { _, actionId, _ ->
            val isValidBarcode = viewModel.searchProductCode.value.trim().isNotEmpty()

            if (((actionId == EditorInfo.IME_ACTION_UNSPECIFIED || actionId == EditorInfo.IME_ACTION_DONE)) || actionId == EditorInfo.IME_ACTION_DONE && isValidBarcode) {
                viewModel.getProductByCode()
            }

            hideSoftKeyboard()
            true
        }

        binding.searchProductCodeLyt.setEndIconOnClickListener {
            findNavController().navigate(
                AddPackageFragmentDirections.actionAddPackageFragmentToProductListDialogFragment()
            )
        }
    }

    override fun subscribeToObservables() {

        setFragmentResultListener(ProductListDialogFragment.REQUEST_KEY) { _, bundle ->
            val dto = bundle.parcelable<ProductDto>(ProductListDialogFragment.ARG_PRODUCT_DTO)
            dto?.let {
                viewModel.setEnteredProduct(it)
            }
        }

        viewModel.showProductDetail.observe(this) {
            if (it) {
                binding.edtQuantity.requestFocus()
            }
        }

    }

    override fun onStart() {
        super.onStart()

        binding.productCodeEdt.requestFocus()
    }

}