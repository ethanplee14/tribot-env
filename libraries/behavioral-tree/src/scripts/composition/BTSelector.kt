package scripts.composition

/**
 * Checks for a single node to return success
 */
class BTSelector(name: String = "BTSelector"): BTNodeContainer(name) {

    /**
     * Iterates each node in order until one returns SUCCESS or RUNNING.
     * If found, immediately exit out of the loop and report the results.
     * If all children fail, will report FAILURE
     *
     * @return SUCCESS when any node succeeds. FAILURE when all fails. RUNNING when selector
     * is still processing a node that is still RUNNING.
     */
    override fun run(): States {
        for (node in nodes) {
            logger.debug("${toString()} running: <$node>")
            val results = node.run()
            logger.debug("$node returned: <$results>")

            if (results == States.SUCCESS || results == States.RUNNING)
                return update(results)
        }
        return update(States.FAILURE)
    }

    override fun toString() = "BTSelector{$name, $state}"
}