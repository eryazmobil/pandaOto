package eryaz.software.panda.ui.dashboard.query.queryStorage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import eryaz.software.panda.data.models.dto.ProductDto
import eryaz.software.panda.databinding.FragmentQueryStorageBinding
import eryaz.software.panda.ui.base.BaseFragment
import eryaz.software.panda.ui.dashboard.query.adapter.ShelfQuantityAdapter
import eryaz.software.panda.ui.dashboard.query.adapter.StorageProductQuantityAdapter
import eryaz.software.panda.ui.dashboard.recording.dialog.ProductListDialogFragment
import eryaz.software.panda.util.extensions.hideSoftKeyboard
import eryaz.software.panda.util.extensions.parcelable
import org.koin.androidx.viewmodel.ext.android.viewModel

class QueryStorageFragment : BaseFragment() {
    override val viewModel by viewModel<QueryStorageFragmentVM>()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentQueryStorageBinding.inflate(layoutInflater)
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

        binding.searchTil.setEndIconOnClickListener {
            findNavController().navigate(
               QueryStorageFragmentDirections.actionQueryProductFragmentToProductListDialogFragment()
            )
        }
    }

    override fun subscribeToObservables() {
        viewModel.storageList.asLiveData()
            .observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

        viewModel.shelfList.asLiveData()
            .observe(viewLifecycleOwner) {
                shelfAdapter.submitList(it)
            }

        setFragmentResultListener(ProductListDialogFragment.REQUEST_KEY) { _, bundle ->
            val dto = bundle.parcelable<ProductDto>(ProductListDialogFragment.ARG_PRODUCT_DTO)
            dto?.let {
                viewModel.setExitStorage(it)
            }
        }
    }

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        StorageProductQuantityAdapter().also {
            binding.recyclerViewForStorage.adapter = it
        }
    }

    private val shelfAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ShelfQuantityAdapter().also {
            binding.recyclerViewForShelf.adapter = it
        }
    }

    override fun onStart() {
        super.onStart()

        binding.searchEdt.requestFocus()
    }

    override fun hideActionBar() = false
}