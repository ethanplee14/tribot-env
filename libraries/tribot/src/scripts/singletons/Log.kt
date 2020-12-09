package scripts.singletons

import org.tribot.api.General
import scripts.logger.Logger

object Log: Logger {

    var state = State.INFO

    enum class State { DEBUG, INFO, WARN, ERROR, FATAL, OFF }

    override fun debug(msg: Any) = print(State.DEBUG, msg)
    override fun info(msg: Any) = print(State.INFO, msg)
    override fun warn(msg: Any) = print(State.WARN, msg)
    override fun error(msg: Any) = print(State.ERROR, msg)
    override fun fatal(msg: Any) = print(State.FATAL, msg)

    private fun print(state: State, msg: Any) {
        if(!sufficientStateLvl(state))
            return

        if(state == State.DEBUG)
            println("[PLEE/DEBUG] $msg")
        else
            General.println("[PLEE/${state.name}] $msg")
    }

    private fun sufficientStateLvl(state: State): Boolean {
        val logLvl = State.values().indexOf(this.state)
        val stateLvl = State.values().indexOf(state)
        return logLvl >= stateLvl
    }
}