package eryaz.software.panda.ui.dashboard.counting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.models.dto.StockTackingProcessDto
import eryaz.software.panda.databinding.ItemCountingInfoBinding
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener

class InfoProductEdtVH(val binding: ItemCountingInfoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: StockTackingProcessDto,
        onEditClick: (StockTackingProcessDto) -> Unit,
        onDeleteClick: (StockTackingProcessDto) -> Unit,
        isLastItem: Boolean
    ) {
        binding.dto = dto

        binding.underline.isVisible = !isLastItem

        binding.quantityValueEdt.doAfterTextChanged {
            dto.editedShelfCurrentQuantity = it.toString().toDoubleOrNull() ?: 0.0
        }

        binding.makeQuantityBtn.setOnSingleClickListener {
            onEditClick.invoke(dto)
        }
        binding.deleteBtn.setOnSingleClickListener {
            onDeleteClick.invoke(dto)
        }
    }

    companion object {
        fun from(parent: ViewGroup): InfoProductEdtVH {
            val binding = ItemCountingInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return InfoProductEdtVH(binding)
        }
    }
}