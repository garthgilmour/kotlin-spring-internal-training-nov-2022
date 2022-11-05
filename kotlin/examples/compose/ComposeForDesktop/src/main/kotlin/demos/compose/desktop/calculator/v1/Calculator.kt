package demos.compose.desktop.calculator.v1

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants.defaultButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.IllegalStateException

import demos.compose.desktop.calculator.v1.Operation.*

@Composable
fun DisplayText(text: String) =
    Text(
        text = text,
        style = TextStyle(color = Black, fontSize = 28.sp),
        modifier = Modifier.padding(all = 10.dp)
    )

@Composable
fun NumberButton(onClick: () -> Unit, number: Int) =
    Button(
        onClick = onClick,
        modifier = Modifier.padding(all = 5.dp),
        colors = defaultButtonColors(backgroundColor = LightGray)
    ) {
        Text(
            number.toString(),
            style = TextStyle(color = Black, fontSize = 18.sp)
        )
    }

@Composable
fun OperationButton(onClick: () -> Unit, label: String) =
    Button(
        onClick = onClick,
        modifier = Modifier.padding(all = 2.dp),
        colors = defaultButtonColors(backgroundColor = LightGray)
    ) {
        Text(label, style = TextStyle(color = Black, fontSize = 14.sp))
    }

fun main() = Window(title = "Calculator via Compose for Desktop V1", size = IntSize(375, 325)) {
    val savedTotal = remember { mutableStateOf(0) }
    val displayedTotal = remember { mutableStateOf(0) }
    val operationOngoing = remember { mutableStateOf(Operation.None) }
    val operationJustChanged = remember { mutableStateOf(false) }

    val numberSelected = { num: Int ->
        when {
            operationJustChanged.value -> {
                operationJustChanged.value = false
                savedTotal.value = displayedTotal.value
                displayedTotal.value = num
            }
            displayedTotal.value == 0 -> {
                displayedTotal.value = num
            }
            else -> {
                displayedTotal.value = (displayedTotal.value * 10) + num
            }
        }
    }

    val clearSelected = {
        displayedTotal.value = 0
        savedTotal.value = 0
        operationOngoing.value = Operation.None
        operationJustChanged.value = false
    }

    val doOperation = {
        val saved = savedTotal.value
        val displayed = displayedTotal.value

        when (operationOngoing.value) {
            Add -> displayedTotal.value = saved + displayed
            Subtract -> displayedTotal.value = saved - displayed
            Multiply -> displayedTotal.value = saved * displayed
            Divide -> displayedTotal.value = saved / displayed
            else -> throw IllegalStateException("No operation to execute!")
        }
    }

    val operationSelected = { op: Operation ->
        operationJustChanged.value = true
        if (operationOngoing.value != None) {
            doOperation()
            savedTotal.value = displayedTotal.value
        }
        operationOngoing.value = op
    }

    val equalsSelected = {
        doOperation()
        operationOngoing.value = Operation.None
    }

    MaterialTheme {
        Column {
            Row {
                DisplayText(text = "${displayedTotal.value}")
            }
            Row {
                Column {
                    Row {
                        NumberButton(onClick = { numberSelected(1) }, number = 1)
                        NumberButton(onClick = { numberSelected(2) }, number = 2)
                        NumberButton(onClick = { numberSelected(3) }, number = 3)
                    }
                    Row {
                        NumberButton(onClick = { numberSelected(4) }, number = 4)
                        NumberButton(onClick = { numberSelected(5) }, number = 5)
                        NumberButton(onClick = { numberSelected(6) }, number = 6)
                    }
                    Row {
                        NumberButton(onClick = { numberSelected(7) }, number = 7)
                        NumberButton(onClick = { numberSelected(8) }, number = 8)
                        NumberButton(onClick = { numberSelected(9) }, number = 9)
                    }
                    Row {
                        NumberButton(onClick = { numberSelected(0) }, number = 0)
                    }
                }
                Column {
                    OperationButton(onClick = { operationSelected(Add) }, label = "+")
                    OperationButton(onClick = { operationSelected(Subtract) }, label = "-")
                    OperationButton(onClick = { operationSelected(Multiply) }, label = "*")
                    OperationButton(onClick = { operationSelected(Divide) }, label = "/")
                    OperationButton(onClick = clearSelected, label = "Clear")
                    OperationButton(onClick = equalsSelected, label = "=")
                }
            }
        }
    }
}
