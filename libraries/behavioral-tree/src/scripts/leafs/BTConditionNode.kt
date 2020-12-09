package scripts.leafs

import scripts.utils.boolToState

class BTConditionNode(private val condition: () -> Boolean): BTNode("BTCondition") {

    override fun run(): States {
        val bool = condition.invoke()
        return update(boolToState(bool))
    }

    override fun toString() = "BTCondition{$name, $condition}"
}
