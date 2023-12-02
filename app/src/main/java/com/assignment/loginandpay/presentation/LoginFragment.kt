package com.assignment.loginandpay.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.assignment.loginandpay.R
import com.assignment.loginandpay.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var login = ""
    private var pw = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        setLoginListener()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.authenticated.collectLatest {
                    if (it) {
                        findNavController().navigate(R.id.action_loginFragment_to_paymentsFragment)
                    } else {
                        binding.loginInput.textField = ""
                        binding.password.textField = ""
                    }
                }
            }
        }
    }

    private fun setLoginListener() {
        with(binding) {
            authButton.setOnClickListener {
                login = binding.loginInput.textField
                pw = binding.password.textField
                if (login.isNotEmpty() && pw.isNotEmpty()) {
                    viewModel.login(login, pw)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
