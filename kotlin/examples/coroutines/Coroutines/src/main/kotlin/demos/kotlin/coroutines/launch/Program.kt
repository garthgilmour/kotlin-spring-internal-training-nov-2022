package demos.kotlin.coroutines.launch

import demos.kotlin.coroutines.shared.pingSlowServer
import demos.kotlin.coroutines.shared.timeNow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking(Dispatchers.IO) {
    println("Program starts at ${timeNow()}")

    val jobs = mutableListOf<Job>()
    jobs += launch { println(pingSlowServer(8)) }
    jobs += launch { println(pingSlowServer(6)) }
    jobs += launch { println(pingSlowServer(4)) }
    jobs += launch { println(pingSlowServer(2)) }

    println("All jobs running")
    jobs.forEach { it.join() }
    println("Program complete at ${timeNow()}")
}



