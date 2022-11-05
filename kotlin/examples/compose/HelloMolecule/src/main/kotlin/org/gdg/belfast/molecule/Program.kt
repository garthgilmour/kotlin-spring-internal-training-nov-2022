package org.gdg.belfast.molecule

import androidx.compose.runtime.*
import app.cash.molecule.RecompositionClock
import app.cash.molecule.moleculeFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun messenger(max: Int) = moleculeFlow(clock = RecompositionClock.Immediate) {
    var count by remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        while (count < max) {
            delay(1000)
            count++
        }
    }

    "Flow message $count"
}

fun main(): Unit = runBlocking {
    launch {
        while (true) {
            println("Main is not blocked")
            delay(500)
        }
    }

    messenger(5).collect(::println)
}
