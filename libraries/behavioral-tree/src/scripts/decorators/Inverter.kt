package scripts.decorators

import scripts.leafs.BTNode

class Inverter(private val node: BTNode): BTNode() {

    override fun run(): States {
        return when(val results = node.run()) {
            States.SUCCESS -> States.FAILURE
            States.FAILURE -> States.SUCCESS
            else -> results
        }
    }

    override fun toString() = "Inverter{$node}"
}