package scripts.interfaces


open class Condition(private var baseCond: () -> Boolean = {true}): () -> Boolean {

    fun and(cond: () -> Boolean): Condition {
        val cached = this.baseCond
        this.baseCond = { cached.invoke() && cond.invoke() }
        return this
    }

    fun or(cond: () -> Boolean): Condition {
        val cached = this.baseCond
        this.baseCond = { cached.invoke() || cond.invoke() }
        return this
    }

    fun not(): Condition {
        val cached = baseCond
        baseCond = { !cached.invoke() }
        return this
    }

    override fun invoke() = baseCond.invoke()
}