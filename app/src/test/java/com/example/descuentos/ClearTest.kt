package com.example.descuentos

import com.example.descuentos.viewmodel.DescuentosViewModel
import org.junit.Assert
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
        Assert.assertEquals("", state.priceInput)
        Assert.assertEquals("", state.discountInput)
        Assert.assertEquals(0.0, state.discount, 0.001)
        Assert.assertEquals(0.0, state.total, 0.001)
        Assert.assertNull(state.validDiscount)
    }
}