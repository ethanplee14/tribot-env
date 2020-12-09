package scripts.actions.bank

import org.tribot.api2007.Banking
import org.tribot.api2007.Inventory
import org.tribot.api2007.types.RSItem
import scripts.interfaces.Action
import scripts.leafs.BTNode
import scripts.utils.boolToState
import scripts.utils.invCount
import scripts.utils.nameOf

class DepositAll: Action {
    override fun invoke(): BTNode.States {
        val count = invCount()
        val depositCount = Banking.depositAll()
        return boolToState(count == depositCount)
    }

    override fun toString() = "DepositAll"
}

class DepositAllExcept(private val filter: (RSItem) -> Boolean): Action {

    private var str = "DepositAllExcept{$filter}"

    constructor(vararg names: String): this({ names.contains(nameOf(it)) }) {
        str = "DepositAllExcept{${names.contentToString()}}"
    }

    constructor(vararg ids: Int): this({ ids.contains(it.id) }) {
        str = "DepositAllExcept{${ids.contentToString()}}"
    }

    override fun invoke(): BTNode.States {
        val count = invCount(filter)
        val invIds = Inventory.getAll().filter(filter).map(RSItem::getID).toIntArray()
        val depositCount = Banking.depositAllExcept(*invIds)
        return boolToState(count == depositCount)
    }

    override fun toString() = str
}
