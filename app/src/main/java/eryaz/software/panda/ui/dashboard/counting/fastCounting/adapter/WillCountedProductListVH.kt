package eryaz.software.panda.ui.dashboard.counting.fastCounting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.models.dto.CountingComparisonDto
import eryaz.software.panda.databinding.ItemCountingProductListBinding
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener

class WillCountedProductListVH(val binding: ItemCountingProductListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: CountingComparisonDto,
        onItemClick: ((CountingComparisonDto) -> Unit)
    ) {
        binding.dto = dto

        binding.root.setOnSingleClickListener {
            onItemClick(dto)
        }
    }

    companion object {
        fun from(parent: ViewGroup): WillCountedProductListVH {
            val binding = ItemCountingProductListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return WillCountedProductListVH(binding)
        }
    }
}