package eryaz.software.panda.ui.dashboard.movement.transferShelf

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
import eryaz.software.panda.databinding.FragmentTransferAddressBinding
import eryaz.software.panda.ui.base.BaseFragment
import eryaz.software.panda.ui.dashboard.recording.dialog.ProductListDialogFragment
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener
import eryaz.software.panda.util.extensions.hideSoftKeyboard
import eryaz.software.panda.util.extensions.observe
import eryaz.software.panda.util.extensions.parcelable
import eryaz.software.panda.util.extensions.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransferShelfFragment : BaseFragment() {
    override val viewModel by viewModel<TransferShelfVM>()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentTransferAddressBinding.inflate(layoutInflater)
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
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.searchEdt.setOnEditorActionListener { _, actionId, _ ->
            val isValidBarcode = viewModel.searchProduct.value.trim().isNotEmpty()

            if ((actionId == EditorInfo.IME_ACTION_UNSPECIFIED || actionId == EditorInfo.IME_ACTION_DONE) && isValidBarcode) {
                viewModel.getBarcodeByCode()
            }

            hideSoftKeyboard()
            true
        }

        binding.exitShelfAddressEdt.setOnEditorActionListener { _, actionId, _ ->
            val isValidAddress = viewModel.exitShelfValue.value.trim().isNotEmpty()

            if ((actionId == EditorInfo.IME_ACTION_UNSPECIFIED || actionId == EditorInfo.IME_ACTION_DONE) && isValidAddress) {

                viewModel.getShelfByCode(viewModel.exitShelfValue.value.trim(), false)
            }

            hideSoftKeyboard()
            true
        }

        binding.enterShelfAddressEdt.setOnEditorActionListener { _, actionId, _ ->
            val isValidAddress = viewModel.enterShelfValue.value.trim().isNotEmpty()

            if ((actionId == EditorInfo.IME_ACTION_UNSPECIFIED || actionId == EditorInfo.IME_ACTION_DONE)  && isValidAddress) {

                viewModel.getShelfByCode(viewModel.enterShelfValue.value.trim(), true)
            }

            hideSoftKeyboard()
            true
        }

        binding.transferBtn.setOnSingleClickListener {
            viewModel.createAddressMovement()
        }

        binding.searchProductBarcode.setEndIconOnClickListener {
            findNavController().navigate(
                TransferShelfFragmentDirections.actionTransferAddressFragmentToProductListDialogFragment()
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

        viewModel.exitShelfId.asLiveData()
            .observe(viewLifecycleOwner) {
                if (it.toInt() != 0)
                    binding.edtQuantity.requestFocus()
            }

        viewModel.transferSuccess.asLiveData()
            .observe(viewLifecycleOwner) {
                toast(getString(R.string.transfer_success))
                binding.searchEdt.requestFocus()
            }

        viewModel.showProductDetail.observe(this){
            if(it){
                binding.exitShelfAddressEdt.requestFocus()
            }
        }

    }

    override fun onStart() {
        super.onStart()

        binding.searchEdt.requestFocus()
    }

    override fun hideActionBar() = false
}