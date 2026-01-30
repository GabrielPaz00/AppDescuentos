package com.example.descuentos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.descuentos.ui.theme.MyApplicationTheme
import com.example.descuentos.view.DescuentosView
import com.example.descuentos.viewmodel.DescuentosViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    content = {
                        DescuentosView(viewModel = DescuentosViewModel())
                    })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    DescuentosView(viewModel = DescuentosViewModel())
}