package com.example.descuentos.viewmodel

import org.junit.Assert.assertEquals
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
        assertEquals("1500.50", viewModel.state.value.priceInput)
    }
}