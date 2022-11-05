package demos.compose.desktop.hello

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants.defaultButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun main() = Window(title = "Hello JetPack Compose Desktop", size = IntSize(500, 150)) {
    val input = remember { mutableStateOf("") }
    val yourName = remember { mutableStateOf("Unknown User") }

    MaterialTheme {
        Column {
            Row {
                Text(
                    text = "Hello ${yourName.value}!",
                    modifier = Modifier.padding(all = 5.dp),
                    fontSize = 28.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = input.value,
                    modifier = Modifier.padding(all = 5.dp),
                    onValueChange = { input.value = it }
                )
                Button(
                    onClick = { yourName.value = input.value },
                    modifier = Modifier.padding(start = 10.dp),
                    colors = defaultButtonColors(backgroundColor = Color.LightGray)
                ) {
                    Text("Update Msg")
                }
            }
        }
    }
}
