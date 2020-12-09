package scripts.actions.bank

import org.tribot.api2007.Banking
import org.tribot.api2007.types.RSItem
import scripts.interfaces.Action
import scripts.leafs.BTNode
import scripts.utils.boolToState
import scripts.utils.nameOf

class Withdraw(
    private val count: Int,
    private val pred: (RSItem) -> Boolean): Action {

    private var str = "Withdraw{$pred}"

    constructor(count: Int, vararg names: String): this(count, { names.contains(nameOf(it)) }) {
        str = "Withdraw{x$count, ${names.contentToString()}}"
    }

    constructor(count: Int, vararg ids: Int): this(count, { ids.contains(it.id) }) {
        str = "Withdraw{x$count, ${ids.contentToString()}}"
    }

    override fun invoke(): BTNode.States {
        val itemIds = Banking.find(pred).map { it.id }.toIntArray()
        return boolToState(Banking.withdraw(count, *itemIds))
    }

    override fun toString() = str
}