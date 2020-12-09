package scripts.actions

import org.tribot.api2007.types.RSObject
import scripts.interfaces.Action
import scripts.leafs.BTNode
import scripts.utils.boolToState

class Interact(
    private val objFinder: () -> RSObject?,
    private vararg val options: String
): Action {

    private var str = "Interact{$objFinder, ${options.contentToString()}}"

    override fun invoke(): BTNode.States {
        val obj = objFinder.invoke() ?: return BTNode.States.FAILURE
        if(!adjustCamera(obj))
            return BTNode.States.FAILURE
        return boolToState(obj.click(*options))
    }

    private fun adjustCamera(obj: RSObject): Boolean {
        if (!obj.isOnScreen || !obj.isClickable)
            return obj.adjustCameraTo()
        return true
    }

    override fun toString() = str
}