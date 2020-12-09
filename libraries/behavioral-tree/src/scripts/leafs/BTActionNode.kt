package scripts.leafs

class BTActionNode(private val action: () -> States): BTNode("BTAction") {
    override fun run() = action.invoke()
    override fun toString() = "BTAction{$name, $action}"
}