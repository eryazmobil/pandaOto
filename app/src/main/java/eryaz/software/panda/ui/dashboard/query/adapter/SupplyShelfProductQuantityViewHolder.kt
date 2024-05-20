package eryaz.software.panda.ui.dashboard.query.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.models.dto.ProductShelfSupplyDto
import eryaz.software.panda.databinding.ItemStorageQuantityTextBinding

class SupplyShelfProductQuantityViewHolder(val binding: ItemStorageQuantityTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: ProductShelfSupplyDto,
        isLastItem: Boolean
    ) {
        binding.keyTxt.text = dto.shelf?.shelfAddress
        binding.valueTxt.text = dto.quantity

        binding.underline.isVisible = !isLastItem
    }

    companion object {
        fun from(parent: ViewGroup): SupplyShelfProductQuantityViewHolder {
            val binding = ItemStorageQuantityTextBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return SupplyShelfProductQuantityViewHolder(binding)
        }
    }
}