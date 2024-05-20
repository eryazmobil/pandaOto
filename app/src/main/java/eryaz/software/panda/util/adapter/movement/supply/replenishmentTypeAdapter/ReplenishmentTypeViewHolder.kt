package eryaz.software.panda.util.adapter.movement.supply.replenishmentTypeAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.enums.ReplenishStatusEnum
import eryaz.software.panda.databinding.ReplenishmentTypeItemBinding
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener

class ReplenishmentTypeViewHolder(val binding: ReplenishmentTypeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        replenishmentType: ReplenishStatusEnum,
        onItemClick: ((ReplenishStatusEnum) -> Unit)

    ) {
        binding.replenishmentType = replenishmentType

        binding.root.setOnSingleClickListener {
            onItemClick(replenishmentType)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ReplenishmentTypeViewHolder {
            val binding = ReplenishmentTypeItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return ReplenishmentTypeViewHolder(binding)
        }
    }
}