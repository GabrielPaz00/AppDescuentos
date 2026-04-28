package com.example.descuentos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.descuentos.ui.theme.MyApplicationTheme
import com.example.descuentos.view.DescuentosView
import com.example.descuentos.viewmodel.DescuentosViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class DescuentosTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `TC-01 - Ingreso valido y calculo exitoso`() {
        composeTestRule.setContent {
            MyApplicationTheme {
                DescuentosView(viewModel = DescuentosViewModel())
            }
        }

        composeTestRule.onNodeWithText("Precio").performTextInput("1000")
        composeTestRule.onNodeWithText("Descuento %").performTextInput("15")
        composeTestRule.onNodeWithText("Generar Descuento").performClick()

        composeTestRule.onNodeWithText("$150.00").assertIsDisplayed()
        composeTestRule.onNodeWithText("$850.00").assertIsDisplayed()
    }

    @Test
    fun `TC-02 - Bloqueo de calculo por limite superior excedido`() {
        composeTestRule.setContent {
            MyApplicationTheme {
                DescuentosView(viewModel = DescuentosViewModel())
            }
        }

        composeTestRule.onNodeWithText("Precio").performTextInput("500")
        composeTestRule.onNodeWithText("Descuento %").performTextInput("101")
        composeTestRule.onNodeWithText("Generar Descuento").performClick()

        composeTestRule.onAllNodesWithText("$0.00").apply {
            fetchSemanticsNodes().forEachIndexed { index, _ ->
                get(index).assertIsDisplayed()
            }
        }
    }

    @Test
    fun `TC-03 - Restauracion integral del estado`() {
        composeTestRule.setContent {
            MyApplicationTheme {
                DescuentosView(viewModel = DescuentosViewModel())
            }
        }

        composeTestRule.onNodeWithText("Precio").performTextInput("250")
        composeTestRule.onNodeWithText("Descuento %").performTextInput("10")
        composeTestRule.onNodeWithText("Generar Descuento").performClick()

        composeTestRule.onNodeWithText("Limpiar").performClick()

        composeTestRule.onAllNodesWithText("$0.00").apply {
            assert(fetchSemanticsNodes().size >= 2)
        }
    }

    @Test
    fun `TC-04 - Analisis de Valor Limite`() {
        composeTestRule.setContent {
            MyApplicationTheme {
                DescuentosView(viewModel = DescuentosViewModel())
            }
        }

        composeTestRule.onNodeWithText("Precio").performTextInput("200")
        composeTestRule.onNodeWithText("Descuento %").performTextInput("100")
        composeTestRule.onNodeWithText("Generar Descuento").performClick()

        composeTestRule.onNodeWithText("$200.00").assertIsDisplayed()
        composeTestRule.onNodeWithText("$0.00").assertIsDisplayed()
    }

    @Test
    fun `TC-05 - Analisis de Valor Limite`() {
        composeTestRule.setContent {
            MyApplicationTheme {
                DescuentosView(viewModel = DescuentosViewModel())
            }
        }

        composeTestRule.onNodeWithText("Precio").performTextInput("350")
        composeTestRule.onNodeWithText("Descuento %").performTextInput("0")
        composeTestRule.onNodeWithText("Generar Descuento").performClick()

        composeTestRule.onNodeWithText("$0.00").assertIsDisplayed()
        composeTestRule.onNodeWithText("$350.00").assertIsDisplayed()
    }
}
