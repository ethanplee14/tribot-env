package scripts.utils

import org.tribot.api2007.Equipment
import org.tribot.api2007.Inventory
import org.tribot.api2007.types.RSItem
import org.tribot.api2007.types.RSItemDefinition
import scripts.singletons.BankCache


fun nameOf(itemId: Int) = RSItemDefinition.get(itemId).name

fun nameOf(item: RSItem) = item.definition.name

fun nameContains(item: RSItem, str: String) = nameOf(item).contains(str)

fun countAll(itemId: Int, includeBank: Boolean = false): Int {
    var count = Equipment.getCount(itemId) + Inventory.getCount(itemId)
    if(includeBank)
        count += BankCache.count(itemId)
    return count
}

/**
 * Uses Inventory.getCount() to count how many items the player has in inventory.
 * Meaning if 1 item is found in inventory it'll take stack into account. If multiple
 * are found, stack isn't accounted for.
 */
fun countAll(itemName: String, includeBank: Boolean = false): Int {
    var count = Equipment.getCount(itemName) + Inventory.getCount(itemName)
    if(includeBank)
        count += BankCache.count(itemName)
    return count
}

fun allItems() = Inventory.getAll() + Equipment.getItems()

fun allItemNames(includeBank: Boolean = false): Array<String> {
    var itemNames = allItems().map { nameOf(it) }.toTypedArray()
    if (includeBank)
        itemNames += BankCache.itemNames()
    return itemNames
}


