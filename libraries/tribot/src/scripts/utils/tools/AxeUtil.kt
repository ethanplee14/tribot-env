package scripts.utils.tools

import org.tribot.api2007.types.RSItem
import scripts.constants.AxeType
import scripts.utils.allItemNames
import scripts.utils.nameOf


fun axeType(item: RSItem) = axeType(nameOf(item))

fun axeType(axeName: String): AxeType {
    if (!axeName.contains(" axe"))
        return AxeType.NONE

    val typeString = axeName.split(" ")[0]
    for (type in AxeType.values())
        if(type.name.equals(typeString, ignoreCase = true))
            return type
    return AxeType.NONE
}

fun highestAxeFilter(checkBankCache: Boolean = true): (RSItem) -> Boolean = { nameOf(it) == highestUsableAxe(checkBankCache) }

fun highestUsableAxe(checkBankCache: Boolean = true): String {
    val axes = allItemNames(checkBankCache)
        .filter { it.contains(" axe") }
        .toTypedArray()
    return highestUsableAxe(*axes)
}

fun highestUsableAxe(vararg axeNames: String): String {
    if (axeNames.isEmpty())
        throw RuntimeException("Axe Names should not be empty")

    var highest = axeNames[0]
    axeNames.forEach {
        val type = axeType(it)
        if (type.canUse() && type.lvl > axeType(highest).lvl) {
            highest = it
        }
    }
    return highest
}
