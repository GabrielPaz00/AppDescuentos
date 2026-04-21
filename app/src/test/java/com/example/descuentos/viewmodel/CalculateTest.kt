package com.example.descuentos.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculateTest {

    private lateinit var viewModel: DescuentosViewModel

    @Before
    fun setup() {
        viewModel = DescuentosViewModel()
    }

    @Test
    fun `calculate updates discount and total when inputs are valid`() {
        viewModel.onPriceChange("1000")
        viewModel.onDiscountChange("10")
        viewModel.calculate()

        val state = viewModel.state.value
        assertEquals(100.0, state.discount, 0.001)
        assertEquals(900.0, state.total, 0.001)
    }

    @Test
    fun `calculate does not update discount and total when discount is invalid`() {
        viewModel.onPriceChange("1000")
        viewModel.onDiscountChange("110")
        viewModel.calculate()

        val state = viewModel.state.value
        assertEquals(0.0, state.discount, 0.001)
        assertEquals(0.0, state.total, 0.001)
    }

    @Test
    fun `calculate does not update discount and total when price is invalid`() {
        viewModel.onPriceChange("abc")
        viewModel.onDiscountChange("10")
        viewModel.calculate()

        val state = viewModel.state.value
        assertEquals(0.0, state.discount, 0.001)
        assertEquals(0.0, state.total, 0.001)
    }
}