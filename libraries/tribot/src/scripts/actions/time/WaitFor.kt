package scripts.actions.time

import org.tribot.api.Timing
import scripts.interfaces.Action
import scripts.interfaces.Condition
import scripts.utils.boolToState

class WaitFor(
    private val cond: Condition,
    private val timeout: Long = 3000
): Action {
    override fun invoke() = boolToState(Timing.waitCondition(cond, timeout))
    override fun toString() = "WaitFor{$cond, ${timeout}ms}"
}