package scripts.actions.bank

import scripts.interfaces.Action
import scripts.singletons.BankCache
import scripts.utils.boolToState

class UpdateBankCache: Action {
    override fun invoke() = boolToState(BankCache.update())
}