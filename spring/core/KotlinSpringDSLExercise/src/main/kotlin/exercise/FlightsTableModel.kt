package exercise

import java.text.SimpleDateFormat
import java.util.ArrayList

import javax.swing.table.AbstractTableModel

class FlightsTableModel() : AbstractTableModel() {
    private val columnNames: Array<String> = arrayOf("Origin", "Destination", "Date", "Time")
    private val data: MutableList<Flight>

    init {
        data = ArrayList()
    }

    constructor(extra: List<Flight>) : this() {
        data.addAll(extra)
    }

    fun addFlight(flight: Flight) {
        data.add(flight)
        val index = data.size - 1
        fireTableRowsInserted(index, index)
    }

    fun addFlights(flights: List<Flight>) {
        val start = data.size
        data.addAll(flights)
        val end = data.size - 1
        fireTableRowsInserted(start, end)
    }

    fun getFlight(row: Int): Flight {
        return data[row]
    }

    override fun getColumnName(index: Int): String {
        return columnNames[index]
    }

    override fun getColumnCount(): Int {
        return 4
    }

    override fun getRowCount(): Int {
        return data.size
    }

    override fun getValueAt(x: Int, y: Int): Any? {
        val f = data[x]
        when (y) {
            0 -> return f.origin
            1 -> return f.destination
            2 -> return formatter1.format(f.departure)
            else -> return formatter2.format(f.departure)
        }
    }

    companion object {
        private val formatter1 = SimpleDateFormat("yyyy.MM.dd")
        private val formatter2 = SimpleDateFormat("HH.mm a")
    }
}
