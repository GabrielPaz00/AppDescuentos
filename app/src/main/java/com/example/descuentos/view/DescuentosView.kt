package com.example.descuentos.view


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.descuentos.viewmodel.DescuentosViewModel
import com.example.descuentos.viewmodel.formatWithCommas


val mainColor = Color(0xFF673AB7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescuentosView(viewModel: DescuentosViewModel) {

    val stateView by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "App Descuentos", color = Color.White
                    )
                }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = mainColor
                )
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoCard(
                    title = "Total",
                    amount = "$${formatWithCommas(stateView.total)}",
                    modifier = Modifier.weight(1f)
                )
                InfoCard(
                    title = "Descuento",
                    amount = "$${formatWithCommas(stateView.discount)}",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp)) // Espacio vertical

            OutlinedTextField(
                value = stateView.priceInput,
                onValueChange = { viewModel.onPriceChange(it) },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = stateView.discountInput,
                onValueChange = { viewModel.onDiscountChange(it) },
                label = { Text("Descuento %") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth(),
                isError = stateView.validDiscount == false,
                trailingIcon = {
                    if (stateView.validDiscount == true) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Descuento válido",
                            tint = mainColor
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            GenerateDiscountButton { viewModel.calculate() }
            Spacer(modifier = Modifier.height(8.dp))
            ClearButton { viewModel.clear() }

        }
    }
}

@Composable
fun InfoCard(
    title: String, amount: String, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(100.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = title,
                style = MaterialTheme.typography.bodyLarge)
            Text(
                text = amount,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 1,
                softWrap = false,
                modifier = Modifier.horizontalScroll(rememberScrollState())
            )
        }
    }
}

@Composable
fun GenerateDiscountButton(calculate: () -> Unit) {
    OutlinedButton(
        onClick = calculate,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text("Generar Descuento", color = mainColor)
    }
}

@Composable
fun ClearButton(clear: () -> Unit) {
    OutlinedButton(
        onClick = clear,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text("Limpiar", color = Color.Red)
    }
}
