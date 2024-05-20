package eryaz.software.panda.util.adapter.movement.route

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.models.dto.RouteDto
import eryaz.software.panda.databinding.ItemRouteListBinding
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener

class RouteViewHolder(val binding: ItemRouteListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: RouteDto,
        onItemClick: ((RouteDto) -> Unit)
    ) {
        binding.dto = dto

        binding.root.setOnSingleClickListener {
            onItemClick(dto)
        }
    }

    companion object {
        fun from(parent: ViewGroup): RouteViewHolder {
            val binding = ItemRouteListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return RouteViewHolder(binding)
        }
    }
}