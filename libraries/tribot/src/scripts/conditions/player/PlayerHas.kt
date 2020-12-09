package scripts.conditions.player

import org.tribot.api2007.Equipment
import org.tribot.api2007.Inventory
import org.tribot.api2007.types.RSItem
import scripts.interfaces.Condition
import scripts.utils.nameOf

class PlayerHas(private val pred: (RSItem) -> Boolean): Condition({
    val equipment = Equipment.getItems()
    val inv = Inventory.getAll()
    equipment.count(pred) > 0 || inv.count(pred) > 0
}) {

    private var str = "PlayerHas{$pred}"

    constructor(vararg names: String): this({ names.contains(nameOf(it)) }) {
        str = "PlayerHas{${names.contentToString()}"
    }

    constructor(vararg ids: Int): this({ ids.contains(it.id) }) {
        str = "PlayerHas{${ids.contentToString()}"
    }

    override fun toString() = str
}