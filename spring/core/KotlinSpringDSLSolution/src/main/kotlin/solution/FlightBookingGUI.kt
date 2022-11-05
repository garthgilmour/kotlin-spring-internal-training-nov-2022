package solution

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.swing.JOptionPane.*

import javax.swing.*
import javax.swing.border.TitledBorder
import javax.swing.GroupLayout.Alignment

import java.awt.*
import java.awt.event.*
import java.text.*
import java.util.Date
import java.util.Locale

@Service("gui")
class FlightBookingGUI @Autowired constructor(private val controller: BookingController) : JFrame("Flight Booking Application"), ActionListener {
    // GUI properties
    private val resultsTableModel: FlightsTableModel
    private val searchResultsTable: JTable
    private val specialOffersTable: JTable
    private var flightSearchPanel: JPanel? = null
    private val originSelection: JList<String>
    private val destinationSelection: JList<String>
    private val monthSelection: JComboBox<String>
    private val daySelection: JComboBox<Int>
    private val originLabel: JLabel
    private val destinationLabel: JLabel
    private val monthLabel: JLabel
    private val dayLabel: JLabel
    private val searchButton: JButton
    private val bookingButton: JButton


    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        resultsTableModel = FlightsTableModel()

        originLabel = buildBlueLabel("Origin Airport")
        destinationLabel = buildBlueLabel("Destination Airport")
        monthLabel = buildBlueLabel("Month of Travel")
        dayLabel = buildBlueLabel("Day of Travel")

        searchButton = JButton("Search For Flights")
        bookingButton = JButton("Book Flight")
        searchResultsTable = JTable(resultsTableModel)
        specialOffersTable = JTable(FlightsTableModel(Utilities.specialOffers))

        originSelection = JList(FlightsListModel(Utilities.airports))
        destinationSelection = JList(FlightsListModel(Utilities.airports))

        monthSelection = JComboBox(Utilities.months)
        daySelection = JComboBox(Utilities.days)

        finishSetup()
    }

    private fun buildBlueLabel(msg: String): JLabel {
        val tmp = JLabel(msg)
        tmp.foreground = Color.BLUE
        return tmp
    }

    private fun finishSetup() {
        createLayout()
        addEventHandling()
        pack()
    }

    private fun addEventHandling() {
        searchButton.addActionListener(this)
        bookingButton.addActionListener(this)
    }

    private fun createLayout() {
        flightSearchPanel = createFlightSelectionPanel()

        val tablesBox = createBoxForTables()

        val layout = GroupLayout(contentPane)
        layout.autoCreateGaps = true
        contentPane.layout = layout

        val g1 = layout.createParallelGroup()
        g1.addComponent(flightSearchPanel)
        g1.addComponent(searchButton)
        g1.addComponent(tablesBox)
        g1.addComponent(bookingButton)
        layout.setHorizontalGroup(g1)

        val g2 = layout.createSequentialGroup()
        g2.addComponent(flightSearchPanel)
        g2.addComponent(searchButton)
        g2.addComponent(tablesBox)
        g2.addComponent(bookingButton)
        layout.setVerticalGroup(g2)
    }

    private fun createBoxForTables(): Box {
        val box = Box.createVerticalBox()
        var tmp: JScrollPane
        specialOffersTable.preferredScrollableViewportSize = Dimension(450, 100)
        searchResultsTable.preferredScrollableViewportSize = Dimension(450, 200)
        tmp = JScrollPane(specialOffersTable)
        tmp.border = TitledBorder("Current Special Offers")
        box.add(tmp)
        tmp = JScrollPane(searchResultsTable)
        tmp.border = TitledBorder("Available Flights")
        box.add(tmp)
        return box
    }

    private fun createFlightSelectionPanel(): JPanel {
        val panel = JPanel()
        panel.border = TitledBorder("Select flight details below:")

        val layout = GroupLayout(panel)
        layout.autoCreateGaps = true
        panel.layout = layout

        val g1 = layout.createSequentialGroup()
        val g2 = layout.createParallelGroup()
        g2.addComponent(originLabel)
        g2.addComponent(originSelection)
        val g3 = layout.createSequentialGroup()
        g3.addComponent(monthLabel)
        g3.addComponent(monthSelection)
        g2.addGroup(g3)

        val g4 = layout.createParallelGroup()
        g4.addComponent(destinationLabel)
        g4.addComponent(destinationSelection)
        val g5 = layout.createSequentialGroup()
        g5.addComponent(dayLabel)
        g5.addComponent(daySelection)
        g4.addGroup(g5)

        g1.addGroup(g2)
        g1.addGroup(g4)
        layout.setHorizontalGroup(g1)

        val g6 = layout.createSequentialGroup()

        val g7 = layout.createParallelGroup()
        g7.addComponent(originLabel)
        g7.addComponent(destinationLabel)

        val g8 = layout.createParallelGroup()
        g8.addComponent(originSelection)
        g8.addComponent(destinationSelection)

        val g9 = layout.createParallelGroup(Alignment.BASELINE)
        g9.addComponent(monthLabel)
        g9.addComponent(monthSelection)
        g9.addComponent(dayLabel)
        g9.addComponent(daySelection)

        g6.addGroup(g7)
        g6.addGroup(g8)
        g6.addGroup(g9)

        layout.setVerticalGroup(g6)

        return panel
    }

    override fun actionPerformed(e: ActionEvent) {
        if (e.source === bookingButton) {
            makeBooking()
        } else if (e.source === searchButton) {
            runSearch()
        }
    }

    private fun runSearch() {
        if (originSelection.selectedIndex >= 0 && destinationSelection.selectedIndex >= 0) {
            val outbound = originSelection.selectedValue as String
            val inbound = destinationSelection.selectedValue as String
            val date = buildDateFromGuiInputs()

            val results = controller.findAvailableFlights(outbound, inbound, date)
            resultsTableModel.addFlights(results)
        } else {
            showMessageDialog(this, "Please choose airports!", "Error", ERROR_MESSAGE)
        }
    }

    private fun makeBooking() {
        val rowSelected = searchResultsTable.selectedRow
        if (rowSelected >= 0) {
            val flight = resultsTableModel.getFlight(rowSelected)
            val result = controller.bookFlight(flight)
            if (result) {
                showMessageDialog(this, "Flight Booked!", "Result", INFORMATION_MESSAGE)
            } else {
                showMessageDialog(this, "Booking Failed!", "Result", INFORMATION_MESSAGE)
            }
        } else {
            showMessageDialog(this, "Please choose a flight!", "Error", ERROR_MESSAGE)
        }
    }

    private fun buildDateFromGuiInputs(): Date {
        val df = DateFormat.getDateInstance(DateFormat.LONG, Locale.US)
        val dateAsString = formatDateString()
        return df.parse(dateAsString)
    }

    private fun formatDateString(): String {
        val sb = StringBuffer()
        sb.append(monthSelection.selectedItem!!.toString())
        sb.append(" ")
        sb.append(daySelection.selectedItem!!.toString())
        sb.append(", 2018")
        return sb.toString()
    }
}
