package scripts.decorators

import scripts.leafs.BTNode

class RepeatUntil(
    private val node: BTNode,
    private val cond: () -> Boolean
): BTNode() {
    override fun run(): States {
        var res = States.FAILURE
        while (cond.invoke())
            res = node.run()
        return res
    }
}