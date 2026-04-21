package com.example.descuentos

import com.example.descuentos.viewmodel.DescuentosViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OnPriceChangeTest {

    private lateinit var viewModel: DescuentosViewModel

    @Before
    fun setup() {
        viewModel = DescuentosViewModel()
    }

    @Test
    fun `onPriceChange updates priceInput state`() {
        viewModel.onPriceChange("1500.50")
        Assert.assertEquals("1500.50", viewModel.state.value.priceInput)
    }
}