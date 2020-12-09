package scripts

import org.tribot.api2007.Inventory
import org.tribot.api2007.ext.Filters
import scripts.actions.Interact
import scripts.actions.bank.DepositAllExcept
import scripts.actions.bank.Withdraw
import scripts.actions.nav.DaxWalk
import scripts.actions.time.WaitFor
import scripts.builds.Bank
import scripts.conditions.player.PlayerAnimating
import scripts.conditions.player.PlayerHas
import scripts.conditions.player.PlayerIn
import scripts.constants.WOODCUTTING
import scripts.leafs.BTNode
import scripts.locale.WCLocale
import scripts.utils.bt.selector
import scripts.utils.bt.sequencer
import scripts.utils.nameContains
import scripts.utils.nearestObj
import scripts.utils.tools.axeType
import scripts.utils.tools.highestAxeFilter

class Woodcut(private val locale: WCLocale) {

    fun build(): BTNode {
        val builder = sequencer()
            .node(setup())
            .node(woodcut())
        return builder.build()
    }

    private fun setup(): BTNode {
        val setupComplete = PlayerHas{
            nameContains(it, " axe") && axeType(it).canUse() }.and{ !Inventory.isFull()
        }
        return selector("WCSetup Selector")
            .condition("WCSetup Condition", setupComplete)
            .node("WCBank", Bank()
                .action("DepositAllExcept Highest Axe", DepositAllExcept(highestAxeFilter()))
                .node(selector()
                    .condition("Has Highest Axe", PlayerHas(highestAxeFilter()))
                    .action("Withdraw Highest Axe", Withdraw(1, highestAxeFilter()))
                    .build()
                ).action("WCSetup Complete", WaitFor(setupComplete))
                .build())
            .build()
    }

    private fun woodcut(): BTNode {
        return sequencer("Woodcut Sequence")
            .node(selector()
                .condition("Player In WCArea?", PlayerIn(locale.treeArea()))
                .action("DaxWalk WCArea", DaxWalk(locale.destTile()))
                .build()

            ).node(selector()
                .condition("Player Woodcutting?", PlayerAnimating(WOODCUTTING))
                .node(sequencer()
                    .action("Chop Oak", Interact({ nearestObj(20, Filters.Objects.inArea(locale.treeArea())
                        .and(Filters.Objects.nameEquals(*locale.treeNames()))::test) }, "Chop"))
                    .action("Wait Woodcutting", WaitFor(PlayerAnimating(WOODCUTTING), 5000))
                    .build()
                ).build()
            ).build()
    }
}