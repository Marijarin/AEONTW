package com.assignment.loginandpay.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.assignment.loginandpay.R
import com.assignment.loginandpay.databinding.FragmentPaymentsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaymentsFragment : Fragment(R.layout.fragment_payments) {
    private val viewModel: PaymentsViewModel by viewModels()
    private var _binding: FragmentPaymentsBinding? = null
    private val paymentsAdapter: PaymentsAdapter by lazy { PaymentsAdapter() }
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPaymentsBinding.bind(view)
        binding.list.adapter = paymentsAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.payments.collectLatest {
                    if (it.isNotEmpty()) {
                        paymentsAdapter.submitList(it)
                    }
                }
            }
        }
        binding.topMaterialTB.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                    builder
                        .setMessage(context?.getString(R.string.want_to_logout))
                        .setTitle(context?.getString(R.string.approve))
                        .setPositiveButton(context?.getString(R.string.yes)) { dialog, which ->
                            viewModel.logout()
                            dialog.dismiss()
                            findNavController().navigate(R.id.action_paymentsFragment_to_loginFragment)
                        }
                        .setNegativeButton(context?.getString(R.string.no)) { dialog, which ->
                            dialog.dismiss()
                        }

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}