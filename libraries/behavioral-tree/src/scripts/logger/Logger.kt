package scripts.logger

interface Logger {
    fun debug(msg: Any)
    fun info(msg: Any)
    fun warn(msg: Any)
    fun error(msg: Any)
    fun fatal(msg: Any)
}