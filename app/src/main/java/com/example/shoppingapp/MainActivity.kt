package com.example.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppingapp.ui.theme.ShoppingAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Green
                ) {
                    ShoppingApp()
                }
                }
            }
        }
    }


@Composable
fun ShoppingApp() {

    var nameitem by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var items by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    var quantities by remember { mutableStateOf(listOf<String>()) }
    var checkedStates by remember { mutableStateOf(mutableStateListOf<Boolean>()) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            value = nameitem,
            onValueChange = { nameitem = it },
            label = { Text("Name of Item") },
            modifier = Modifier.fillMaxWidth()
        )


        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (nameitem.isNotBlank() && quantity.isNotBlank()) {
                    items = items + Pair(nameitem, quantity)
                    quantities = quantities + quantity
                    checkedStates.add(false)  // Initialize with unchecked state
                    nameitem = ""
                    quantity = ""
                }
            }) {
                Text("Add item to List")
            }

        LazyColumn {
            itemsIndexed(items) { index, item ->

                val name = item.first
                val qty = item.second

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Checkbox(
                        checked = checkedStates[index],
                        onCheckedChange = { isChecked ->
                            checkedStates[index] = isChecked
                        }
                    )
                    Text(text = "$name - $qty")
                }

            }
        }
       }
    }

