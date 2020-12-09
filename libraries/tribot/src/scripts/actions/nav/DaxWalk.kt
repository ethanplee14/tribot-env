package scripts.actions.nav

import org.tribot.api.interfaces.Positionable
import org.tribot.api2007.types.RSArea
import org.tribot.api2007.types.RSTile
import scripts.dax_api.api_lib.DaxWalker
import scripts.interfaces.Action
import scripts.leafs.BTNode
import scripts.utils.boolToState

class DaxWalk(private val tileSupplier: () -> Positionable): Action {

    private var str = "DaxWalk{$tileSupplier}"

    constructor(area: RSArea): this(area::getRandomTile) {
        str = "DaxWalk{$area}"
    }

    constructor(area: RSArea, plane: Int): this({
        val tile = area.randomTile
        RSTile(tile.x, tile.y, plane)
    }) {
        str = "DaxWalk{$area}"
    }

    constructor(tile: RSTile): this({tile}) {
        str = "DaxWalk{$tile}"
    }

    override fun invoke(): BTNode.States {
        val tile = tileSupplier.invoke()
        return boolToState(DaxWalker.walkTo(tile))
    }

    override fun toString() = str
}