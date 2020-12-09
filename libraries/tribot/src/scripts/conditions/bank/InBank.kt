package scripts.conditions.bank

import org.tribot.api2007.Banking
import scripts.interfaces.Condition

class InBank: Condition(Banking::isInBank) {
    override fun toString() = "InBank?"
}