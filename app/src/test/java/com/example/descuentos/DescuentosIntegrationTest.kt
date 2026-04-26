package com.example.descuentos

import com.example.descuentos.viewmodel.DescuentosViewModel
import com.example.descuentos.viewmodel.formatWithCommas
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNull

import org.junit.Before
import org.junit.Test

class DescuentosIntegrationTest {

    private lateinit var viewModel: DescuentosViewModel

    @Before
    fun setup() {
        // Inicializamos el ViewModel antes de cada prueba
        viewModel = DescuentosViewModel()
    }

    @Test
    fun `TC-INT-01 - Ingreso valido y calculo exitoso`() {
        // 1. Simular interacción del usuario (Ingreso de datos)
        viewModel.onPriceChange("1000.0")
        viewModel.onDiscountChange("15.0")

        // Verificar validación intermedia
        assertTrue(viewModel.state.value.validDiscount == true)

        // 2. Ejecutar acción (Botón Calcular)
        viewModel.calculate()

        // 3. Comprobar oráculo de resultados
        val state = viewModel.state.value
        assertEquals(150.0, state.discount, 0.001) // 1000 * 0.15 = 150
        assertEquals(850.0, state.total, 0.001)    // 1000 - 150 = 850

        // 4. Comprobar formateo de moneda esperado para la vista
        assertEquals("150.00", formatWithCommas(state.discount))
        assertEquals("850.00", formatWithCommas(state.total))
    }

    @Test
    fun `TC-INT-02 - Bloqueo de calculo por limite superior excedido`() {
        // 1. Ingresar precio y un descuento mayor a 100
        viewModel.onPriceChange("500.0")
        viewModel.onDiscountChange("101.0")

        // 2. El estado debe reflejar que el descuento es inválido de inmediato
        assertFalse(viewModel.state.value.validDiscount == true)

        // 3. Ejecutar acción
        viewModel.calculate()

        // 4. Verificar que no se realizaron cálculos matemáticos
        val state = viewModel.state.value
        assertEquals(0.0, state.discount, 0.0)
        assertEquals(0.0, state.total, 0.0)
    }

    @Test
    fun `TC-INT-03 - Restauracion integral del estado`() {
        // 1. Establecer un estado con datos
        viewModel.onPriceChange("250.0")
        viewModel.onDiscountChange("10.0")
        viewModel.calculate()

        // 2. Ejecutar acción de limpieza
        viewModel.clear()

        // 3. Comprobar que todos los valores vuelven al estado inicial (DescuentosState default)
        val state = viewModel.state.value
        assertEquals("", state.priceInput)
        assertEquals("", state.discountInput)
        assertEquals(0.0, state.discount, 0.0)
        assertEquals(0.0, state.total, 0.0)
        assertNull(state.validDiscount)
    }
    @Test
    fun `TC-INT-04 - Analisis de Valor Limite`() {
        viewModel.onPriceChange("200.0")
        viewModel.onDiscountChange("100.0")

        assertTrue(viewModel.state.value.validDiscount == true)

        viewModel.calculate()
        val state = viewModel.state.value
        assertEquals("200.00", formatWithCommas(state.discount))
        assertEquals("0.00", formatWithCommas(state.total))
    }
    @Test
    fun `TC-INT-05 - Analisis de Valor Limite`() {
        viewModel.onPriceChange("350.0")
        viewModel.onDiscountChange("0.0")

        assertTrue(viewModel.state.value.validDiscount == true)

        viewModel.calculate()
        val state = viewModel.state.value
        assertEquals("0.00", formatWithCommas(state.discount))
        assertEquals("350.00", formatWithCommas(state.total))
    }
}

