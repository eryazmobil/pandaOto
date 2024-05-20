package eryaz.software.panda.util.adapter.outbound

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.models.dto.ControlPointScreenDto
import eryaz.software.panda.databinding.ItemControlPointListBinding
import eryaz.software.panda.util.bindingAdapter.setOnSingleClickListener

class ControlPointListVH(val binding: ItemControlPointListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: ControlPointScreenDto,
        onItemClick: ((ControlPointScreenDto) -> Unit)
    ) {
        binding.dto = dto
        binding.executePendingBindings()
        setStatus(dto)

        binding.root.setOnSingleClickListener {
            onItemClick(dto)
        }
    }

    private fun setStatus(dto: ControlPointScreenDto) {
        val (textColor, statusColor) = when (dto.status) {
            "blue" -> "#ffffffff" to "#0500FF"
            "yellow" -> "#000000" to "#FFCB08"
            "red" -> "#ffffffff" to "#852523"
            "green" -> "#000000" to "#6CC654"
            else -> "#ffffffff" to "#A500FF"
        }

        binding.clientNames.apply {
            setTextColor(Color.parseColor(textColor))
        }
        binding.definition.apply {
            setTextColor(Color.parseColor(textColor))
        }
        binding.fixName.apply {
            setTextColor(Color.parseColor(textColor))
        }
        binding.itemRoot.apply {
            setBackgroundColor(Color.parseColor(statusColor))
        }
    }

    companion object {
        fun from(parent: ViewGroup): ControlPointListVH {
            val binding = ItemControlPointListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
            return ControlPointListVH(binding)
        }
    }
}