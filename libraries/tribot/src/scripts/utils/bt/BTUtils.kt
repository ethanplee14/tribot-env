package scripts.utils.bt

import scripts.builders.BTBuilder
import scripts.composition.BTSelector
import scripts.composition.BTSequencer
import scripts.singletons.Log


fun selector(): BTBuilder {
    val sel = BTSelector()
    sel.logger = Log
    return BTBuilder(sel)
}

fun selector(name: String): BTBuilder {
    val sel = BTSelector()
    sel.name = name
    sel.logger = Log
    return BTBuilder(sel)
}

fun sequencer(): BTBuilder {
    val seq = BTSequencer()
    seq.logger = Log
    return BTBuilder(seq)
}

fun sequencer(name: String): BTBuilder {
    val seq = BTSequencer()
    seq.name = name
    seq.logger = Log
    return BTBuilder(seq)
}