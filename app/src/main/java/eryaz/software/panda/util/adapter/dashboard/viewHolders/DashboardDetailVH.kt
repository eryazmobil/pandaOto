package eryaz.software.panda.util.adapter.dashboard.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.models.dto.DashboardDetailItemDto
import eryaz.software.panda.databinding.ItemDashboardDetailListBinding
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener
import eryaz.software.panda.util.extensions.onChanged

class DashboardDetailVH (val binding: ItemDashboardDetailListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dto: DashboardDetailItemDto, onItemClick :(
        (DashboardDetailItemDto)->Unit)) {
        binding.dto = dto
        dto.hasPermission.onChanged {
            binding.root.alpha = if (it) 1f else 0.5f
            binding.root.isEnabled = it
        }
        binding.root.setOnSingleClickListener {
            onItemClick(dto)
        }
    }
    companion object {
        fun from(parent: ViewGroup): DashboardDetailVH {
            val binding = ItemDashboardDetailListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
            return DashboardDetailVH(binding)
        }
    }

}