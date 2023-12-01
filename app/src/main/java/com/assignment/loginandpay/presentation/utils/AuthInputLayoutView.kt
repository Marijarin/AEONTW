package com.assignment.loginandpay.presentation.utils

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.withStyledAttributes
import androidx.core.widget.doAfterTextChanged
import com.assignment.loginandpay.R
import com.assignment.loginandpay.databinding.ViewAuthInputLayoutBinding
import com.google.android.material.textfield.TextInputLayout

class AuthInputLayoutView(
    context: Context,
    attrs: AttributeSet,
) : TextInputLayout(context, attrs) {
    private var binding: ViewAuthInputLayoutBinding
    var type: Int = 0
        set(value) {
            field = value
            updateType()
        }
    var hint: String = ""
        set(value) {
            field = value
            updateHint()
        }
    var textField: String = ""

    private val hintError = resources.getString(R.string.error_hint)

    init {
        binding = ViewAuthInputLayoutBinding.inflate(
            LayoutInflater.from(context),
            this@AuthInputLayoutView,
            false
        )
        context.withStyledAttributes(attrs, R.styleable.InputLayoutView) {
            type = getInt(R.styleable.InputLayoutView_type, 0)
            hint = getString(R.styleable.InputLayoutView_hint) ?: ""
            addView(binding.root)
            binding.inputEditText.doAfterTextChanged { text ->
                textField = text.toString()
            }
        }
    }

    private fun updateType() {
        binding.apply {
            when (type) {
                TEXT -> {
                    inputEditText.inputType = InputType.TYPE_CLASS_TEXT
                    editTextIsEmpty()
                }

                PASSWORD -> {
                    inputEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                    textInputLayout.endIconMode = END_ICON_PASSWORD_TOGGLE
                    textInputLayout.isErrorEnabled = true
                    textInputLayout.endIconDrawable?.alpha = 105
                    editTextIsEmpty()
                }
            }
        }
    }

    private fun updateHint() {
        binding.apply {
            if (hint.isNotEmpty()) textInputLayout.hint = hint
        }
    }
    private fun editTextIsEmpty() = with(binding) {
        inputEditText.setOnFocusChangeListener { _, hasFocus ->
            error = if (!hasFocus && inputEditText.text.isNullOrEmpty()) {
                hintError
            } else {
                null
            }
        }
    }

    companion object {
        private const val TEXT = 1
        private const val PASSWORD = 2
    }
}