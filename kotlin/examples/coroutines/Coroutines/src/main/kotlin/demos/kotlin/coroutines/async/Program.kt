package demos.kotlin.coroutines.async

import demos.kotlin.coroutines.shared.pingSlowServer
import demos.kotlin.coroutines.shared.timeNow
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking(Dispatchers.IO) {
    println("Program starts at ${timeNow()}")

    val deferredJobs = mutableListOf<Deferred<String>>()
    deferredJobs += async { pingSlowServer(8) }
    deferredJobs += async { pingSlowServer(6) }
    deferredJobs += async { pingSlowServer(4) }
    deferredJobs += async { pingSlowServer(2) }

    val results = deferredJobs
            .map { it.await() }
            .joinToString(
                    prefix = "\"",
                    postfix = "\"",
                    separator = ", ")
    println("Results are $results")
    println("Program complete at ${timeNow()}")
}



