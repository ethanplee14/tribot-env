package scripts.logger

class EmptyLogger: Logger {
    override fun debug(msg: Any) {}
    override fun info(msg: Any) {}
    override fun warn(msg: Any) {}
    override fun error(msg: Any) {}
    override fun fatal(msg: Any) {}
}