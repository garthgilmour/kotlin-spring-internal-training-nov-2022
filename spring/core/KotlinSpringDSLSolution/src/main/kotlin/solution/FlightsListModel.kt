package solution

import java.util.ArrayList

import javax.swing.ListModel
import javax.swing.event.ListDataListener

class FlightsListModel(items: List<String>) : ListModel<String> {
    private val items: MutableList<String>
    private val listeners: MutableList<ListDataListener>

    init {
        this.items = ArrayList()
        this.items.addAll(items)
        listeners = ArrayList()
    }

    override fun addListDataListener(param: ListDataListener) {
        listeners.add(param)
    }

    override fun getElementAt(index: Int): String {
        return items[index]
    }

    override fun getSize(): Int {
        return items.size
    }

    override fun removeListDataListener(param: ListDataListener) {
        listeners.remove(param)
    }
}
