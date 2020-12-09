package scripts.composition

/**
 * Runs each node for all to succeed
 */
class BTSequencer(name: String = "BTSequencer"): BTNodeContainer(name) {

    /**
     * Iterates and runs each node in order. If any of the node returns FAILURE, the
     * sequence resolves as FAILURE. If any node is running, sequence will continue
     * to run the rest of the nodes and returns RUNNING. Resolves to SUCCESS only when
     * all nodes return SUCCESS
     *
     * @return FAILURE if any node failed. RUNNING if any node is running. SUCCESS if all nodes succeeds
     */
    override fun run(): States {
        for (node in nodes) {
            logger.debug("${toString()} running <$node>")
            val results = node.run()
            logger.debug("$node returned <$results>")

            if (results == States.FAILURE)
                return update(results)
            else if (results == States.RUNNING)
                update(results)
        }
        return if(state == States.RUNNING) state else update(States.SUCCESS)
    }

    override fun toString() = "BTSequencer{$name, $state}"
}