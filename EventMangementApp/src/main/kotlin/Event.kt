interface Display {
    fun showEventDetails()
}

open class EventManager : Display {
    protected val events = mutableListOf<String>()

    private val onEventAdded: MutableList<(String) -> Unit> = mutableListOf()
    private val onEventRemoved: MutableList<(String) -> Unit> = mutableListOf()

    fun addEvent(event: String) {
        events.add(event)
        onEventAdded.forEach { it(event) }
        println("Event added: $event")
    }

    fun removeEvent(event: String) {
        if (event in events) {
            events.remove(event)
            onEventRemoved.forEach { it(event) }
            println("Event removed: $event")
        } else {
            println("Event not found: $event")
        }
    }

    //Events notification
    fun subscribeOnAdd(notify: (String) -> Unit) {
        onEventAdded.add(notify)
    }

    fun subscribeOnRemove(notify: (String) -> Unit) {
        onEventRemoved.add(notify)
    }

    override fun showEventDetails() {
        if (events.isEmpty()) {
            println("No events to display.")
        } else {
            println("Current events:")
            events.forEach { println(it) }
        }
    }
}

// SpecialEvent subclass with additional features like VIP lists and premium services.
class SpecialEvent : EventManager() {
    private val vipList = mutableListOf<String>()
    private val premiumServices = mutableListOf<String>()

    fun addVIP(guest: String) {
        vipList.add(guest)
        println("VIP added: $guest")
    }

    fun addPremiumService(service: String) {
        premiumServices.add(service)
        println("Premium service added: $service")
    }

    override fun showEventDetails() {
        super.showEventDetails()
        if (vipList.isNotEmpty()) {
            println("VIP Guests:")
            vipList.forEach { println(it) }
        }
        if (premiumServices.isNotEmpty()) {
            println("Premium Services:")
            premiumServices.forEach { println(it) }
        }
    }
}

fun main() {
    val specialEventManager = SpecialEvent()

    specialEventManager.subscribeOnAdd { event -> println("Notification: '$event' has been added to special events!") }

    specialEventManager.addEvent("Music Event")
    specialEventManager.addVIP("Aaqib Eqbal")
    specialEventManager.addPremiumService("Premium Party")

    specialEventManager.showEventDetails()
}