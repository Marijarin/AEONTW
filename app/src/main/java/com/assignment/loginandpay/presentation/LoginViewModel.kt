package com.assignment.loginandpay.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.loginandpay.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _authenticated = MutableStateFlow(false)
    val authenticated = _authenticated.asStateFlow()

    fun login (login: String, pw:String) = viewModelScope.launch {
            try {
                _authenticated.value = repository.login(login, pw)
            } catch (e: Exception) {
                _authenticated.value = false
               Log.w("LoginVM", "log in failed")
            }
        }
    }
