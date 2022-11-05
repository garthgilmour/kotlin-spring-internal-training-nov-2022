package demos.compose.desktop.layouts

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants.defaultButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


@InternalLayoutApi
@Composable
fun ButtonRow(arrangement: Arrangement.Horizontal, vararg names: String) {
    Row(horizontalArrangement = arrangement, modifier = Modifier.fillMaxWidth()) {
        names.forEach { name ->
            Button(
                onClick = { println("Pushed $name") },
                modifier = Modifier.padding(all = 5.dp),
                colors = defaultButtonColors(backgroundColor = Color.LightGray)
            ) {
                Text(name)
            }
        }
    }
}

@InternalLayoutApi
fun main() = Window(title = "Layouts Demo", size = IntSize(400, 200)) {
    MaterialTheme {
        Column {
            ButtonRow(Arrangement.Start, "Homer", "Marge")
            ButtonRow(Arrangement.Center, "Bart", "Lisa", "Maggie")
            ButtonRow(Arrangement.End, "Snowball", "Santa's Little Helper")
        }
    }
}
