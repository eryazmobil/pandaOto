package eryaz.software.panda.util.adapter.movement.packageList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.models.dto.VehiclePackageDto
import eryaz.software.panda.databinding.ItemOnVehiclePackageBinding
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener

class VehiclePackageViewHolder(val binding: ItemOnVehiclePackageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: VehiclePackageDto,
        onItemClick: ((VehiclePackageDto) -> Unit)
    ) {
        binding.dto = dto

        binding.root.setOnSingleClickListener {
            onItemClick(dto)
        }
    }

    companion object {
        fun from(parent: ViewGroup): VehiclePackageViewHolder {
            val binding = ItemOnVehiclePackageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return VehiclePackageViewHolder(binding)
        }
    }
}