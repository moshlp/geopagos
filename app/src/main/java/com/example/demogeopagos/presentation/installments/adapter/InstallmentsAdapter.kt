package com.example.demogeopagos.presentation.installments.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.demogeopagos.R
import com.example.demogeopagos.commons.BaseAdapter
import com.example.demogeopagos.commons.BindingViewHolder
import com.example.demogeopagos.data.model.PayerCost
import com.example.demogeopagos.databinding.SnippetInstallmentsBinding
import java.util.stream.Collectors

class InstallmentsAdapter(
    rv: RecyclerView,
    private val clickListener: ThumbnailListener
) : BaseAdapter<PayerCost, SnippetInstallmentsBinding>(
    rv,
    R.layout.snippet_installments
) {

    class ThumbnailListener(val clickListener: (item: PayerCost) -> Unit) {
        fun onClick(item: PayerCost) = clickListener(item)
    }

    override fun populateBindViewHolder(
        holder: BindingViewHolder<SnippetInstallmentsBinding>?,
        item: PayerCost?,
        position: Int
    ) {
        if (holder != null && holder.binding != null && item != null) {
            holder.binding.installmentDetail.text = item.recommendedMessage
            val labels = item.labels?.stream()?.filter { it ->
                it.contains("CFT")
            }?.collect(Collectors.toList())
            if (!labels.isNullOrEmpty()) {
                holder.binding.cft.text = labels[0]
            }
            holder.binding.container.setOnClickListener {
                clickListener.clickListener(item)
            }
        }
    }

    override fun compareItems(itemA: PayerCost?, itemB: PayerCost?): Boolean {
        if (itemA != null && itemB != null && itemA.installments != null && itemB.installments != null) {
            return itemA.installments == itemB.installments
        }
        return false
    }

    override fun compareItemsContent(
        itemA: PayerCost?,
        itemB: PayerCost?
    ): Boolean {
        if (itemA != null && itemB != null && itemA.installmentAmount != null && itemB.installmentAmount != null) {
            return itemA.installmentAmount == itemB.installmentAmount
        }
        return false
    }
}