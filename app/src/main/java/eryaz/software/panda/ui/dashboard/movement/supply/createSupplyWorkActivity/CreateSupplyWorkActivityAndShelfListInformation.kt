package eryaz.software.panda.ui.dashboard.movement.supply.createSupplyWorkActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import eryaz.software.panda.R
import eryaz.software.panda.data.models.dto.ErrorDialogDto
import eryaz.software.panda.databinding.FragmentCreateSupplyWorkActivityAndShelfListInformationBinding
import eryaz.software.panda.ui.base.BaseFragment
import eryaz.software.panda.util.adapter.movement.supply.suplyDapperShelfAdapter.SupplyDapperShelfAdapter
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener
import eryaz.software.panda.util.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateSupplyWorkActivityAndShelfListInformation : BaseFragment() {

    override val viewModel by viewModel<CreateSupplyWorkActivityVM>()
    private val args: CreateSupplyWorkActivityAndShelfListInformationArgs by navArgs()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentCreateSupplyWorkActivityAndShelfListInformationBinding.inflate(layoutInflater)
    }

    private val productShelfIdList = ArrayList<Int>()

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

        viewModel.getReportProductShelfListForWorkActivityForPda(args.supplyStatus)

        viewModel.supplyProductShelfDapperDtoList.observe(this) {
            adapter.submitList(it)
        }

        viewModel.finishThePage.observe(this){
            if (it) {
                findNavController().navigateUp()
            }
        }

    }

    override fun setClicks() {

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        adapter.onItemChecked = {

            if (it.isChecked) {
                productShelfIdList.add(it.productId)
            } else {
                productShelfIdList.remove(it.productId)
            }

        }

        binding.createWorkActivityForSupply.setOnSingleClickListener {

            if (productShelfIdList.isEmpty()) {
                errorDialog.show(
                    requireContext(),
                    ErrorDialogDto(
                        titleRes = R.string.error, messageRes = R.string.must_select_product
                    )
                )
            }
            else{
                viewModel.createProductShelfWorkActivityForPda(
                    args.supplyStatus,
                    productShelfIdList
                )
            }
        }

    }

    private val adapter by lazy {
        SupplyDapperShelfAdapter(requireContext()).also {
            binding.supplyShelfDapperRecycler.adapter = it
        }
    }


}