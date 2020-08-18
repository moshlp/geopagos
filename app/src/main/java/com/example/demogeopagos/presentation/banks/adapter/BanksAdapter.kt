package com.example.demogeopagos.presentation.banks.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demogeopagos.R
import com.example.demogeopagos.commons.BaseAdapter
import com.example.demogeopagos.commons.BindingViewHolder
import com.example.demogeopagos.data.model.BankResponse
import com.example.demogeopagos.data.model.PaymentMethodsResponse
import com.example.demogeopagos.databinding.SnippetBanksBinding

class BanksAdapter (rv: RecyclerView, val clickListener: ThumbnailListener) : BaseAdapter<BankResponse, SnippetBanksBinding>(rv, R.layout.snippet_banks) {

    var previousView : View? = null

    class ThumbnailListener(val clickListener: (item: BankResponse) -> Unit) {
        fun onClick(item: BankResponse) = clickListener(item)
    }

    override fun populateBindViewHolder(
        holder: BindingViewHolder<SnippetBanksBinding>?,
        item: BankResponse?,
        position: Int
    ) {
        if (holder != null && holder.binding != null && item != null) {
            holder.binding.bankName.text = item.name
            Glide.with(holder.binding.thumbnail.context)
                .load(item.thumbnail)
                .error(R.drawable.ic_baseline_account_balance_24)
                .placeholder(R.drawable.ic_baseline_account_balance_24)
                .into(holder.binding.thumbnail)
            holder.binding.container.setOnClickListener {
                clickListener.clickListener(item)
                toggleColor(it)
            }
        }
    }

    private fun toggleColor(view: View) {
        if (previousView == null){
            previousView = view
        } else {
            previousView!!.setBackgroundColor(Color.WHITE)
            previousView = view
        }
        if (view.background is ColorDrawable) {
            val color : ColorDrawable = view.background as ColorDrawable
            var newColor = Color.WHITE
            if (color.color == Color.WHITE){
                newColor = view.context.getColor(R.color.colorAccent)
            }
            view.setBackgroundColor(newColor)
        }
    }

    override fun compareItems(itemA: BankResponse?, itemB: BankResponse?): Boolean {
        if (itemA != null && itemB != null) {
            return itemA.id.equals(itemB.id)
        }
        return false
    }

    override fun compareItemsContent(itemA: BankResponse?, itemB: BankResponse?): Boolean {
        if (itemA != null && itemB != null) {
            return itemA.name.equals(itemB.name)
        }
        return false
    }


}