package com.assignment.loginandpay.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.loginandpay.domain.model.Payment
import com.assignment.loginandpay.domain.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val repository: PaymentRepository
) : ViewModel() {
    init {
        loadPayments()
    }
    private val _payments = MutableStateFlow<List<Payment>>(emptyList())
    val payments = _payments.asStateFlow()
    private fun loadPayments() = viewModelScope.launch {
        _payments.value = repository.getAll()
    }

    fun logout() {
        repository.deleteToken()
    }
}
