package com.example.descuentos

import com.example.descuentos.viewmodel.DescuentosViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OnDiscountChangeTest {

    private lateinit var viewModel: DescuentosViewModel

    @Before
    fun setup() {
        viewModel = DescuentosViewModel()
    }

    @Test
    fun `onDiscountChange with valid value updates state correctly`() {
        viewModel.onDiscountChange("20")
        Assert.assertEquals("20", viewModel.state.value.discountInput)
        Assert.assertTrue(viewModel.state.value.validDiscount == true)
    }

    @Test
    fun `onDiscountChange with invalid value (out of range) updates state correctly`() {
        viewModel.onDiscountChange("105")
        Assert.assertEquals("105", viewModel.state.value.discountInput)
        Assert.assertFalse(viewModel.state.value.validDiscount == true)
    }

    @Test
    fun `onDiscountChange with invalid value (not a number) updates state correctly`() {
        viewModel.onDiscountChange("abc")
        Assert.assertEquals("abc", viewModel.state.value.discountInput)
        Assert.assertFalse(viewModel.state.value.validDiscount == true)
    }

    @Test
    fun `onDiscountChange with empty value sets validDiscount to null`() {
        viewModel.onDiscountChange("")
        Assert.assertEquals("", viewModel.state.value.discountInput)
        Assert.assertNull(viewModel.state.value.validDiscount)
    }
}