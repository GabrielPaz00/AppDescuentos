package com.example.descuentos.viewmodel


import androidx.lifecycle.ViewModel
import com.example.descuentos.model.DescuentosState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.util.Locale


class DescuentosViewModel : ViewModel() {

    val state = MutableStateFlow(DescuentosState())


    fun onPriceChange(price: String) {
        state.update {
            it.copy(
                priceInput = price
            )
        }
    }

    fun onDiscountChange(discount: String) {
        val discountValue = discount.toDoubleOrNull()
        val isValid = discountValue != null && discountValue in 0.0..100.0

        state.update {
            it.copy(
                discountInput = discount,
                validDiscount = if (discount.isEmpty()) null else isValid
            )
        }
    }

    fun calculate() {
        val price = state.value.priceInput.toDoubleOrNull() ?: return
        val discountPercent = state.value.discountInput.toDoubleOrNull() ?: return


        val discountAmount = price * (discountPercent / 100)
        val finalPrice = price - discountAmount


        if (state.value.validDiscount == true) {
            state.update {
                it.copy(
                    discount = discountAmount,
                    total = finalPrice
                )
            }
        }
    }
    fun clear() {
        state.update {
            it.copy(
                priceInput = "",
                discountInput = "",
                discount = 0.0,
                total = 0.0,
                validDiscount = null
            )
        }
    }
}

fun formatWithCommas(value: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale.US).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return formatter.format(value)
}


