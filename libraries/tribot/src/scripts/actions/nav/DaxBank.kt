package scripts.actions.nav

import scripts.dax_api.api_lib.DaxWalker
import scripts.interfaces.Action
import scripts.leafs.BTNode
import scripts.utils.boolToState

class DaxBank: Action {
    override fun invoke(): BTNode.States {
        return boolToState(DaxWalker.walkToBank())
    }
}
