package scripts.utils

import org.tribot.api2007.Player
import org.tribot.api2007.types.RSArea
import org.tribot.api2007.types.RSCharacter
import org.tribot.api2007.types.RSTile
import kotlin.math.roundToInt


fun playerIn(area: RSArea) = area.contains(Player.getPosition())

fun playerIn(area: RSArea, includePlane: Boolean = true) = if(includePlane) {
    area.contains(RSTile(Player.getPosition().x, Player.getPosition().y, area.plane))
} else {
    area.contains(Player.getPosition())
}

fun playerAt(tile: RSTile) = Player.getPosition() == tile

fun playerFacing(): RSCharacter.DIRECTION {
    val direction = arrayOf(
        RSCharacter.DIRECTION.S,
        RSCharacter.DIRECTION.SW,
        RSCharacter.DIRECTION.W,
        RSCharacter.DIRECTION.NW,
        RSCharacter.DIRECTION.N,
        RSCharacter.DIRECTION.NE,
        RSCharacter.DIRECTION.E,
        RSCharacter.DIRECTION.SE
    )
    val orientation = (Player.getRSPlayer().orientation / 256.0).roundToInt()
    return direction[orientation]
}

fun frontTile(): RSTile {
    val directionPoint = mapOf(
        Pair(RSCharacter.DIRECTION.N, Pair(0, 1)),
        Pair(RSCharacter.DIRECTION.NE, Pair(1, 1)),
        Pair(RSCharacter.DIRECTION.E, Pair(1, 0)),
        Pair(RSCharacter.DIRECTION.SE, Pair(1, -1)),
        Pair(RSCharacter.DIRECTION.S, Pair(0, -1)),
        Pair(RSCharacter.DIRECTION.SW, Pair(-1, -1)),
        Pair(RSCharacter.DIRECTION.W, Pair(-1, 0)),
        Pair(RSCharacter.DIRECTION.NW, Pair(-1, 1))
    )
    val facing = playerFacing()
    val frontPoint = directionPoint[facing]
    return Player.getPosition().translate(frontPoint?.first ?: 0, frontPoint?.second ?: 0)
}