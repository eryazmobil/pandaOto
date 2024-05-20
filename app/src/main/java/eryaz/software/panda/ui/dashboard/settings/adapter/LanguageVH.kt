package eryaz.software.panda.ui.dashboard.settings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eryaz.software.panda.data.models.remote.response.LanguageModel
import eryaz.software.panda.databinding.ItemLanguageBinding
import eryaz.software.panda.util.extensions.dpToPx
import eryaz.software.panda.util.extensions.getDrawableRes
import eryaz.software.panda.util.extensions.onChanged

class LanguageVH(val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        model: LanguageModel,
        onItemClick: ((LanguageModel) -> Unit)? = null,
    ) {
        binding.itemTxt.text = model.lang.fullName
        binding.iconImg.setImageResource(model.lang.getDrawableRes())

        model.isSelected.onChanged {
            binding.iconImg.strokeWidth = if (it) 2.dpToPx().toFloat() else 0f
        }

        binding.root.setOnClickListener {
            onItemClick?.invoke(model)
        }
    }

    companion object {
        fun from(parent: ViewGroup) = LanguageVH(
            ItemLanguageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
