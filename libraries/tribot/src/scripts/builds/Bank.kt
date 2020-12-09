package scripts.builds

import scripts.actions.bank.OpenBank
import scripts.actions.bank.UpdateBankCache
import scripts.actions.nav.DaxBank
import scripts.actions.time.WaitFor
import scripts.builders.BTBuilder
import scripts.composition.BTNodeContainer
import scripts.composition.BTSequencer
import scripts.conditions.bank.BankOpen
import scripts.conditions.bank.InBank
import scripts.utils.selector


class Bank: BTBuilder(BTSequencer()) {

    init {
        node(selector("Bank Sequence")
            .condition(InBank())
            .action(DaxBank())
            .build()
        )
        .action(OpenBank())
        .action(WaitFor(BankOpen()))
        .action(UpdateBankCache())
    }

    override fun build(): BTNodeContainer {
        action(UpdateBankCache())
        return super.build()
    }
}

