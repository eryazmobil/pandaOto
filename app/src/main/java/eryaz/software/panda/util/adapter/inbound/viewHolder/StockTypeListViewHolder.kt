package eryaz.software.panda.util.adapter.inbound.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.models.dto.StockTypeDto
import eryaz.software.panda.databinding.InMovementStockTypeListDialogBinding
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener

class StockTypeListViewHolder(val binding: InMovementStockTypeListDialogBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: StockTypeDto,
        onItemClick: ((StockTypeDto) -> Unit)
    ) {
        binding.itemBtn.setText(dto.titleRes)

        binding.root.setOnSingleClickListener {
            onItemClick(dto)
        }
    }

    companion object {
        fun from(parent: ViewGroup): StockTypeListViewHolder {
            val binding = InMovementStockTypeListDialogBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return StockTypeListViewHolder(binding)
        }
    }
}