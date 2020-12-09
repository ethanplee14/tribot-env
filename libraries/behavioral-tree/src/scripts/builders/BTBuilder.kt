package scripts.builders

import scripts.composition.BTNodeContainer
import scripts.leafs.BTActionNode
import scripts.leafs.BTConditionNode
import scripts.leafs.BTNode


open class BTBuilder(private val container: BTNodeContainer) {

    fun action(action: () -> BTNode.States): BTBuilder {
        container.add(BTActionNode(action))
        return this
    }

    fun action(name: String, action: () -> BTNode.States): BTBuilder {
        val node = BTActionNode(action)
        node.name = name
        container.add(node)
        return this
    }

    fun condition(condition: () -> Boolean): BTBuilder {
        container.add(BTConditionNode(condition))
        return this
    }

    fun condition(name: String, condition: () -> Boolean): BTBuilder {
        val node = BTConditionNode(condition)
        node.name = name
        container.add(node)
        return this
    }

    fun node(node: BTNode): BTBuilder {
        container.add(node)
        return this
    }

    fun node(name: String, node: BTNode): BTBuilder {
        node.name = name
        container.add(node)
        return this
    }

    open fun build(): BTNodeContainer {
        return container
    }
}