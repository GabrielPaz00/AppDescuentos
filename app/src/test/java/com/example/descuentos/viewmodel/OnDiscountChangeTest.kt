package com.example.descuentos.viewmodel


import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
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
        assertEquals("20", viewModel.state.value.discountInput)
        assertTrue(viewModel.state.value.validDiscount == true)
    }

    @Test
    fun `onDiscountChange with invalid value (out of range) updates state correctly`() {
        viewModel.onDiscountChange("105")
        assertEquals("105", viewModel.state.value.discountInput)
        assertFalse(viewModel.state.value.validDiscount == true)
    }

    @Test
    fun `onDiscountChange with invalid value (not a number) updates state correctly`() {
        viewModel.onDiscountChange("abc")
        assertEquals("abc", viewModel.state.value.discountInput)
        assertFalse(viewModel.state.value.validDiscount == true)
    }

    @Test
    fun `onDiscountChange with empty value sets validDiscount to null`() {
        viewModel.onDiscountChange("")
        assertEquals("", viewModel.state.value.discountInput)
        assertNull(viewModel.state.value.validDiscount)
    }
}