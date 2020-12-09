package scripts.leafs

abstract class BTNode(var name: String = "BTNode") {

    var state = States.IDLE
        protected set

    enum class States { IDLE, RUNNING, FAILURE, SUCCESS }

    abstract fun run(): States

    protected fun update(state: States): States {
        this.state = state
        return state
    }

    override fun toString() = "BTNode{$state}"
}