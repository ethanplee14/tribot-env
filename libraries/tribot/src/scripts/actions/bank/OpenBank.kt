package scripts.actions.bank

import org.tribot.api2007.Banking
import scripts.interfaces.Action
import scripts.utils.boolToState

class OpenBank: Action {
    override fun invoke() = boolToState(Banking.openBank())
    override fun toString() = "OpenBank"
}