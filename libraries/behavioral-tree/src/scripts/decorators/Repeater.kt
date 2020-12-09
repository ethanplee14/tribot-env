package scripts.decorators

import scripts.leafs.BTNode

/**
 * Repeats infinitely by default.
 */
class Repeater(
    private val node: BTNode,
    private val times: Int = -1
): BTNode("${node.name} Repeater") {
    override fun run(): States {
        var i = 0
        var state = BTNode.States.IDLE
        while(i < times) {
            state = node.run()
            i++
        }
        return state
    }

    override fun toString() = "Repeater{$node, $times}"
}