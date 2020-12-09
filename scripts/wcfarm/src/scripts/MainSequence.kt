package scripts

import scripts.composition.BTNodeContainer
import scripts.decorators.RepeatUntil
import scripts.leafs.BTNode
import scripts.locale.WCLocale
import scripts.utils.sequencer


class MainSequence(
        private val normLocale: WCLocale,
        private val oakLocale: WCLocale
) {

    fun build(): BTNodeContainer {
        val mainSeq = sequencer("Main")
//            .node("Primer", RepeatUntil(Woodcut(normLocale).build(), HasEnoughGPOrLogs()))
        return mainSeq.build()
    }
}

