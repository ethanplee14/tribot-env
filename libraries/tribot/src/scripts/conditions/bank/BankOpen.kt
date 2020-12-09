package scripts.conditions.bank

import org.tribot.api2007.Banking
import scripts.interfaces.Condition

class BankOpen: Condition(Banking::isBankScreenOpen) {
    override fun toString() = "BankOpen?"
}