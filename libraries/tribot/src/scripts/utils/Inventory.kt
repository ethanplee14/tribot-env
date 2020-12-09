package scripts.utils

import org.tribot.api2007.Inventory
import org.tribot.api2007.types.RSItem


fun invCount(): Int {
    return Inventory.getAll()
        .map(RSItem::getStack)
        .reduce(Integer::sum)
}

fun invCount(exceptionFilter: (RSItem) -> Boolean): Int {
    return Inventory.getAll()
        .filter{ !exceptionFilter.invoke(it) }
        .map(RSItem::getStack)
        .fold(0, Integer::sum)
}

