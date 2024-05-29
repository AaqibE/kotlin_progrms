import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

interface Display {
    fun showEventDetails()
}

open class EventManager : Display {
    protected val events = mutableListOf<Pair<String?, LocalDateTime?>>()

    private val onEventAdded: MutableList<(Pair<String?, LocalDateTime?>) -> Unit> = mutableListOf()
    private val onEventRemoved: MutableList<(Pair<String?, LocalDateTime?>) -> Unit> = mutableListOf()

    fun addEvent(event: String?, dateTime: String?) {
        if (event == null) {
            println("Cannot add null event.")
            return
        }

        val parsedDateTime = try {
            dateTime?.let { LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE_TIME) }
        } catch (e: DateTimeParseException) {
            println("Error parsing date and time for event '$event': ${e.message}")
            null
        }

        events.add(Pair(event, parsedDateTime))
        onEventAdded.forEach { it(Pair(event, parsedDateTime)) }
        println("Event added: $event at ${parsedDateTime ?: "Unknown time"}")
    }

    fun removeEvent(event: String?) {
        if (event == null) {
            println("Cannot remove null event.")
            return
        }

        val eventToRemove = events.find { it.first == event }
        if (eventToRemove != null) {
            events.remove(eventToRemove)
            onEventRemoved.forEach { it(eventToRemove) }
            println("Event removed: $event")
        } else {
            println("Event not found: $event")
        }
    }

    // Events notification
    fun subscribeOnAdd(notify: (Pair<String?, LocalDateTime?>) -> Unit) {
        onEventAdded.add(notify)
    }

    fun subscribeOnRemove(notify: (Pair<String?, LocalDateTime?>) -> Unit) {
        onEventRemoved.add(notify)
    }

    override fun showEventDetails() {
        if (events.isEmpty()) {
            println("No events to display.")
        } else {
            println("Current events:")
            events.forEach {
                println("Event: ${it.first ?: "Unknown event"}, Time: ${it.second ?: "Unknown time"}")
            }
        }
    }
}

// SpecialEvent subclass with additional features like VIP lists and premium services.
class SpecialEvent : EventManager() {
    private val vipList = mutableListOf<String?>()
    private val premiumServices = mutableListOf<String?>()

    fun addVIP(guest: String?) {
        if (guest == null) {
            println("Cannot add null VIP.")
            return
        }
        vipList.add(guest)
        println("VIP added: $guest")
    }

    fun addPremiumService(service: String?) {
        if (service == null) {
            println("Cannot add null premium service.")
            return
        }
        premiumServices.add(service)
        println("Premium service added: $service")
    }

    override fun showEventDetails() {
        super.showEventDetails()
        if (vipList.isNotEmpty()) {
            println("VIP Guests:")
            vipList.forEach { println(it ?: "Unknown VIP") }
        }
        if (premiumServices.isNotEmpty()) {
            println("Premium Services:")
            premiumServices.forEach { println(it ?: "Unknown service") }
        }
    }
}

fun main() {
    val specialEventManager = SpecialEvent()

    specialEventManager.subscribeOnAdd { event ->
        println("Notification: '${event.first ?: "Unknown event"}' has been added to special events at ${event.second ?: "Unknown time"}!")
    }

    specialEventManager.addEvent("Music Event", "2024-05-29T20:30:00")
    specialEventManager.addVIP("Aaqib Eqbal")
    specialEventManager.addPremiumService("Premium Party")
    specialEventManager.addEvent(null, "2024-05-29T20:30:00")  // Example of adding null event
    specialEventManager.addEvent("Invalid Event", "Invalid date-time")  // Example of adding an event with invalid date-time

    specialEventManager.showEventDetails()

    specialEventManager.removeEvent(null)  // Example of removing null event
}
