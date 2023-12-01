package com.assignment.loginandpay.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.loginandpay.databinding.ItemPaymentBinding
import com.assignment.loginandpay.domain.model.Payment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PaymentsAdapter :
    ListAdapter<Payment, PaymentsAdapter.PaymentViewHolder>(PaymentViewHolder.PaymentDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = ItemPaymentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PaymentViewHolder(
        private val binding: ItemPaymentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(payment: Payment) {
            binding.apply {
                heading.text = payment.title
                amount.text = payment.amount ?: "--"
                created.text = payment.created?.let { convertLongToTime(it) } ?: "--"
            }
        }

        class PaymentDiffCallback : DiffUtil.ItemCallback<Payment>() {
            override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean {
                return oldItem == newItem
            }
        }

        private fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("ru"))
            return format.format(date)
        }
    }

}