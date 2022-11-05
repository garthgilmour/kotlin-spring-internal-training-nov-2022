package org.gdg.belfast.mosaic;

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.jakewharton.mosaic.Text
import com.jakewharton.mosaic.runMosaic

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.jakewharton.mosaic.Column
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

import org.jline.terminal.TerminalBuilder

fun main() = runMosaic {
    var height by mutableStateOf(3)

    setContent {
        PyramidDisplay(height)
    }

    withContext(IO) {
        val terminal = TerminalBuilder.terminal().apply {
            enterRawMode()
        }
        val reader = terminal.reader()

        while (true) {
            when (val letter = reader.read()) {
                'q'.code -> break
                in '1'.code .. '9'.code -> {
                    height = letter - '0'.code
                }
            }
        }
    }
}

@Composable
fun PyramidDisplay(height: Int) {
    Column {
        PyramidTitle(height)
        PyramidBody(height)
    }
}

@Composable
fun PyramidTitle(height: Int) {
    Text("Here is a Pyramid of height $height")
}

@Composable
fun PyramidBody(height: Int) {
    Text(buildString {
        printPyramid(this, height)
    })
}

fun printPyramid(builder: StringBuilder, height: Int) {
    (1..height).forEach { row ->
        val spaces = height - row
        val hashes = (row * 2) - 1

        repeat(spaces) { builder.append(" ") }
        repeat(hashes) { builder.append("#") }
        builder.append("\n")
    }
}
