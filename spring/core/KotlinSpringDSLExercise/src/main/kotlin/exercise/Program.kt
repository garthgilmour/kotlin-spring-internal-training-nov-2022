package exercise

import org.springframework.boot.runApplication

object Program {
	@JvmStatic
	fun main(args: Array<String>) {
		val gui = FlightBookingGUI();
		gui.isVisible = true
	}
}


