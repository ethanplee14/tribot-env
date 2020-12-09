package scripts.utils

import scripts.builders.BTBuilder
import scripts.composition.BTSelector
import scripts.composition.BTSequencer
import scripts.leafs.BTNode

fun boolToState(bool: Boolean): BTNode.States {
    return if (bool) BTNode.States.SUCCESS
    else BTNode.States.FAILURE
}

fun selector() = BTBuilder(BTSelector())
fun sequencer() = BTBuilder(BTSequencer())

fun selector(name: String): BTBuilder {
    val sel = BTSelector()
    sel.name = name
    return BTBuilder(sel)
}

fun sequencer(name: String): BTBuilder {
    val seq = BTSequencer()
    seq.name = name
    return BTBuilder(seq)
}