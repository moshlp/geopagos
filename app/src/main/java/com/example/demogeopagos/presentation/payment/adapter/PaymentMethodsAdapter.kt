package com.example.demogeopagos.presentation.payment.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.example.demogeopagos.R
import com.example.demogeopagos.commons.BaseAdapter
import com.example.demogeopagos.commons.BindingViewHolder
import com.example.demogeopagos.data.model.PaymentMethodsResponse
import com.example.demogeopagos.databinding.SnippetPaymentMethodBinding
import com.example.demogeopagos.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PaymentMethodsAdapter(rv: RecyclerView, val clickListener: ThumbnailListener) : BaseAdapter<PaymentMethodsResponse, SnippetPaymentMethodBinding>(rv, R.layout.snippet_payment_method) {

    var previousView : View? = null

    override fun populateBindViewHolder(
        holder: BindingViewHolder<SnippetPaymentMethodBinding>?,
        item: PaymentMethodsResponse?,
        position: Int
    ) {
        if (holder != null && holder.binding != null && item != null) {
            holder.binding.cardName.text = item.name
            Glide.with(holder.binding.thumbnail.context)
                .load(item.thumbnail)
                .placeholder(R.drawable.ic_baseline_credit_card_24)
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

    override fun compareItems(
        itemA: PaymentMethodsResponse?,
        itemB: PaymentMethodsResponse?
    ): Boolean {
        if (itemA != null && itemB != null) {
            return itemA.id.equals(itemB.id)
        }
        return false
    }

    override fun compareItemsContent(
        itemA: PaymentMethodsResponse?,
        itemB: PaymentMethodsResponse?
    ): Boolean {
        if (itemA != null && itemB != null) {
            return itemA.name.equals(itemB.name)
        }
        return false
    }

    class ThumbnailListener(val clickListener: (item: PaymentMethodsResponse) -> Unit) {
        fun onClick(item: PaymentMethodsResponse) = clickListener(item)
    }
}