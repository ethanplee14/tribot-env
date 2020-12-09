package scripts.composition

import scripts.leafs.BTNode
import scripts.logger.EmptyLogger
import scripts.logger.Logger

abstract class BTNodeContainer(name: String): BTNode(name) {

    var logger: Logger = EmptyLogger()
    protected val nodes = mutableListOf<BTNode>()

    fun add(node: BTNode) {
        nodes.add(node)
    }

    fun addAll(vararg nodes: BTNode) {
        this.nodes.addAll(nodes)
    }
}