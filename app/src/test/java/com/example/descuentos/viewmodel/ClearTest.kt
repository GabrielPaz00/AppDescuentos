package com.example.descuentos.viewmodel


import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ClearTest {

    private lateinit var viewModel: DescuentosViewModel

    @Before
    fun setup() {
        viewModel = DescuentosViewModel()
    }

    @Test
    fun `clear resets state to default values`() {
        viewModel.onPriceChange("500")
        viewModel.onDiscountChange("50")
        viewModel.calculate()

        viewModel.clear()

        val state = viewModel.state.value
        assertEquals("", state.priceInput)
        assertEquals("", state.discountInput)
        assertEquals(0.0, state.discount, 0.001)
        assertEquals(0.0, state.total, 0.001)
        assertNull(state.validDiscount)
    }
}