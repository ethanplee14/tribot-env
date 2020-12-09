package scripts.actions

import org.tribot.api2007.types.RSObject
import scripts.interfaces.Action
import scripts.leafs.BTNode
import scripts.utils.boolToState

class Click(
    private val objFinder: () -> RSObject,
    private vararg val options: String
): Action {
    override fun invoke(): BTNode.States {
        val obj = objFinder.invoke()
        return boolToState(obj.click(*options))
    }
}